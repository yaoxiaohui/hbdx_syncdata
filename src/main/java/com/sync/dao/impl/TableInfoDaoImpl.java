package com.sync.dao.impl;

import com.sync.dao.TableInfoDao;
import com.sync.pojo.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
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

    /*public static void main(String[] args) {
        TableInfoDaoImpl tableInfoDao = new TableInfoDaoImpl();
        tableInfoDao.dataGetAndAnalyze();
    }*/

    /**
     * 取出分析数据并入库
     */
    public void dataGetAndAnalyze() {
        //查询分类信息
        List<CategoryBean> categoryBeans = queryTableInfoCategory();
        String modelString = TextAnalyze.getModelStr(categoryBeans);
        //工单信息和相关表的信息
        List<WorkOrderBean> workOrderBeans = queryTableInfoWorkorder();
        int[][] resultArray = TextAnalyze.categoryAnalyze(modelString, workOrderBeans, categoryBeans);

        //统计的每个地区的工单条数
        Map<String, Integer> countMap = queryTableInfoCount();

        //各个市
        JSONObject CZjsonObject = new JSONObject();
        JSONObject TSjsonObject = new JSONObject();
        JSONObject QHDjsonObject = new JSONObject();
        JSONObject LFjsonObject = new JSONObject();
        JSONObject ZJKjsonObject = new JSONObject();
        JSONObject XTjsonObject = new JSONObject();
        JSONObject HDjsonObject = new JSONObject();
        JSONObject BDjsonObject = new JSONObject();
        JSONObject SJZjsonObject = new JSONObject();
        JSONObject CDjsonObject = new JSONObject();
        JSONObject HSjsonObject = new JSONObject();
        //市下的区县集合
        JSONObject CZcountyJsonObjectTempArea = new JSONObject();
        JSONObject TScountyJsonObjectTempArea = new JSONObject();
        JSONObject QHDcountyJsonObjectTempArea = new JSONObject();
        JSONObject LFcountyJsonObjectTempArea = new JSONObject();
        JSONObject ZJKcountyJsonObjectTempArea = new JSONObject();
        JSONObject XTcountyJsonObjectTempArea = new JSONObject();
        JSONObject HDcountyJsonObjectTempArea = new JSONObject();
        JSONObject BDcountyJsonObjectTempArea = new JSONObject();
        JSONObject SJZcountyJsonObjectTempArea = new JSONObject();
        JSONObject CDcountyJsonObjectTempArea = new JSONObject();
        JSONObject HScountyJsonObjectTempArea = new JSONObject();
        int CZCount = 0;
        int TSCount = 0;
        int QHDCount = 0;
        int LFCount = 0;
        int ZJKCount = 0;
        int XTCount = 0;
        int HDCount = 0;
        int BDCount = 0;
        int SJZCount = 0;
        int CDCount = 0;
        int HSCount = 0;
        int CZTELECOUNTCount = 0;
        int TSTELECOUNTCount = 0;
        int QHDTELECOUNTCount = 0;
        int LFTELECOUNTCount = 0;
        int ZJKTELECOUNTCount = 0;
        int XTTELECOUNTCount = 0;
        int HDTELECOUNTCount = 0;
        int BDTELECOUNTCount = 0;
        int SJZTELECOUNTCount = 0;
        int CDTELECOUNTCount = 0;
        int HSTELECOUNTCount = 0;
        for (int i = 1; i < resultArray.length; i++) {
            //各个父分类
            JSONObject fatherJsonObject = new JSONObject();
//            JSONObject CFLHjsonObject = new JSONObject();
//            JSONObject JZjsonObject = new JSONObject();
//            JSONObject XFXYjsonObject = new JSONObject();
//            JSONObject HJHKHGXjsonObject = new JSONObject();
//            JSONObject TCQYjsonObject = new JSONObject();
//            JSONObject XSHDjsonObject = new JSONObject();
//            JSONObject WTWJjsonObject = new JSONObject();
            //各个父分类 统计
            JSONObject XZjsonObjectCount = new JSONObject();
            JSONObject CFLHjsonObjectCount = new JSONObject();
            JSONObject JZjsonObjectCount = new JSONObject();
            JSONObject XFXYjsonObjectCount = new JSONObject();
            JSONObject HJHKHGXjsonObjectCount = new JSONObject();
            JSONObject TCQYjsonObjectCount = new JSONObject();
            JSONObject XSHDjsonObjectCount = new JSONObject();
            JSONObject WTWJjsonObjectCount = new JSONObject();
            //各个子分类
            JSONObject SONXZjsonObject = new JSONObject();
            JSONObject SONCFLHjsonObject = new JSONObject();
            JSONObject SONJZjsonObject = new JSONObject();
            JSONObject SONXFXYjsonObject = new JSONObject();
            JSONObject SONHJHKHGXjsonObject = new JSONObject();
            JSONObject SONTCQYjsonObject = new JSONObject();
            JSONObject SONXSHDjsonObject = new JSONObject();
            JSONObject SONWTWJjsonObject = new JSONObject();
            //各个分类数量统计
            int XZcategoryCount = 0;
            int CFcategoryCount = 0;
            int JZcategoryCount = 0;
            int XFXYcategoryCount = 0;
            int HJHKHGXcategoryCount = 0;
            int TCQYcategoryCount = 0;
            int XSHDcategoryCount = 0;
            int WTWJcategoryCount = 0;
            for (int j = 1; j < resultArray[i].length; j++) {
                String categorCode = CategoryMapping.getAliasByGivenIndex(String.valueOf(j));
                if(categorCode != null && categorCode.contains("XZ-")){
                    SONXZjsonObject.put(categorCode, resultArray[i][j]);
                    XZcategoryCount = XZcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("CFLH-")){
                    SONCFLHjsonObject.put(categorCode, resultArray[i][j]);
                    CFcategoryCount = CFcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("JZ-")){
                    SONJZjsonObject.put(categorCode, resultArray[i][j]);
                    JZcategoryCount = JZcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("XFXY-")){
                    SONXFXYjsonObject.put(categorCode, resultArray[i][j]);
                    XFXYcategoryCount = XFXYcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("HJHKHGX-")){
                    SONHJHKHGXjsonObject.put(categorCode, resultArray[i][j]);
                    HJHKHGXcategoryCount = HJHKHGXcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("TCQY-")){
                    SONTCQYjsonObject.put(categorCode, resultArray[i][j]);
                    TCQYcategoryCount = TCQYcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("XSHD-")){
                    SONXSHDjsonObject.put(categorCode, resultArray[i][j]);
                    XSHDcategoryCount = XSHDcategoryCount + resultArray[i][j];
                }else if(categorCode != null && categorCode.contains("WTWJ-")){
                    SONWTWJjsonObject.put(categorCode, resultArray[i][j]);
                    WTWJcategoryCount = WTWJcategoryCount + resultArray[i][j];
                }

            }

            XZjsonObjectCount.put("SON", SONXZjsonObject);
            XZjsonObjectCount.put("COUNT", XZcategoryCount);
            fatherJsonObject.put("XZ", XZjsonObjectCount);

            CFLHjsonObjectCount.put("SON", SONCFLHjsonObject);
            CFLHjsonObjectCount.put("COUNT", CFcategoryCount);
            fatherJsonObject.put("CFLH", CFLHjsonObjectCount);

            JZjsonObjectCount.put("SON", SONJZjsonObject);
            JZjsonObjectCount.put("COUNT", JZcategoryCount);
            fatherJsonObject.put("JZ", JZjsonObjectCount);

            XFXYjsonObjectCount.put("SON", SONXFXYjsonObject);
            XFXYjsonObjectCount.put("COUNT", XFXYcategoryCount);
            fatherJsonObject.put("XFXY", XFXYjsonObjectCount);

            HJHKHGXjsonObjectCount.put("SON", SONHJHKHGXjsonObject);
            HJHKHGXjsonObjectCount.put("COUNT", HJHKHGXcategoryCount);
            fatherJsonObject.put("HJHKHGX", HJHKHGXjsonObjectCount);

            TCQYjsonObjectCount.put("SON", SONTCQYjsonObject);
            TCQYjsonObjectCount.put("COUNT", TCQYcategoryCount);
            fatherJsonObject.put("TCQY", TCQYjsonObjectCount);

            XSHDjsonObjectCount.put("SON", SONXSHDjsonObject);
            XSHDjsonObjectCount.put("COUNT", XSHDcategoryCount);
            fatherJsonObject.put("XSHD", XSHDjsonObjectCount);

            WTWJjsonObjectCount.put("SON", SONWTWJjsonObject);
            WTWJjsonObjectCount.put("COUNT", WTWJcategoryCount);
            fatherJsonObject.put("WTWJ", WTWJjsonObjectCount);

            //区县的统计结构
            JSONObject countyJsonObject = new JSONObject();

            String countyAlias = CountyMapping.getAliasByGivenIndex(String.valueOf(i));
            int countTemp = XZcategoryCount+CFcategoryCount+JZcategoryCount
                    +XFXYcategoryCount+HJHKHGXcategoryCount+TCQYcategoryCount+XSHDcategoryCount+WTWJcategoryCount;
            countyJsonObject.put("COUNT", countTemp);
            countyJsonObject.put("TELECOUNT", countMap.get(countyAlias));
            countyJsonObject.put("CATEGORY", fatherJsonObject);

            if(countyAlias != null && countyAlias.contains("CZ-")){
                CZcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                CZCount = CZCount + countTemp;
                CZTELECOUNTCount = CZTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("TS-")){
                TScountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                TSCount = TSCount + countTemp;
                TSTELECOUNTCount = TSTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("QHD-")){
                QHDcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                QHDCount = QHDCount + countTemp;
                QHDTELECOUNTCount = QHDTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("LF-")){
                LFcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                LFCount = LFCount + countTemp;
                LFTELECOUNTCount = LFTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("ZJK-")){
                ZJKcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                ZJKCount = ZJKCount + countTemp;
                ZJKTELECOUNTCount = ZJKTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("XT-")){
                XTcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                XTCount = XTCount + countTemp;
                XTTELECOUNTCount = XTTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("HD-")){
                HDcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                HDCount = HDCount + countTemp;
                HDTELECOUNTCount = HDTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("BD-")){
                BDcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                BDCount = BDCount + countTemp;
                BDTELECOUNTCount = BDTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            } else if(countyAlias != null && countyAlias.contains("SJZ-")){
                SJZcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                SJZCount = SJZCount + countTemp;
                SJZTELECOUNTCount = SJZTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("CD-")){
                CDcountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                CDCount = CDCount + countTemp;
                CDTELECOUNTCount = CDTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }else if(countyAlias != null && countyAlias.contains("HS-")){
                HScountyJsonObjectTempArea.put(countyAlias, countyJsonObject);
                HSCount = HSCount + countTemp;
                HSTELECOUNTCount = HSTELECOUNTCount + (StringUtils.isEmpty(countMap.get(countyAlias))? 0 : countMap.get(countyAlias));
            }

        }
        SJZjsonObject.put("COUNT", SJZCount);
        SJZjsonObject.put("TELECOUNT", SJZTELECOUNTCount);
        SJZjsonObject.put("COUNTY", SJZcountyJsonObjectTempArea);

        BDjsonObject.put("COUNT", BDCount);
        BDjsonObject.put("TELECOUNT", BDTELECOUNTCount);
        BDjsonObject.put("COUNTY", BDcountyJsonObjectTempArea);

        CDjsonObject.put("COUNT", CDCount);
        CDjsonObject.put("TELECOUNT", CDTELECOUNTCount);
        CDjsonObject.put("COUNTY", CDcountyJsonObjectTempArea);

        CZjsonObject.put("COUNT", CZCount);
        CZjsonObject.put("TELECOUNT", CZTELECOUNTCount);
        CZjsonObject.put("COUNTY", CZcountyJsonObjectTempArea);

        HDjsonObject.put("COUNT", HDCount);
        HDjsonObject.put("TELECOUNT", HDTELECOUNTCount);
        HDjsonObject.put("COUNTY", HDcountyJsonObjectTempArea);

        HSjsonObject.put("COUNT", HSCount);
        HSjsonObject.put("TELECOUNT", HSTELECOUNTCount);
        HSjsonObject.put("COUNTY", HScountyJsonObjectTempArea);

        LFjsonObject.put("COUNT", LFCount);
        LFjsonObject.put("TELECOUNT", LFTELECOUNTCount);
        LFjsonObject.put("COUNTY", LFcountyJsonObjectTempArea);

        QHDjsonObject.put("COUNT", QHDCount);
        QHDjsonObject.put("TELECOUNT", QHDTELECOUNTCount);
        QHDjsonObject.put("COUNTY", QHDcountyJsonObjectTempArea);

        TSjsonObject.put("COUNT", TSCount);
        TSjsonObject.put("TELECOUNT", TSTELECOUNTCount);
        TSjsonObject.put("COUNTY", TScountyJsonObjectTempArea);

        ZJKjsonObject.put("COUNT", ZJKCount);
        ZJKjsonObject.put("TELECOUNT", ZJKTELECOUNTCount);
        ZJKjsonObject.put("COUNTY", ZJKcountyJsonObjectTempArea);

        XTjsonObject.put("COUNT", XTCount);
        XTjsonObject.put("TELECOUNT", XTTELECOUNTCount);
        XTjsonObject.put("COUNTY", XTcountyJsonObjectTempArea);

        //插入分析结果 向countresult表
        CountResultBean countResultBean = new CountResultBean();
        countResultBean.setSJZ(SJZjsonObject != null ? SJZjsonObject.toString() : null);
        countResultBean.setBD(BDjsonObject != null ? BDjsonObject.toString() : null);
        countResultBean.setCD(CDjsonObject != null ? CDjsonObject.toString() : null);
        countResultBean.setCZ(CZjsonObject != null ? CZjsonObject.toString() : null);
        countResultBean.setHD(HDjsonObject != null ? HDjsonObject.toString() : null);
        countResultBean.setHS(HSjsonObject != null ? HSjsonObject.toString() : null);
        countResultBean.setLF(LFjsonObject != null ? LFjsonObject.toString() : null);
        countResultBean.setQHD(QHDjsonObject != null ? QHDjsonObject.toString() : null);
        countResultBean.setTS(TSjsonObject != null ? TSjsonObject.toString() : null);
        countResultBean.setZJK(ZJKjsonObject != null ? ZJKjsonObject.toString() : null);
        countResultBean.setXT(XTjsonObject != null ? XTjsonObject.toString() : null);
        addCountresult(countResultBean);
        //更新工单表 插入分析结果
        updateWorkorder(workOrderBeans);
    }

    /**
     * 查询数据(原始库表)(旧同步分析数据方案所使用的方法)
     */
    public List<WorkOrderBean> queryTableInfo() {
        List<WorkOrderBean> beanList = new ArrayList<>();

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
        String sql = "SELECT * FROM category WHERE roleWord IS NOT NULL AND level=2";
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
     * 查询数据(地区表county)
     */
    public Map<Integer, String> queryTableInfoCounty() {
        Map<Integer, String> map = new HashMap<>();
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM county WHERE level = 2";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                map.put(Integer.parseInt(rs.getObject("id").toString()), String.valueOf(rs.getObject("alias")));
            }
        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.queryTableInfoCounty() >>>>>>", e);
        } finally {
            dbConnection.close(ps, rs, null, conn);
        }
        return map;
    }

    /**
     * 查询数据(workorder 工单表及相关联表)
     */
    public List<WorkOrderBean> queryTableInfoWorkorder() {

        List<WorkOrderBean> beanList = new ArrayList<WorkOrderBean>();

        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT w.id, wc.content_a, wc.content_q, c.alias, c.id cid FROM ((workordercontent wc " +
                "JOIN workorder w ON w.id = wc.workOrderId AND wc.content_a IS NOT NULL) " +
                "JOIN t_c_users tcu ON w.phoneNum = tcu.DEVICE_NUMBER) JOIN county c ON tcu.CITY_NO = c.id AND c.alias IS NOT NULL";
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
     * @param countResultBean
     */
    public void addCountresult(CountResultBean countResultBean) {

        PreparedStatement ps = null;
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        try {
            String sql = "insert into countresult(date,SJZ,BD, HD, CZ, XT, ZJK,TS, LF, CD, HS, QHD) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);

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
            ps.execute();

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
            String sql = "UPDATE workorder SET keyword=?, emotion=?, matchCategory=?, isAnalyze=?, phoneLocation=? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < beanList.size(); i++) {
                WorkOrderBean workOrderBean = beanList.get(i);
                ps.setString(1, workOrderBean.getKeyword());
                ps.setInt(2, Integer.parseInt(StringUtils.isEmpty(workOrderBean.getEmotion()) ? "0" : workOrderBean.getEmotion()));
                ps.setString(3, workOrderBean.getMatchCategory());
                ps.setInt(4, 1);//是否分析(0:未分析,1.分析)
                ps.setString(5, workOrderBean.getCid());//county表中的id
                ps.setInt(6, Integer.parseInt(workOrderBean.getId()));
                ps.addBatch();
                if((i != 0 && i%1000 == 0) || i == beanList.size()-1){
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }
            }

        } catch (SQLException e) {
            log.error("TableInfoDaoImpl.updateWorkorder() >>>>>>", e);
        } finally {
            dbConnection.close(ps, null, null, conn);
        }
    }
}
