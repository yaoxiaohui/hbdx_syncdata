package com.sync.control;

import com.sync.pojo.WorkOrderBean;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
import util.FileProcess;
import util.FtpUtil;
import util.TimestampTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description : 原始数据同步
 * @Date Created in 18:39 2017/9/04.
 * @Modified By :
 */
@Component
public class ResourceDataSyncController {

    private static final Logger log = Logger.getLogger(ResourceDataSyncController.class);
    private static TableInfoService tableInfoService = new TableInfoServiceImpl();
    private static final String FILE_PATTERN = "/%s/%s-%s.txt";

    @Scheduled(cron = "0 25 14 * * ?")
    public static void syncData() {

        FtpClient ftpClient = null;
        try {
            log.info("=============================TableInfoSyncControl start===============================");
            // 连接ftp
            ftpClient = FtpUtil.connect(FtpUtil.FTPINFO_PROPERTIES.get("IP"), Integer.parseInt(FtpUtil.FTPINFO_PROPERTIES.get("PORT")),
                    FtpUtil.FTPINFO_PROPERTIES.get("USER"), FtpUtil.FTPINFO_PROPERTIES.get("PASSWORD"), FtpUtil.FTPINFO_PROPERTIES.get("REMOTEPATH"));
            // 取当天所有未处理文件
            String curDate = TimestampTool.getCurrentDate();
            List<String> fileNameList = FtpUtil.getFileList(ftpClient, "/" + curDate);
            // 解析时间，求三个文件时间交集，取同时存在的三个文件。
            List<String> curTimeList = getAllFileDateTime(fileNameList);
            for (String curTime : curTimeList) {
                processOneTimeSection(ftpClient, curDate, curTime);
                log.info("=============================processOneTimeSection:" + curTime +"===============================");
            }
            log.info("=============================TableInfoSyncControl stop===============================");
        } catch (Exception e) {
            log.error("TableInfoSyncControl.run() is error >>>>>>", e);
        } finally {
            if (ftpClient != null) {
                if (ftpClient.isConnected()) {
                    try {
                        ftpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 处理一个时间段（当前半小时为一个时间段）的文件
     *
     * @param ftpClient ftp客户端
     * @param curDate   当前日期
     * @param curTime   当前时间段
     */
    private static void processOneTimeSection(FtpClient ftpClient, String curDate, String curTime) throws IOException, FtpProtocolException {
        // 遍历SERVICEREQUEST生成<CONTACTID,SERVICEREQUEST>映射
        // 遍历TRECORDINFO生成<CALLID,TRECORDINFO日期>映射
        String srFile = String.format(FILE_PATTERN, curDate, "SERVICEREQUEST", curTime);
        String riFile = String.format(FILE_PATTERN, curDate, "TRECORDINFO", curTime);
        Map<String, String> srMap = FileProcess.readFile(ftpClient, srFile, 7);
        Map<String, String> riMap = FileProcess.readFile(ftpClient, riFile, 0);
        // 遍历CONTACTDETAIL，过滤T.CONTACTDURATION, 接触时长为0的记录，
        // 关联SERVICEREQUEST和TRECORDINFO，得到完整信息，插入workOrder表
        String cdFile = String.format(FILE_PATTERN, curDate, "CONTACTDETAIL", curTime);
        List<String> cdList = FileProcess.readFile(ftpClient, cdFile, 1, "0");
        String contactid;// 人工接触流水号
        String callid;// 录音id
        String[] srItems;
        String serialNum;
        String customerServiceId;// 受理员工号
        String phoneNum; //来电号码
        String acceptLocation;
        String phoneLocation;
        String sourceAudioPath;
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String, String> map;
        for (String loopcd : cdList) {
            String[] cdFieldValue = loopcd.split("\\|");
            if (cdFieldValue.length != 5 || StringUtils.isEmpty(cdFieldValue[2]) || StringUtils.isEmpty(cdFieldValue[3])) {
                continue;
            }
            contactid = cdFieldValue[2];
            callid = cdFieldValue[3];
            if (srMap.get(contactid) == null || riMap.get(callid) == null) {
                continue;
            }
            srItems = srMap.get(contactid).split("\\|");
            riMap.get(callid);

            serialNum = contactid;
            customerServiceId = cdFieldValue[4];
            phoneNum = srItems[0];
            acceptLocation = srItems[10];
            phoneLocation = srItems[8];
            sourceAudioPath = riMap.get(callid).split("\\|")[1];
            map = new HashMap<>();
            map.put("serialNum", serialNum);
            map.put("customerServiceId", customerServiceId);
            map.put("phoneNum", phoneNum);
            map.put("acceptLocation", acceptLocation);
            map.put("phoneLocation", phoneLocation);
            map.put("sourceAudioPath", sourceAudioPath);
            mapList.add(map);
        }
        tableInfoService.addData(mapList, "workorder", FileProcess.arrayToString(WorkOrderBean.WORKORDERFeilds));

        //修改已同步的文件名
        ftpClient.rename(srFile,srFile+".common");
        ftpClient.rename(riFile,riFile+".common");
        ftpClient.rename(cdFile,cdFile+".common");
    }

    /**
     * 取所有当天未处理数据时间值，只包含三个文件均存在时间
     *
     * @param fileNameList
     * @return
     */
    private static List<String> getAllFileDateTime(List<String> fileNameList) {
        List<String> sr_dt_list = new ArrayList<>();
        List<String> cd_dt_list = new ArrayList<>();
        List<String> ri_dt_list = new ArrayList<>();
        for (String loopfn : fileNameList) {
            if (loopfn.startsWith("SERVICEREQUEST-")) {
                sr_dt_list.add(loopfn.replace("SERVICEREQUEST-", "").replace(".txt", ""));
            }
            if (loopfn.startsWith("CONTACTDETAIL-")) {
                cd_dt_list.add(loopfn.replace("CONTACTDETAIL-", "").replace(".txt", ""));
            }
            if (loopfn.startsWith("TRECORDINFO-")) {
                ri_dt_list.add(loopfn.replace("TRECORDINFO-", "").replace(".txt", ""));
            }
        }
        sr_dt_list.retainAll(cd_dt_list);
        sr_dt_list.retainAll(ri_dt_list);
        return sr_dt_list;
    }

}
