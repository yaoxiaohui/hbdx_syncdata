package com.sync.dao.impl;

import com.sync.dao.TableInfoDao;
import com.sync.pojo.LogInfo;
import org.apache.log4j.Logger;
import util.DBConnection;
import util.JsonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:09 2017/9/04.
 * @Modified By :
 */
public class TableInfoDaoImpl implements TableInfoDao {

    private static final Logger log = Logger.getLogger(TableInfoDaoImpl.class);

    DBConnection dbConnection = DBConnection.getInstance();

    /**
     * 查询数据
     */
    public List<Map<String, String>> queryTableInfo() {
        String fields1 = DBConnection.TABLEINFO_PROPERTIES.get("fields1").trim();
        String table1 = DBConnection.TABLEINFO_PROPERTIES.get("table1").trim();
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("url"), DBConnection.DB_PROPERTIES.get("username"), DBConnection.DB_PROPERTIES.get("password"));
        PreparedStatement ps = null;
        ResultSet rs = null;
//      String sql = "select " + fields1 + " from " + table1+" limit 100";
        String sql = "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (SELECT * FROM " + table1 + ") A WHERE ROWNUM <= 100 ) WHERE RN >= 1";
        String[] fieldArray = fields1.split(",");
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < fieldArray.length; i++) {
                    map.put(fieldArray[i], String.valueOf(rs.getObject(fieldArray[i])));
                }
                mapList.add(map);
            }
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfo() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return mapList;
    }

    /**
     * 添加数据
     */
    public void addData(List<Map<String, String>> mapList, String table, String fields) {
//        String fields = DBConnection.TABLEINFO_PROPERTIES.get("fields").trim();
//        String table = DBConnection.TABLEINFO_PROPERTIES.get("table").trim();

        PreparedStatement ps = null;
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        try {
            String[] fieldArray = fields.split(",");
            StringBuffer temp = new StringBuffer();
            for (int i = 0; i < fieldArray.length; i++) {
                temp.append("?");
                if(i != fieldArray.length-1){
                    temp.append(",");
                }
            }
            conn.setAutoCommit(false);
            String sql = "insert into " + table + " (" + fields + ") values("+temp+")";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < mapList.size(); i++) {
                Map<String, String> map = mapList.get(i);
                for (int j = 0; j < fieldArray.length; j++) {
                    ps.setString(j + 1, map.get(fieldArray[j]));
                }
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            //把数据插入日志表
            String jsonStr = JsonUtil.objectToJson(mapList);
            LogInfoDaoImpl logInfoDao = new LogInfoDaoImpl();
            LogInfo logInfo = new LogInfo();
            logInfo.setContent(jsonStr);
            logInfoDao.addData(logInfo);
            log.error("TableInfoDaoImpl.addData() >>>>>>", e);
        } finally {
            dbConnection.close(ps, null, null, conn);
        }
    }
}
