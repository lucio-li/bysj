package controller.partment;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.human.Moments;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UploadService;
import utils.AppConfig;
import utils.HttpRequestUtils;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.UUID;

@Controller
@RequestMapping("/human/upload")
public class UploadController {
	@Resource
    private UploadService uploadService;
    @Autowired
    private HttpServletRequest request;
    private  HttpServletResponse response;
    @RequestMapping("/content")
    public String  content(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	//上传照片前的上传数据


        String time = request.getParameter("time");
//
//        if (time.length() < 5) {
//            ResponseUtils.renderJson(response, "{\"result\":\"fail\"}");
//            return null;
//        }
        String content  = request.getParameter("content");
        String avatarUrl = request.getParameter("avatarUrl");
        String nickname = request.getParameter("nickname");
        Moments moments = new Moments();
        moments.setContent(content);
        moments.setTime(time);
        moments.setAvatarUrl(avatarUrl);
        moments.setNickname(nickname);
        try {
        	String directory = uploadService.addContent(moments);
            Moments result = new Moments();
            result.setDirectory(directory);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(result);
            ResponseUtils.renderJson(response, str);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.renderJson(response, "{\"result\":\"fail\"}");
        }
        return null;
    }
//
    /*
     *图片上传
     */
    @RequestMapping("image")
    public String  uploadImage(String media_id, String directory, HttpServletResponse response)
            throws IllegalStateException, IOException {
        uploadService.downloadImage(media_id, directory);
        return null;
    }
    @RequestMapping("index")
    public String  index(Model model, String code, String state, HttpServletResponse response) throws UnsupportedEncodingException {
        String  result = null;

        try {
        //            result = momentsService.queryAll();

                //System.out.println(momentsList.size());


        //        ObjectMapper mapper = new ObjectMapper();
        //        String str = mapper.writeValueAsString(momentsList);
        //        ResponseUtils.renderJson(response, str);
            String  timestamp = Long.toString(System.currentTimeMillis() / 1000);
            String nonceStr = UUID.randomUUID().toString();
            String url = "";
            if (code != null) {
                if (state != null) {
                    url = "http://bysjwx.free.ngrok.cc/bysj_weixin/human/upload/index?code=" + code + "&state=" + state;
                } else {
                    url = "http://bysjwx.free.ngrok.cc/bysj_weixin/human/upload/index?code=" + code;
                }
            } else {
                if (state != null) {
                    url = "http://bysjwx.free.ngrok.cc/bysj_weixin/human/upload/index?state=" + state;
                } else {
                    url = "http://bysjwx.free.ngrok.cc/bysj_weixin/human/upload/index";
                }
            }

            //String url = "https://lq555.cn/bysj_weixin/human/upload/index";
            //获取jsapi_ticket
            String ticket;
            String string1;
            if ("".equals(AppConfig.jsapi_ticket)) {
                String jsonStrTicket = HttpRequestUtils.sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket", "access_token=" + AppConfig.access_token + "&type=jsapi");
                System.out.println("jsonStrTicket" + jsonStrTicket);
                JSONObject json = JSONObject.fromObject(jsonStrTicket);
                ticket = (String) json.get("ticket");
                string1 = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr
                        + "&timestamp=" + timestamp + "&url=" + url;
                MessageDigest crypt = MessageDigest.getInstance("SHA-1");
                crypt.reset();
                crypt.update(string1.getBytes("UTF-8"));
                String signature = byteToHex(crypt.digest());
                AppConfig.timestamp = timestamp;
                AppConfig.nonceStr = nonceStr;
                AppConfig.signature = signature;
            } else {

            }
            System.out.println("signature" + AppConfig.signature);
            model.addAttribute("timestamp", AppConfig.timestamp);
            model.addAttribute("nonceStr", AppConfig.nonceStr);
            model.addAttribute("signature", AppConfig.signature);

            if (code != null) {
                JSONObject json = getAccessToken(code);
                model.addAttribute("nickname", json.getString("nickname"));
                model.addAttribute("headimgurl", json.getString("headimgurl"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload_index";
    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    private JSONObject getAccessToken(String code) {
        System.out.println("code" + code);
        //获取accessToken
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String param = "appid=" + AppConfig.appid + "&secret=" + AppConfig.appsecret
                + "&code=" + code + "&grant_type=authorization_code";
        String jsonCode = HttpRequestUtils.sendGet(url, param);
        JSONObject jsonObjectR = JSONObject.fromObject(jsonCode);
        AppConfig.wxAccess_token = jsonObjectR.getString("access_token");
        AppConfig.wxOpenid = jsonObjectR.getString("openid");

        //获取用户信息
        String userUrl = "https://api.weixin.qq.com/sns/userinfo";
        String userParam = "access_token=" + AppConfig.wxAccess_token + "&openid=" + AppConfig.wxOpenid + "&lang=zh_CN";
        jsonCode = HttpRequestUtils.sendGet(userUrl, userParam);

        JSONObject jsonObject = JSONObject.fromObject(jsonCode);
        System.out.println("用户昵称" + jsonObject.getString("nickname"));
        return jsonObject;
    }
}
