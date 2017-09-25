package com.sync.dao;

import com.sync.pojo.CategoryBean;
import com.sync.pojo.WorkOrderBean;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:07 2017/9/04.
 * @Modified By :
 */
public interface TableInfoDao {

    List<WorkOrderBean> queryTableInfo();

    List<CategoryBean> queryTableInfoCategory();

    void addData(List<Map<String, String>> mapList, String table, String fields);

    void addTagartTableData(List<WorkOrderBean> beanList);

    void dataGetAndAnalyze();
}
