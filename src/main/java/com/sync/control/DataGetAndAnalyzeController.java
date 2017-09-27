package com.sync.control;

import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @Author ljw
 * @Description :取出数据 分析并入库
 * @Date Created in 18:39 2017/9/04.
 * @Modified By :
 */
@Component
public class DataGetAndAnalyzeController {

    private static final Logger log = Logger.getLogger(DataGetAndAnalyzeController.class);
    TableInfoService tableInfoService = new TableInfoServiceImpl();

    @Scheduled(cron="0 25 14 * * ?")
    public void run(){
        try {
            log.info("=============================DataGetAndAnalyzeController start===============================");
            tableInfoService.dataGetAndAnalyze();
            log.info("=============================DataGetAndAnalyzeController stop===============================");
        }catch (Exception e) {
            log.error("DataGetAndAnalyzeController.run() is error >>>>>>", e);
        }
    }
}
