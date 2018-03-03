package utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by liqiao on 2018/1/21.
 * 微信工具类
 */
public class WeixinUtil {
    /**
     * 创建菜单
     * @param token
     * @param menu
     * @return
     * @throws IOException
     */
    public static String createMenu(String token,String menu) {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        String result = HttpRequestUtils.sendPost(url, menu);
        return result;
    }
    public static String createArticles(String articles) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + AppConfig.access_token;
        String result = HttpRequestUtils.sendPost(url, articles);
        System.out.println("上传图文素材");
        System.out.println(result);
        return result;
    }

    /**
     * 将xml转化为Map集合
     *
     * @param request
     * @return
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream ins = null;
        try {
            ins = request.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = reader.read(ins);
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
        Element root = doc.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        try {
            ins.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return map;
    }
    /**
     * 文本消息转化为xml
     *
     * @param object
     * @return
     */
    public static String objectToXml(Object object) {
        XStream xstream = new XStream();
        xstream.alias("xml", object.getClass());
        return xstream.toXML(object);

    }
    /**
     * map转化为xml
     *
     * @param map
     * @return
     */
    public static String mapToXml(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXmlTmp(map, sb);
        sb.append("</xml>");
        System.out.println("将Map转成Xml, Xml：" + sb.toString());
        try {
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
    public static String mapToXmlTmp(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXmlTmp(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXmlTmp((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                }

            }

        }
        return sb.toString();
    }
}
