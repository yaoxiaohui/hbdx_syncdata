package com.sync.control;

import com.sync.pojo.TRECORDINFO9;
import com.sync.pojo.T_CCT_CONTACTDETAIL;
import com.sync.pojo.T_SR_SERVICEREQUEST;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;
import sun.net.ftp.FtpClient;
import util.FileProcess;
import util.FtpUtil;
import util.TimestampTool;

import java.io.IOException;
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

    public void run(){

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
            List<String> fileNameList = FtpUtil.getFileList(ftpClient, "/"+ TimestampTool.getCurrentDate());
            for (int j = 0; j < fileNameList.size(); j++) {
                String fileName = fileNameList.get(j);
                for (int i = 0; i < tableNames.length ; i++) {
                    if(fileName != null &&
                            ((fileName.contains("SERVICEREQUEST") && tableNames[i].contains("SERVICEREQUEST"))
                            || (fileName.contains("CONTACTDETAIL") && tableNames[i].contains("CONTACTDETAIL"))
                            || (fileName.contains("TRECORDINFO") && tableNames[i].contains("TRECORDINFO")))){

                        List<Map<String, String>> mapList = FileProcess.readFile(ftpClient,
                                "/"+ TimestampTool.getCurrentDate()+"/"+fileName, tableFields[i]);
                        log.info(tableNames[i]+"获得的数据量是>>>>>>>>>>>>>>"+mapList.size()+"条");
                        tableInfoService.addData(mapList, tableNames[i], FileProcess.arrayToString(tableFields[i]));
                        //修改文件名，已同步
                    }
                }
            }
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
}
