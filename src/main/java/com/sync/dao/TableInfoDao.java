package com.sync.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:07 2017/9/04.
 * @Modified By :
 */
public interface TableInfoDao {

    List<Map<String, String>> queryTableInfo();

    void addData(List<Map<String, String>> mapList);
}
