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
public class DataAnalyzeController {

    private static final Logger log = Logger.getLogger(DataAnalyzeController.class);
    TableInfoService tableInfoService = new TableInfoServiceImpl();

    public static void main(String[] args) {
        DataAnalyzeController dataAnalyzeController = new DataAnalyzeController();
        dataAnalyzeController.dataAnalyze();
    }
    //启动时执行一次，后面每隔30分钟执行一次（毫秒）
    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void dataAnalyze(){
        try {
            log.info("=============================DataGetAndAnalyzeController start===============================");
            tableInfoService.dataGetAndAnalyze();
            log.info("=============================DataGetAndAnalyzeController stop===============================");
        }catch (Exception e) {
            log.error("DataGetAndAnalyzeController.run() is error >>>>>>", e);
        }
    }
}
