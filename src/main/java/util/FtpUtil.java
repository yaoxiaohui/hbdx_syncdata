package util;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpProtocolException;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FtpUtil {

    //加载ftp连接信息
    public static final HashMap<String, String> FTPINFO_PROPERTIES = FileProcess.readProperties("/ftpconfig.properties");
    /**
     * 连接到ftp服务器
     * @param ip ftp服务器地址
     * @param port ftp服务器端口
     * @param user 登陆ftp服务器用户
     * @param password 登陆ftp服务器用户密码
     * @param remotePath ftp服务器文件路径
     * @return FtpClient
     * @throws RuntimeException
     */
    public static FtpClient connect(String ip, int port, String user, String password, String remotePath) throws RuntimeException {
        FtpClient ftpClient = FtpClient.create();
        SocketAddress addr = new InetSocketAddress(ip, port);
        try{
            ftpClient.connect(addr);
            ftpClient.login(user, password.toCharArray());
            ftpClient.setBinaryType();
            if (remotePath!=null && remotePath.length() != 0) { // 把远程系统上的目录切换到参数path所指定的目录
                ftpClient.changeDirectory(remotePath);
                /*String[] paths=remotePath.split("/");
                for(String path : paths){
                    if(path.length() == 0)
                        continue;
                    try{
                        ftpClient.changeDirectory(path);
                    }catch(FtpProtocolException ex){
                        ftpClient.makeDirectory(path);
                        ftpClient.changeDirectory(path);
                    }
                }*/
            }
            return ftpClient;
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * 取得相对于当前连接目录的某个目录下所有文件列表
     *
     * @param path
     * @return
     * @throws FtpProtocolException
     */
    public static List<String> getFileList(FtpClient ftpClient, String path) throws FtpProtocolException{
        List<String> list = new ArrayList<>();
        DataInputStream dis;
        try {
            dis = new DataInputStream(ftpClient.nameList(path));
            String filename = "";
            while((filename = dis.readLine()) != null){
                String filenameFuffix = filename.split("\\.")[1];
                if(!filename.contains("common") && ("txt").equals(filenameFuffix)){
                    list.add(filename);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 从ftp下载文件到本地
     *
     * @param filename 服务器上的文件名
     * @param newfilename 本地生成的文件名
     * @return
     * @throws Exception
     */
    public static long downloadFile(FtpClient ftpClient, String filename, String newfilename) {
        long result = 0;
        InputStream is = null;
        FileOutputStream os = null;
        try {
            is = ftpClient.getFileStream(filename);
            java.io.File outfile = new java.io.File(newfilename);
            os = new FileOutputStream(outfile);
            byte[] bytes = new byte[is.available()];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
                result = result + c;
            }
        } catch (IOException | FtpProtocolException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 断开与ftp服务器连接
     *
     * @throws IOException
     */
    public static boolean closeServer(FtpClient ftpClient) {
        try {
            if (ftpClient != null) {
                //ftpClient.closeServer();
                ftpClient.close();
            }
            System.out.println("已从服务器断开");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
