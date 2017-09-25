package com.sync.dao.impl;

import com.sync.dao.TableInfoDao;
import com.sync.pojo.CategoryBean;
import com.sync.pojo.CountResultBean;
import com.sync.pojo.LogInfo;
import com.sync.pojo.WorkOrderBean;
import org.apache.log4j.Logger;
import sun.rmi.server.InactiveGroupException;
import util.*;

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
     * 取出分析数据并入库
     */
    public void dataGetAndAnalyze() {
        //查询分类信息
        List<CategoryBean> categoryBeans = queryTableInfoCategory();
        String modelString = TextAnalyze.getModelStr(categoryBeans);
        //工单信息和相关表的信息
        List<WorkOrderBean> workOrderBeans = queryTableInfoWorkorder();
        TextAnalyze.categoryAnalyze(modelString, workOrderBeans, categoryBeans);
        //统计的每个地区的工单条数
        Map<String, Integer> countMap = queryTableInfoCount();

        //最后结果json
        Map<String, Object> countResultMap = new HashMap<>();
        Map<String, Map> countyCuntMapTemp = new HashMap<>();
        Map<String, Object> countyCuntMap = new HashMap<>();





        List<CountResultBean> beanList = new ArrayList<>();

        CountResultBean countResultBean = new CountResultBean();
//        countResultBean.setSJZ();


    }

    /**
     * 查询数据(原始库表)(旧同步分析数据方案所使用的方法)
     */
    public List<WorkOrderBean> queryTableInfo() {
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
        String sql = "SELECT * FROM category WHERE roleWord IS NOT NULL";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setId(String.valueOf(rs.getObject("id")));
                categoryBean.setName(String.valueOf(rs.getObject("name")));
                categoryBean.setRoleWord(String.valueOf(rs.getObject("roleWord")));
                categoryBean.setAlias(String.valueOf(rs.getObject("alias")));
                beanList.add(categoryBean);
            }
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfoCategory() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return beanList;
    }

    /**
     * 查询数据(workorder 工单表及相关联表)
     */
    public List<WorkOrderBean> queryTableInfoWorkorder() {

        List<WorkOrderBean> beanList = new ArrayList<WorkOrderBean>();

        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT w.id, wc.content_a, wc.content_q, c.alias, c.id cid FROM ((workordercontent wc LEFT JOIN workorder w ON w.id = wc.workOrderId)" +
                "LEFT JOIN t_c_users tcu ON w.phoneNum = tcu.DEVICE_NUMBER)" +
                "LEFT JOIN county c ON tcu.CITY_NO = c.id";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                WorkOrderBean workOrderBean = new WorkOrderBean();
                workOrderBean.setId(String.valueOf(rs.getObject("id")));
                workOrderBean.setTextContent(String.valueOf(rs.getObject("content_a"))+String.valueOf(rs.getObject("content_q")));
                workOrderBean.setCountyAlias(String.valueOf(rs.getObject("alias")));
                workOrderBean.setCid(String.valueOf(rs.getObject("cid")));
                beanList.add(workOrderBean);
            }
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfoWorkorder() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return beanList;
    }

    /**
     * 查询数据(workorder 工单表及相关联表) 统计每个地区的工单数量
     */
    public Map<String, Integer> queryTableInfoCount() {
        Map<String, Integer> countMap = new HashMap<>();

        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT  c.alias, count(w.id) workorderCount FROM ((workordercontent wc LEFT JOIN workorder w ON w.id = wc.workOrderId)" +
                "LEFT JOIN t_c_users tcu ON w.phoneNum = tcu.DEVICE_NUMBER)" +
                "LEFT JOIN county c ON tcu.CITY_NO = c.id group by c.alias";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int TELECOUNT = 0;
            while (rs.next()) {
                String alias = String.valueOf(rs.getObject("alias"));
                Integer workorderCount = Integer.parseInt(rs.getObject("workorderCount").toString());
                if(alias != null && !("").equals(alias)){
                    countMap.put(alias,workorderCount);
                    TELECOUNT = TELECOUNT + workorderCount;
                }
            }
            countMap.put("TELECOUNT", TELECOUNT);
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfoCount() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return countMap;
    }
    /**
     * 添加数据(原始表里添加数据)
     */
    public void addData(List<Map<String, String>> mapList, String table, String fields) {
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
            System.out.println(sql);
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
     * 工单表中添加数据(从内存中关联的同步写完就不用此方法了)
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
                ps.setLong(1, Long.parseLong(CommenUtil.isNumeric(workOrderBean.getSerialNum()) == true ? workOrderBean.getSerialNum() : "0"));
                ps.setString(2, workOrderBean.getCustomerServiceId());
                ps.setTimestamp(3, !"".equals(workOrderBean.getCallTime()) ? TimestampTool.datetime(workOrderBean.getCallTime()) : null);
                ps.setString(4, workOrderBean.getPhoneNum());
                ps.setString(5, workOrderBean.getAcceptLocation());
                ps.setString(6, workOrderBean.getPhoneLocation());
                ps.setString(7, workOrderBean.getKeyword());
                ps.setInt(8, Integer.parseInt(workOrderBean.getEmotion() != null ? workOrderBean.getEmotion() : "0"));
//                ps.setString(9, workOrderBean.getMatchCategory());
                ps.setObject(9, null);
                ps.setString(10, workOrderBean.getSourceAudioPath());
                ps.addBatch();
                if((i != 0 && i%1000 == 0) || i == beanList.size()-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }

        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.addTagartTableData() >>>>>>", e);
        } finally {
            dbConnection.close(ps, null, null, conn);
        }

    }

    /**
     * 添加到统计结果表
     * @param beanList
     */
    public void addCountresult(List<CountResultBean> beanList) {

        PreparedStatement ps = null;
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        try {
            conn.setAutoCommit(false);
            String sql = "insert into countresult(date,SJZ,BD, HD, CZ, XT, ZJK,TS, LF, CD, HS, QHD) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < beanList.size(); i++) {
                CountResultBean countResultBean = beanList.get(i);
                ps.setObject(1, TimestampTool.getCurDate());
                ps.setString(2, countResultBean.getSJZ());
                ps.setString(3, countResultBean.getBD());
                ps.setString(4, countResultBean.getHD());
                ps.setString(5, countResultBean.getCZ());
                ps.setString(6, countResultBean.getXT());
                ps.setString(7, countResultBean.getZJK());
                ps.setString(8, countResultBean.getTS());
                ps.setString(9, countResultBean.getLF());
                ps.setString(10, countResultBean.getCD());
                ps.setString(11, countResultBean.getHS());
                ps.setString(12, countResultBean.getQHD());
                ps.addBatch();
                if((i != 0 && i%1000 == 0) || i == beanList.size()-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }

        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.addCountresult() >>>>>>", e);
        } finally {
            dbConnection.close(ps, null, null, conn);
        }
    }

    /**
     * 更新工单表，插入相应的分析字段
     * @param beanList
     */
    public void updateWorkorder(List<WorkOrderBean> beanList) {

        PreparedStatement ps = null;
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        try {
            conn.setAutoCommit(false);
            String sql = "UPDATE workorder SET keyword=?, emotion=?, matchCategory=?, isAnalyze=? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < beanList.size(); i++) {
                WorkOrderBean workOrderBean = beanList.get(i);
                ps.setString(1, workOrderBean.getKeyword());
                ps.setInt(2, Integer.parseInt(workOrderBean.getEmotion()));
                ps.setString(3, workOrderBean.getMatchCategory());
                ps.setInt(4, Integer.parseInt(workOrderBean.getIsAnalyze()));
                ps.setInt(5, Integer.parseInt(workOrderBean.getId()));
             ps.addBatch();
                if((i != 0 && i%1000 == 0) || i == beanList.size()-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }

        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.updateWorkorder() >>>>>>", e);
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
