package util;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by ZHXG on 2017/9/04.
 */
public class HttpClientUtil {

    public String requstData(String uri, String paramContent) {

        String rev = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(uri);
            //添加http头信息
            httppost.addHeader("Authorization", "your token");
            httppost.addHeader("Content-Type", "application/json");
            httppost.addHeader("User-Agent", "imgfornote");
            httppost.setEntity(new StringEntity(paramContent != null ? paramContent : "", "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            /*//检验状态码，如果成功接收数据
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code+"code");
            if (code == 200) {
                rev = EntityUtils.toString(response.getEntity());//返回json格式： {"id": "","name": ""}
                System.out.println("返回数据==="+rev);
            }*/
            rev = EntityUtils.toString(response.getEntity());//返回json格式
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rev;
    }
}
