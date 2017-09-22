package com.sync.control;

import com.sync.pojo.TRECORDINFO9;
import com.sync.pojo.T_CCT_CONTACTDETAIL;
import com.sync.pojo.T_SR_SERVICEREQUEST;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import sun.net.ftp.FtpClient;
import util.FileProcess;
import util.FtpUtil;
import util.TimestampTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :表信息同步任务（同步到初始表）
 * @Date Created in 18:39 2017/9/04.
 * @Modified By :
 */
public class ResourceTableInfoSyncController {

    private static final Logger log = Logger.getLogger(ResourceTableInfoSyncController.class);
    TableInfoService tableInfoService = new TableInfoServiceImpl();
    private static final String FILE_PATTERN = "/%s/%s-%s.txt";

    public static void run(){

        FtpClient ftpClient = null;
        try {
            log.info("=============================TableInfoSyncControl start===============================");
            String[] tableNames = {"T_SR_SERVICEREQUEST", "T_CCT_CONTACTDETAIL", "TRECORDINFO9"};
//            String[] filePaths = {"D:\\"+ TimestampTool.getCurrentDate()+"\\SERVICEREQUEST.txt",
//                    "D:\\"+ TimestampTool.getCurrentDate()+"\\CONTACTDETAIL.txt",
//                    "D:\\"+ TimestampTool.getCurrentDate()+"\\TRECORDINFO.txt"};
            String[][] tableFields = {T_SR_SERVICEREQUEST.TSRSERVICEREQUESTFeilds,
                    T_CCT_CONTACTDETAIL.TCCTCONTACTDETAILFeilds,
                    TRECORDINFO9.TRECORDINFO9Feilds};

            //连接ftp
            ftpClient = FtpUtil.connect("136.142.25.4", 21, "testvoice", "testvoice", "/");
            String currentDate = TimestampTool.getCurrentDate();
            List<String> fileNameList = FtpUtil.getFileList(ftpClient, "/"+ currentDate);
            // 解析时间，求三个文件时间交集，取最小时间同时存在的三个文件。
            String minFileDateTime = getMinFileDateTime(fileNameList);
            if(StringUtils.isEmpty(minFileDateTime)){
                return;
            }
            // 遍历SERVICEREQUEST生成<CONTACTID,SERVICEREQUEST>映射
            // 遍历TRECORDINFO生成<CALLID,TRECORDINFO日期>映射
            String srFile = String.format(FILE_PATTERN,currentDate,"SERVICEREQUEST",minFileDateTime);
            String riFile = String.format(FILE_PATTERN,currentDate,"TRECORDINFO",minFileDateTime);
            String cdFile = String.format(FILE_PATTERN,currentDate,"CONTACTDETAIL",minFileDateTime);
            Map<String,String> srMap = FileProcess.readFile(ftpClient, srFile,7);
            Map<String,String> riMap = FileProcess.readFile(ftpClient, riFile,0);
            // 遍历CONTACTDETAIL，过滤T.CONTACTDURATION, 接触时长为0的记录，
            //    关联SERVICEREQUEST和TRECORDINFO，得到完整信息，插入workOrder表
            List<String> cdList = FileProcess.readFile(ftpClient, cdFile, 1,"0");
            for(String loopcd:cdList){
                String[] cdFieldValue = loopcd.split("\\|");

            }




//            for (int j = 0; j < fileNameList.size(); j++) {
//                String fileName = fileNameList.get(j);
//                for (int i = 0; i < tableNames.length ; i++) {
//                    if(fileName != null &&
//                            ((fileName.contains("SERVICEREQUEST") && tableNames[i].contains("SERVICEREQUEST"))
//                            || (fileName.contains("CONTACTDETAIL") && tableNames[i].contains("CONTACTDETAIL"))
//                            || (fileName.contains("TRECORDINFO") && tableNames[i].contains("TRECORDINFO")))){
//
//                        List<Map<String, String>> mapList = FileProcess.readFile(ftpClient,
//                                "/"+ currentDate +"/"+fileName, tableFields[i]);
//                        log.info(tableNames[i]+"获得的数据量是>>>>>>>>>>>>>>"+mapList.size()+"条");
////                        tableInfoService.addData(mapList, tableNames[i], FileProcess.arrayToString(tableFields[i]));
//                        //修改文件名，已同步
//                    }
//                }
//            }
            //修改已同步的文件名
            ftpClient.rename(srFile,srFile+".common");
            ftpClient.rename(riFile,riFile+".common");
            log.info("=============================TableInfoSyncControl stop===============================");
        }catch (Exception e) {
            log.error("TableInfoSyncControl.run() is error >>>>>>", e);
        }finally{
            if(ftpClient !=null){
                if(ftpClient.isConnected()){
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
     *
     * @param fileNameList
     * @return
     */
    private static String getMinFileDateTime(List<String> fileNameList){
        List<String> sr_dt_list = new ArrayList<>();
        List<String> cd_dt_list = new ArrayList<>();
        List<String> ri_dt_list = new ArrayList<>();
        for(String loopfn:fileNameList){
            if(loopfn.startsWith("SERVICEREQUEST-")){
                sr_dt_list.add(loopfn.replace("SERVICEREQUEST-","").replace(".txt", ""));
            }
            if(loopfn.startsWith("CONTACTDETAIL-")){
                cd_dt_list.add(loopfn.replace("CONTACTDETAIL-","").replace(".txt", ""));
            }
            if(loopfn.startsWith("TRECORDINFO-")){
                ri_dt_list.add(loopfn.replace("TRECORDINFO-","").replace(".txt",""));
            }
        }
        sr_dt_list.retainAll(cd_dt_list);
        sr_dt_list.retainAll(ri_dt_list);
        if(sr_dt_list.size() > 0){
            return sr_dt_list.get(0);
        }
        return "";
    }

    public static void main(String[] args) {
        run();
    }
}
