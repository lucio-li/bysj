package utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lq on 2017/12/12.
 */
public class ResponseUtils {
    public static void renderJson(HttpServletResponse response, String text) {
        // System.out.print(text);
        render(response, "application/json", text);
    }


    /**
     * 发送内容。使用UTF-8编码。
     *
     * @param response
     * @param contentType
     * @param text
     */
    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType("application/json");
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
}
