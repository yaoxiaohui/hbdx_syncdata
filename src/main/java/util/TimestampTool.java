package util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 * @author ZHXG
 */
public class TimestampTool {

    /**
     * 当前时间
     */
    public static Timestamp crunttime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取当前时间的字符串  2006-07-07
     */
    public static String getCurrentDate() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 10);
    }

    /**
     * 获取当前时间的字符串  2006-07-07 22:10:10
     */
    public static String getCurrentDateTime() {
        Timestamp d = crunttime();
        return d.toString().substring(0, 19);
    }

    /**
     * 获取给定时间的字符串,只有日期  2006-07-07
     */
    public static String getStrDate(Timestamp t) {
        return t.toString().substring(0, 10);
    }

    /**
     * 获取给定时间的字符串  2006-07-07 22:10:10
     */
    public static String getStrDateTime(Timestamp t) {
        return t.toString().substring(0, 19);
    }

    /**
     * 获得当前日期的前段日期
     */
    public static String getStrIntervalDate(String days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -Integer.parseInt(days));
        String strBeforeDays = sdf.format(cal.getTime());
        return strBeforeDays;
    }

    /**
     * 获得当前日期的前段日期
     */
    public static String getStrIntervalDateHHMMSS(String days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -Integer.parseInt(days));
        String strBeforeDays = sdf.format(cal.getTime());
        return strBeforeDays;
    }

    /**
     * 转换为日期类型
     */
    public static Date getDateType(String dt) {
        Date jDt = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (dt.length() > 10) {
                jDt = sdf.parse(dt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jDt;
    }

    /**
     * 转换为日期类型 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        return date;
    }

    /**
     * 返回日期 格式:2006-07-05
     */
    public static Timestamp date(String str) {
        Timestamp tp = null;
        if (str.length() <= 10) {
            String[] string = str.trim().split("-");
            int one = Integer.parseInt(string[0]) - 1900;
            int two = Integer.parseInt(string[1]) - 1;
            int three = Integer.parseInt(string[2]);
            tp = new Timestamp(one, two, three, 0, 0, 0, 0);
        }
        return tp;
    }

    /**
     * 返回时间和日期  格式:2006-07-05 22:10:10
     */
    public static Timestamp datetime(String str) {
        Timestamp tp = null;
        if (str.length() > 10) {
            String[] string = str.trim().split(" ");
            String[] date = string[0].split("-");
            String[] time = string[1].split(":");
            int date1 = Integer.parseInt(date[0]) - 1900;
            int date2 = Integer.parseInt(date[1]) - 1;
            int date3 = Integer.parseInt(date[2]);
            int time1 = Integer.parseInt(time[0]);
            int time2 = Integer.parseInt(time[1]);
            int time3 = Integer.parseInt(time[2]);
            tp = new Timestamp(date1, date2, date3, time1, time2, time3, 0);
        }
        return tp;
    }

    /**
     * 返回日期和时间(没有秒)  格式:2006-07-05 22:10
     */
    public static Timestamp datetimeHm(String str) {
        Timestamp tp = null;
        if (str.length() > 10) {
            String[] string = str.trim().split(" ");
            String[] date = string[0].split("-");
            String[] time = string[1].split(":");
            int date1 = Integer.parseInt(date[0]) - 1900;
            int date2 = Integer.parseInt(date[1]) - 1;
            int date3 = Integer.parseInt(date[2]);
            int time1 = Integer.parseInt(time[0]);
            int time2 = Integer.parseInt(time[1]);
            tp = new Timestamp(date1, date2, date3, time1, time2, 0, 0);
        }
        return tp;
    }

    /**
     * 获取给定时间的字符串,日期时分秒
     */
    public static String getStringDateNew() {
        Timestamp t = crunttime();
        return t.toString().substring(0, 19).replaceAll("-", "").replaceAll(":", "").replace(" ", "");
    }


}
