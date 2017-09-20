package util;

import com.sync.control.TargetTableInfoSyncController;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 文件处理
 *
 * @author ZHXG
 * 2017/9/04 上午9:40:17
 */
public class FileProcess {

    private static final Logger log = Logger.getLogger(FileProcess.class);
    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static List<Map<String, String>> readFile(String filePath, String[] tableField) {

        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        try {
            File file = new File(filePath);
            if(file.exists()){
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    String[] fieldValue = line.split("\\|");
                    Map<String, String> map = new HashMap<String, String>();
                    for (int i = 0; i < tableField.length; i++) {
                        if(tableField.length != fieldValue.length){
                            continue;
                        }
                        map.put(tableField[i], fieldValue[i]);
                    }
                    mapList.add(map);
                }
            }else{
                log.info("此路径下的文件不存在>>>>>>>>>>>>>>>>>>>>"+filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapList;
    }
    /**
     * 字符串数组转以逗号为分割的字符串
     *
     * @param strArray
     * @return
     */
    public static String arrayToString(String[] strArray){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < strArray.length; i++){
            if(i == strArray.length-1){
                stringBuffer. append(strArray[i]);
            }else{
                stringBuffer. append(strArray[i]+",");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 部分中文标点转英文
     *
     * @param content
     * @return
     */
    public static String replacePunctuation(String content) {
        String[] regs = {" ", "，", "。", "；", " ", ",", ".", ";"};
        for (int i = 0; i < regs.length / 2; i++) {
            content = content.replaceAll(regs[i], regs[i + regs.length / 2]);
        }
        return content;
    }

    /**
     * 获得out
     *
     * @param response
     * @return
     */
    public static PrintWriter getOut(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * 替换特殊符号为""
     *
     * @param str
     * @return
     */
    public static String replaceAll(String str) {
        return str.replaceAll("[\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥×‘’“”＃，。；、　％]", "");
    }

    /**
     * 读取资源文件
     *
     * @param resource
     * @return
     */
    public static HashMap<String, String> readProperties(String resource) {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        // 读取文件到Stream流
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path + resource));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        Properties pp = new Properties();
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            pp.load(is);
            Set<Object> set = pp.keySet();
            for (Object o : set) {
                map.put(o.toString(), new String(pp.get(o).toString().getBytes("ISO-8859-1"), "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除文件或者文件夹
     *
     * @param sPath
     * @return
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFiles(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    private static boolean deleteFiles(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    private static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
