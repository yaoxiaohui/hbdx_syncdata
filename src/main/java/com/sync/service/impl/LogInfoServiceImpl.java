package com.sync.service.impl;

import com.sync.dao.impl.LogInfoDaoImpl;
import com.sync.pojo.LogInfo;
import com.sync.service.LogInfoService;

/**
 * Created by ljw on 2017/4/5.
 */
public class LogInfoServiceImpl implements LogInfoService {

    public void addData(LogInfo logInfo) {
        new LogInfoDaoImpl().addData(logInfo);
    }

}
