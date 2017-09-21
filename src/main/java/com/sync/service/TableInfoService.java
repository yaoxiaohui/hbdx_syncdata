package com.sync.service;

import com.sync.pojo.WorkOrderBean;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:07 2017/9/04.
 * @Modified By :
 */
public interface TableInfoService {

    List<WorkOrderBean> queryTableInfo();

    void addData(List<Map<String, String>> mapList, String table, String fields);
}
