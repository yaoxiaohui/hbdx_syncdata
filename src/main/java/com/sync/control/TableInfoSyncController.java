package com.sync.control;

import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :表信息同步任务
 * @Date Created in 18:39 2017/9/04.
 * @Modified By :
 */
public class TableInfoSyncController{

    private static final Logger log = Logger.getLogger(TableInfoSyncController.class);
    TableInfoService tableInfoService = new TableInfoServiceImpl();

    public void run(){
        try {
            log.info("=============================TableInfoSyncControl run===============================");
            List<Map<String, String>> mapList = tableInfoService.queryTableInfo();
            log.info(">>>>>>>>>获得数据："+mapList.size()+"条");
            tableInfoService.addData(mapList);
            log.info("=============================TableInfoSyncControl stop===============================");
        }catch (Exception e) {
            log.error("TableInfoSyncControl.run() is error >>>>>>", e);
        }
    }
}
