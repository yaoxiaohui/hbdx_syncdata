package util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 9:21 2017/9/04.
 * @Modified By :
 */
public class RequestHandleUtil {

    public static String handle(HttpServletRequest request) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedInputStream in = new BufferedInputStream(request.getInputStream());
        int i;
        char c;
        while ((i = in.read()) != -1) {
            c = (char) i;
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }
}
