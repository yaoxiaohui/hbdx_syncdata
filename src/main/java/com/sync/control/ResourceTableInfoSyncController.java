package com.sync.control;

import com.sync.pojo.TRECORDINFO9;
import com.sync.pojo.T_CCT_CONTACTDETAIL;
import com.sync.pojo.T_SR_SERVICEREQUEST;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;
import util.FileProcess;
import util.TimestampTool;

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
        try {
            log.info("=============================TableInfoSyncControl start===============================");
            String[] tableNames = {"T_SR_SERVICEREQUEST", "T_CCT_CONTACTDETAIL", "TRECORDINFO9"};
            String[] filePaths = {"D:\\"+ TimestampTool.getCurrentDate()+"\\SERVICEREQUEST.txt",
                    "D:\\"+ TimestampTool.getCurrentDate()+"\\CONTACTDETAIL.txt",
                    "D:\\"+ TimestampTool.getCurrentDate()+"\\TRECORDINFO.txt"};
            String[][] tableFields = {T_SR_SERVICEREQUEST.TSRSERVICEREQUESTFeilds,
                    T_CCT_CONTACTDETAIL.TCCTCONTACTDETAILFeilds,
                    TRECORDINFO9.TRECORDINFO9Feilds};
            for (int i = 0; i < tableNames.length ; i++) {
                List<Map<String, String>> mapList = FileProcess.readFile(filePaths[i], tableFields[i]);
                log.info(tableNames[i]+"获得的数据量是>>>>>>>>>>>>>>"+mapList.size()+"条");
                tableInfoService.addData(mapList, tableNames[i], FileProcess.arrayToString(tableFields[i]));
            }
            log.info("=============================TableInfoSyncControl stop===============================");
        }catch (Exception e) {
            log.error("TableInfoSyncControl.run() is error >>>>>>", e);
        }
    }
}
