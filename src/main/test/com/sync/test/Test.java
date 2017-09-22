package com.sync.test;
import com.sync.pojo.TRECORDINFO9;
import com.sync.pojo.T_CCT_CONTACTDETAIL;
import com.sync.pojo.T_SR_SERVICEREQUEST;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import com.zhxg.doc_classify.runstart.RunClass;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;
import util.FileProcess;
import util.FtpUtil;
import util.TimestampTool;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 15:46 2017/9/8.
 * @Modified By :
 */
public class Test {

    /*public static void main(String[] sad) {
        String modelString = "[{'name':'负面-政府负面-环保问题-水体污染','nice':'环保问题','keys':'变差,变黄,浑浊,异常,水域,河流,河水,湖水,海洋,地下水,自来水,污水,废水,脏水,臭水,红水,黄水,黑水,海域'},{'name':'负面-政府负面-腐败问题-霸占问题','nice':'腐败问题','keys':'路霸侵吞霸占霸市菜霸街霸警霸冒领退税款无端被强征暴力毁占疯狂抢占强占鱼池被无故因开挖提水站占用土地'},{'name':'负面-政府负面-民生问题-土地问题','nice':'民生问题','keys':'强制征地非法占用占用农田倒卖集体土地非法征地数量大\n'}]";
        //D:\\zhxg\\doc  表示日志输出位置  可以为空
        String ds = RunClass.init(modelString, "D:\\zhxg\\doc");
        System.out.println(ds);
        String title = "去市场买菜时，如果老板蹲下去，一定要留心！赶紧看看吧！！";
        String content = "原标题：去市场买菜时，如果老板蹲下去，一定要留心！赶紧看看吧！！" +
                "看了这个视频  真的不能愉快的买菜了！  女商贩一分钟内  竟迅雷不及掩耳盗铃之势  调包了两袋鱼。  " +
                "必是日积月累的实操  方能获此手法之娴熟！    视频没点开的不要紧  请看动图↓  " +
                "第一次，小贩装菜的时候，拿到低处，拿另一袋掉了包    第二次，小贩索性扔掉鱼和袋子，" +
                "直接拎起提前准备好的次品上台    止不住看了好几遍  被这“鬼手”彻底折服  卖菜卖成这样，" +
                "也是成精了。  只是功夫用错了地方，欺骗消费者的事情可不能干！  注意了，买菜时留点心，" +
                "不良商贩太可恶！  多留个心眼，别让无良商贩有机可乘  看文章返回搜狐，查看更多 " +
                "beginimghttp://5b0988e595225.cdn.sohucs.com/images/20170905/23" +
                "312f73208e4c4185d082e4f932ccd7.gifendimg beginimghttp://5b0988e595225.cdn.sohucs.c" +
                "om/images/20170905/54511cf2df3645389d34e5961c915074.gifendimg ";
        String result = result = RunClass.run(title, content);
        System.out.println(result);
    }*/

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        //因为在命令窗口进行mysql数据库的导入一般分三步走，所以所执行的命令将以字符串数组的形式出现
        //根据属性文件的配置获取数据库导入所需的命令，组成一个数组
//        String cmdarray[] = {"\"C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql.exe\" -uroot -proot", "use hbdx",
//                "load data infile \"D:\\TRECORDINFO-201709141530.txt\" replace into table TRECORDINFO9 fields terminated by'|';"};
//        String cmdarray[] = {"\"C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql.exe\" -uroot -proot", "use hbdx",
//        "load data infile \"D:\\CONTACTDETAIL-201709151717.txt\" replace into table T_CCT_CONTACTDETAIL fields terminated by'|';"};

//        String cmdarray[] = {"\"C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql.exe\" -uroot -proot", "use hbdx",
//                "load data infile \"D:\\SERVICEREQUEST-201709141500.txt\" replace into table T_SR_SERVICEREQUEST fields terminated by'|';"};
        String cmdarray[] = {"\"C:/Program Files/MySQL/MySQL Server 5.7/bin/mysql.exe\" -uroot -proot", "use hbdx",
                "load data infile \"D:\\SERVICEREQUEST-201709141500.txt\" replace into table t_c_users fields terminated by'|';"};

        Process process;
        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>start>>>>>>>>>>>>>>>>>>>>>>>>>>");
            process = runtime.exec("cmd /c " + cmdarray[0]);
            //执行了第一条命令以后已经登录到mysql了，所以之后就是利用mysql的命令窗口
            //进程执行后面的代码
            OutputStream os = process.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os);
            //命令1和命令2要放在一起执行
            writer.write(cmdarray[1] + "\r\n" + cmdarray[2]);
            writer.flush();
            writer.close();
            os.close();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>end>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("执行完成");
    }

    //txt文件读取
    /*public static void main(String[] args) {
        TableInfoService tableInfoService = new TableInfoServiceImpl();
        String[] tableNames = {"T_SR_SERVICEREQUEST", "T_CCT_CONTACTDETAIL", "TRECORDINFO9"};
        String[] filePaths = {"D:\\"+ TimestampTool.getCurrentDate()+"\\SERVICEREQUEST.txt",
                "D:\\"+ TimestampTool.getCurrentDate()+"\\CONTACTDETAIL.txt",
                "D:\\"+ TimestampTool.getCurrentDate()+"\\TRECORDINFO.txt"};
        String[][] tableFields = {T_SR_SERVICEREQUEST.TSRSERVICEREQUESTFeilds,
                T_CCT_CONTACTDETAIL.TCCTCONTACTDETAILFeilds,
                TRECORDINFO9.TRECORDINFO9Feilds};
        for (int i = 0; i < tableNames.length ; i++) {
            List<Map<String, String>> mapList = FileProcess.readFile(filePaths[i], tableFields[i]);
            tableInfoService.addData(mapList, tableNames[i], FileProcess.arrayToString(tableFields[i]));
        }
    }*/

//    public static void main(String[] args) throws FtpProtocolException {
//
//        FtpClient ftpClient = FtpUtil.connect("136.142.25.4", 21, "testvoice", "testvoice", "/");
//        System.out.println("===="+ftpClient.isPassiveModeEnabled());
//        System.out.println("getFileList: "+FtpUtil.getFileList(ftpClient, "/").get(0));
////        long ll = FtpUtil.downloadFile(ftpClient, "/test/test.txt", "E:\\test\\test.txt");
////        System.out.println("long : "+ll);
//        FtpUtil.closeServer(ftpClient);
//    }

}

