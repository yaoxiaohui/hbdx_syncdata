package com.sync.test;

import java.io.*;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 15:24 2017/4/5.
 * @Modified By :
 */
public class DemoTest {
    //    public static void main(String[] args){
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url = "jdbc:oracle:thin:@172.16.254.206:1521:orcl";
//        String userName = "HBTELE";
//        String password = "HBTELE";
//
//        try {
//            Connection conn = DriverManager.getConnection(url, userName, password);
//            Statement stmt = conn.createStatement();
//            ResultSet res = stmt.executeQuery("select * from TEL_RECORD_INFO_1");
//            while (res.next()){
//                System.out.println(res.getString(1));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//      }
    public static void main(String[] args) throws IOException, InterruptedException {
        String cmd = "cmd /c dir c:\\windows";
        final Process process = Runtime.getRuntime().exec(cmd);
        printMessage(process.getInputStream());
        printMessage(process.getErrorStream());
        int value = process.waitFor();
        System.out.println(value);
    }
    private static void printMessage(final InputStream input) {
        new Thread(new Runnable() {
            public void run() {
                Reader reader = new InputStreamReader(input);
                BufferedReader bf = new BufferedReader(reader);
                String line = null;
                try {
                    while ((line = bf.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
