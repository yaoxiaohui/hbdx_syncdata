package com.sync.service;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:07 2017/9/04.
 * @Modified By :
 */
public interface TableInfoService {

    List<Map<String, String>> queryTableInfo();

    void addData(List<Map<String, String>> mapList, String table, String fields);
}
