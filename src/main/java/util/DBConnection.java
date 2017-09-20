package util;

import java.sql.*;
import java.util.HashMap;
/**
 * @Author ljw
 * @Description
 * @Date Created in 10:53 2017/9/04.
 * @Modified By:
 */
public class DBConnection {

    private volatile static DBConnection dbConnection = null;

    // 同步信息文件
    public static final HashMap<String, String> CONFIG_PROPERTIES = FileProcess.readProperties("/config.properties");
    // 资源文件
    public static final HashMap<String, String> DB_PROPERTIES = FileProcess.readProperties("/dbconfig.properties");
    //表信息文件
    public static final HashMap<String, String> TABLEINFO_PROPERTIES = FileProcess.readProperties("/table_info_config.properties");

    static {
        try {
//          Class.forName(DB_PROPERTIES.get("driver"));
            Class.forName(DB_PROPERTIES.get("localdriver"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    /**
     * 设置成为单例，是为了防止多线程调用时候锁库
     * @return
     */
    public static DBConnection getInstance(){
        if(dbConnection == null){
            synchronized (DBConnection.class) {
                if(dbConnection == null){
                    dbConnection = new DBConnection();
                }
            }
        }
        return dbConnection;
    }
    /**
     * 获得连接
     */
    public synchronized Connection getConnection(String url, String username, String password){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 释放对象
     */
    public synchronized void close(PreparedStatement ps,ResultSet rs,Statement statement,Connection connection){
        if(rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                rs = null;
            }
        }
        if(statement!=null)
        {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                statement = null;
            }
        }

        if(ps!=null)
        {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                ps = null;
            }
        }

        if(connection!=null)
        {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally{
                connection = null;
            }
        }
    }
}
