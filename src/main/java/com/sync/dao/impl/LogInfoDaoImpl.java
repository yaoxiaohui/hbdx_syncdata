package com.sync.dao.impl;

import com.sync.dao.LogInfoDao;
import com.sync.pojo.LogInfo;
import org.apache.log4j.Logger;
import util.DBConnection;
import util.TimestampTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author ljw
 * @Description
 * @Date Created in 10:45 2017/4/5.
 * @Modified By:
 */
public class LogInfoDaoImpl implements LogInfoDao {

    private static final Logger log = Logger.getLogger(LogInfoDaoImpl.class);

    public void addData(LogInfo logInfo) {
        DBConnection dbConnection = DBConnection.getInstance();
        Connection conn = dbConnection.getConnection(DBConnection.DB_PROPERTIES.get("localurl"), DBConnection.DB_PROPERTIES.get("localusername"), DBConnection.DB_PROPERTIES.get("localpassword"));
        PreparedStatement ps = null;
        String sql = "insert into t_log_info (content,create_time) values(?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, logInfo.getContent() != null ? logInfo.getContent() : "");
            ps.setString(2, TimestampTool.getCurrentDateTime());
            ps.execute();
        } catch (SQLException e) {
            log.error("LogInfoDaoImpl.addData() >>>>>>", e);
        } finally {
            dbConnection.close(ps, null, null, conn);
        }
    }

}

