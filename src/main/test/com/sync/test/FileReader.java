package com.sync.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static void main(String[] args){
        String pathname1 = "D:\\handle\\bxltcqy.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        List<String> stringList1 = readFile(pathname1);
        String pathname2 = "D:\\handle\\zjxy.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        List<String> stringList2 = readFile(pathname2);
        String pathname3 = "D:\\handle\\LIST.TXT"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        List<String> stringList3 = readFile(pathname3);

        StringBuffer voicePathContent1 = new StringBuffer();
        for (String  temp : stringList1) {
            voicePathContent1.append(temp+".wav,");
        }
        StringBuffer voicePathContent2 = new StringBuffer();
        for (String  temp : stringList2) {
            voicePathContent2.append(temp+".wav,");
        }

        StringBuffer resultIdContent1 = new StringBuffer();//不限量套餐迁转的（语音id结果）
        StringBuffer resultIdContent2 = new StringBuffer();//租机续约（语音id结果）
        for (int i = 0; i < stringList3.size(); i++) {
            String voiceFileName = stringList3.get(i);
            if(voicePathContent1.toString().contains(voiceFileName)){
                int tempId = i+1;
                resultIdContent1.append("\""+tempId+"\",");// (i+1)是id
            }else if(voicePathContent2.toString().contains(voiceFileName)){
                int tempId = i+1;
                resultIdContent2.append("\""+tempId+"\",");// (i+1)是id
            }

        }
        writeFile(resultIdContent1.toString(), "D:\\handle\\result_id_bxltcqy.txt");
        writeFile(resultIdContent2.toString(), "D:\\handle\\result_id_zjxy.txt");



        StringBuffer resultContent1 = new StringBuffer();//不限量套餐迁转的（语音文本结果）
        StringBuffer resultContent2 = new StringBuffer();//租机续约（语音文本结果）
        String pathname4 = "D:\\handle\\workordercontent.csv"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        List<String> stringList4 = readFile(pathname4);
        for (int i = 0; i < stringList4.size(); i++) {
            String temp = stringList4.get(i);
            if(temp != null && !"".equals(temp)){
                String tempresult = temp.split(",")[0];
                if ((resultIdContent1.toString()).contains(tempresult)){
                    resultContent1.append(temp+"\n");
                }else if ((resultIdContent2.toString()).contains(tempresult)){
                    resultContent2.append(temp+"\n");
                }
            }
        }
        writeFile(resultContent1.toString(), "D:\\handle\\result_bxltcqy.txt");
        writeFile(resultContent2.toString(), "D:\\handle\\result_zjxy.txt");

    }
    /**
     * 读取txt文件的内容
     * @param pathname 想要读取的文件对象
     * @return 返回文件内容
     */
    public static List<String> readFile(String pathname){
        List<String> stringList = new ArrayList<>();
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 读取TXT文件 */
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line;
            while ((line = br.readLine())!= null) {
                stringList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }

    /**
     * 读取txt文件的内容
     * @param fileContent,filePath 想要读取的文件对象
     * @return 返回文件内容
     */
    public static List<String> writeFile(String fileContent, String filePath){
        List<String> stringList = new ArrayList<>();
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 写入Txt文件 */
            File writename = new File(filePath); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(fileContent); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
