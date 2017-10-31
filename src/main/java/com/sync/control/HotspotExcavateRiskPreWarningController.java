package com.sync.control;

import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HotspotExcavateRiskPreWarningController {

    private static final Logger log = Logger.getLogger(HotspotExcavateRiskPreWarningController.class);
    TableInfoService tableInfoService = new TableInfoServiceImpl();

    //启动时执行一次，后面每隔12小时执行一次（毫秒）
    @Scheduled(fixedRate = 1000 * 60 * 60 * 12)
    public void dataAnalyze(){
        try {
            log.info("=============================HotspotExcavateRiskPreWarningController start===============================");
            tableInfoService.noDataGetAndAnalyze();
            log.info("=============================HotspotExcavateRiskPreWarningController stop===============================");
        }catch (Exception e) {
            log.error("HotspotExcavateRiskPreWarningController.run() is error >>>>>>", e);
        }
    }
}
