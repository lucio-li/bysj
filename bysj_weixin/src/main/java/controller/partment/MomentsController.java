package controller.partment;

import model.human.Moments;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MomentsService;
import utils.AppConfig;
import utils.HttpRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Formatter;
import java.util.List;

/**
 * 朋友圈照片的展示，管理
 * Created by lq on 2017/12/13.
 */
@Controller
@RequestMapping("/human/moments")
public class MomentsController {
    @Resource
    private MomentsService momentsService;

    /**
     *
     * @param response
     * 获取所有的动态
     * @return
     */
    @RequestMapping("list")
    public String  list(Model model, String code, HttpServletResponse response) {
        List<Moments> list = null;
        if (code!= null) {
            System.out.println("code" + code);
            //获取accessToken
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
            String param = "appid=" + AppConfig.appid + "&secret=" + AppConfig.appsecret
                    + "&code=" + code + "&grant_type=authorization_code";
            String jsonRes = HttpRequestUtils.sendGet(url, param);
            JSONObject jsonObject = JSONObject.fromObject(jsonRes);
            AppConfig.wxAccess_token = jsonObject.getString("access_token");
            AppConfig.wxOpenid = jsonObject.getString("openid");

            //获取用户信息
            String userUrl = "https://api.weixin.qq.com/sns/userinfo";
            String userParam = "access_token=" + AppConfig.wxAccess_token + "&openid=" + AppConfig.wxOpenid + "&lang=zh_CN";
            jsonRes = HttpRequestUtils.sendGet(userUrl, userParam);

            jsonObject = JSONObject.fromObject(jsonRes);
            model.addAttribute("nickname", jsonObject.getString("nickname"));
        }
        try {
            list = momentsService.queryAll();
            model.addAttribute("momentsList", list);
//            System.out.println(momentsList.size());



        } catch (Exception e) {
            e.printStackTrace();
        }

//        ObjectMapper mapper = new ObjectMapper();
//        String str = mapper.writeValueAsString(momentsList);
//        ResponseUtils.renderJson(response, str);



        return "moments_list";
    }

    /**
     * 删除一条动态
     */
//    @RequestMapping("deleteOne")
//    public String deleteOne(HttpServletResponse response, String time) {
//        if (time == null || "".equals(time.trim())) {
//            ResponseUtils.renderJson(response, "{\"result\": \"fail\"}");
//            return null;
//        }
//        String result = momentsService.deleteOne(time);
//        ResponseUtils.renderJson(response, "{\"result\": \"" + result + "\"}");
//        return null;
//    }
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
