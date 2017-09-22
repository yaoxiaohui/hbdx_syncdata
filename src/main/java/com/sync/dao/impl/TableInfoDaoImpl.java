package com.sync.dao.impl;

import com.sync.dao.TableInfoDao;
import com.sync.pojo.CategoryBean;
import com.sync.pojo.LogInfo;
import com.sync.pojo.WorkOrderBean;
import org.apache.log4j.Logger;
import util.DBConnection;
import util.JsonUtil;

import java.sql.*;
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
     * 查询数据(原始库表)
     */
    public List<WorkOrderBean> queryTableInfo() {
//        String fields1 = DBConnection.TABLEINFO_PROPERTIES.get("fields1").trim();
//        String table1 = DBConnection.TABLEINFO_PROPERTIES.get("table1").trim();
        List<WorkOrderBean> beanList = new ArrayList<WorkOrderBean>();

        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sr.CALLERNO, sr.ACCEPTTIME, sr.CONTACTID, sr.SUBSCITY, sr.ACCEPTCITY, cct.STAFFID, t.FILENAME, cct.CONTACTDURATION" +
                " FROM (TRECORDINFO9 t LEFT JOIN T_CCT_CONTACTDETAIL cct ON cct.CALLID = t.CALLID)" +
                " LEFT JOIN T_SR_SERVICEREQUEST sr ON sr.CONTACTID = cct.CONTACTID";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkOrderBean workOrderBean = new WorkOrderBean();
                workOrderBean.setSerialNum(String.valueOf(rs.getObject("CONTACTID")));//流水号
                workOrderBean.setCustomerServiceId(String.valueOf(rs.getObject("STAFFID")));//受理员工号
                workOrderBean.setCallTime(String.valueOf(rs.getObject("ACCEPTTIME")));//来电时间
                workOrderBean.setPhoneNum(String.valueOf(rs.getObject("CALLERNO")));//来电号码
                workOrderBean.setSourceAudioPath(String.valueOf(rs.getObject("FILENAME")));//语音路径(华为方同步过来的)
                workOrderBean.setAcceptLocation(String.valueOf(rs.getObject("ACCEPTCITY")));//受理地
                workOrderBean.setPhoneLocation(String.valueOf(rs.getObject("SUBSCITY")));//来电归属地
                beanList.add(workOrderBean);
            }
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfo() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return beanList;
    }

    /**
     * 查询数据(category分类表)
     */
    public List<CategoryBean> queryTableInfoCategory() {

        List<CategoryBean> beanList = new ArrayList<CategoryBean>();

        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT sr.CALLERNO, sr.ACCEPTTIME, sr.CONTACTID, sr.SUBSCITY, sr.ACCEPTCITY, cct.STAFFID, t.FILENAME, tcu.CITY_NO" +
                " FROM ((T_SR_SERVICEREQUEST sr LEFT JOIN T_CCT_CONTACTDETAIL cct ON sr.CONTACTID = cct.CONTACTID)" +
                " LEFT JOIN TRECORDINFO9 t ON cct.CALLID = t.CALLID)" +
                " LEFT JOIN t_c_users tcu ON sr.CALLERNO = tcu.DEVICE_NUMBER";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               /* CategoryBean categoryBean = new CategoryBean();
                workOrderBean.setSerialNum(String.valueOf(rs.getObject("CONTACTID")));//流水号
                workOrderBean.setCustomerServiceId(String.valueOf(rs.getObject("STAFFID")));//受理员工号
                workOrderBean.setCallTime(String.valueOf(rs.getObject("ACCEPTTIME")));//来电时间
                workOrderBean.setPhoneNum(String.valueOf(rs.getObject("CALLERNO")));//来电号码
                workOrderBean.setAudioPath(String.valueOf(rs.getObject("FILENAME")));//语音路径
                workOrderBean.setAcceptLocation(String.valueOf(rs.getObject("ACCEPTCITY")));//受理地
                workOrderBean.setPhoneLocation(String.valueOf(rs.getObject("SUBSCITY")));//来电归属地
                beanList.add(CategoryBean);*/
            }
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfoCategory() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return beanList;
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

    /**
     * 工单表中添加数据
     * @param beanList
     */
    @Override
    public void addTagartTableData(List<WorkOrderBean> beanList) {

        PreparedStatement ps = null;
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        try {
            conn.setAutoCommit(false);
            String sql = "insert into workorder (serialNum, customerServiceId, callTime, phoneNum," +
                    "acceptLocation, phoneLocation, keyword, emotion, matchCategory, sourceAudioPath) values(?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < beanList.size(); i++) {
                WorkOrderBean workOrderBean = beanList.get(i);
                ps.setInt(1, Integer.parseInt(workOrderBean.getSerialNum() != null ? workOrderBean.getSerialNum() : "0"));
                ps.setString(2, workOrderBean.getCustomerServiceId());
                ps.setTimestamp(3, new Timestamp(Long.parseLong(workOrderBean.getCallTime() != null ? workOrderBean.getCallTime() : "0")));
                ps.setString(4, workOrderBean.getPhoneNum());
                ps.setString(5, workOrderBean.getAcceptLocation());
                ps.setString(6, workOrderBean.getPhoneLocation());
                ps.setString(7, workOrderBean.getKeyword());
                ps.setInt(8, Integer.parseInt(workOrderBean.getEmotion() != null ? workOrderBean.getEmotion() : "0"));
                ps.setString(9, workOrderBean.getMatchCategory());
                ps.setString(10, workOrderBean.getSourceAudioPath());
                ps.addBatch();
                if(i%1000 == 0 || i == beanList.size()-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }

        } catch (SQLException e) {
           /* //把数据插入日志表
            String jsonStr = JsonUtil.objectToJson(mapList);
            LogInfoDaoImpl logInfoDao = new LogInfoDaoImpl();
            LogInfo logInfo = new LogInfo();
            logInfo.setContent(jsonStr);
            logInfoDao.addData(logInfo);
            log.error("TableInfoDaoImpl.addData() >>>>>>", e);*/
        } finally {
            dbConnection.close(ps, null, null, conn);
        }

    }
}
