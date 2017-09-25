package com.sync.service.impl;

import com.sync.dao.impl.TableInfoDaoImpl;
import com.sync.pojo.CategoryBean;
import com.sync.pojo.WorkOrderBean;
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

    public List<WorkOrderBean> queryTableInfo() {
        return new TableInfoDaoImpl().queryTableInfo();
    }

    public List<CategoryBean> queryTableInfoCategory() {
        return new TableInfoDaoImpl().queryTableInfoCategory();
    }

    public void addData(List<Map<String, String>> mapList, String table, String fields) {
        new TableInfoDaoImpl().addData(mapList, table, fields);
    }
    public void addTagartTableData(List<WorkOrderBean> beanList) {
        new TableInfoDaoImpl().addTagartTableData(beanList);
    }

    public void dataGetAndAnalyze() {
        new TableInfoDaoImpl().dataGetAndAnalyze();
    }

}
