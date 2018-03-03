package utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by lq on 2017/12/12.
 */
public class ResponseUtils {
    public static void renderJson(HttpServletResponse response, String text) {
        // System.out.print(text);
        render(response, "application/json", text);
    }
    public static void renderText(HttpServletResponse response, String text) {
        // System.out.print(text);
        render(response, "text/plain", text);
    }


    /**
     * 发送内容。使用UTF-8编码。
     *
     * @param response
     * @param contentType
     * @param text
     */
    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.getWriter().write(text);
        } catch (IOException e) {
        }
    }
    /**
     * 输出图片格式
     */
    public static void renderImg(HttpServletResponse response, String url) {
        //把头像写到前端
        try {
            URL uri = new URL(url);
            response.setContentType("image/*");
            String[] urlArray = url.split("=");
            if (urlArray.length >= 1) {
                response.setContentType("image/" + urlArray[1]);
            }
            InputStream in = uri.openStream();

            OutputStream out = response.getOutputStream();
            byte buffer[]=new byte[1024];
            int len = 0;
            while((len = in.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            response.flushBuffer();
        } catch (IOException e) {
        }
    }
    /**
     * 输出图片格式
     */
    public static void renderImgByText(HttpServletResponse response, String text) {
        //把头像写到前端
        try {

            response.setContentType("image/*");

            response.getWriter().write(text);



        } catch (IOException e) {
        }
    }
}
