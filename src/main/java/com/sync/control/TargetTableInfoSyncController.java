package com.sync.control;

import com.sync.pojo.WorkOrderBean;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :表信息同步任务（同步和分析到目标表）
 * @Date Created in 18:39 2017/9/04.
 * @Modified By :
 */
public class TargetTableInfoSyncController {

    private static final Logger log = Logger.getLogger(TargetTableInfoSyncController.class);
    TableInfoService tableInfoService = new TableInfoServiceImpl();

    public void run(){
        try {
            log.info("=============================TableInfoSyncControl start===============================");
            //从原始表取出数据
            List<WorkOrderBean> beanList = tableInfoService.queryTableInfo();
            log.info(">>>>>>>>>获得数据："+beanList.size()+"条");
            //分析并插入数据
            tableInfoService.addTagartTableData(beanList);
            log.info("=============================TableInfoSyncControl stop===============================");
        }catch (Exception e) {
            log.error("TableInfoSyncControl.run() is error >>>>>>", e);
        }
    }
}
