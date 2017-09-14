package com.sync.service.impl;

import com.sync.dao.impl.TableInfoDaoImpl;
import com.sync.service.TableInfoService;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:09 2017/9/04.
 * @Modified By :
 */
public class TableInfoServiceImpl implements TableInfoService {

    public List<Map<String, String>> queryTableInfo() {
        return new TableInfoDaoImpl().queryTableInfo();
    }

    public void addData(List<Map<String, String>> mapList) {
        new TableInfoDaoImpl().addData(mapList);
    }
}
