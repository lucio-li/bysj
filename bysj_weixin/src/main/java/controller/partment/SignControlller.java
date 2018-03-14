package controller.partment;

import model.human.WxUser;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IWxUserService;
import utils.AppConfig;
import utils.HttpRequestUtils;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by liqiao on 2018/3/13.
 */
@Controller
@RequestMapping("/human/sign")
public class SignControlller {
    @Resource
    private IWxUserService wxUserService;
    @RequestMapping("/index")
    public String index(Model model) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowTime = df.format(new Date());// new Date()为获取当前系统时间
        System.out.println(nowTime);
        model.addAttribute("nowTime", nowTime);
        return "qr_code";
    }
    @RequestMapping("/check")
    public String check(Model model, String state, String code) {
        String time = state;
        //获取用户信息
        WxUser wxUser = null;
        if (code != null) {
            JSONObject json = getAccessToken(code);
            String openid = json.getString("openid");
            String nickname = json.getString("nickname");
            String headimgurl = json.getString("headimgurl");
            wxUser = wxUserService.queryByOpenid(openid);
            if (wxUser == null) {
                wxUser = new WxUser();
                wxUser.setOpenid(openid);
                wxUser.setHeadimgurl(headimgurl);
                wxUser.setNickname(nickname);
                wxUserService.insertOne(wxUser);
            } else {
                wxUser.setHeadimgurl(headimgurl);
                wxUser.setNickname(nickname);
                wxUserService.update(wxUser);
            }
            model.addAttribute("username", wxUser.getUsername());
            model.addAttribute("nickname", json.getString("nickname"));
            model.addAttribute("headimgurl", json.getString("headimgurl"));
        } else {
//            model.addAttribute("result", "error");//签到失败，不是微信公众号扫码进行的签到
//            return "sign_check";
        }

        if (time != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String nowTime = df.format(new Date());// new Date()为获取当前系统时间

            if (nowTime.equals(time)) {
                model.addAttribute("result", "success");
                wxUserService.updateSignTime(wxUser);//签到成功，更新签到的时间
            } else {
                model.addAttribute("result", "error");
            }

        }
        SimpleDateFormat nowDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String nowDfTime = nowDf.format(new Date());// new Date()为获取当前系统时间
        model.addAttribute("signTime", nowDfTime);
        return "sign_check";
    }




    //已签到的人员列表
    @RequestMapping("/signList")
    public String signList(Model model) {
        List<WxUser> wxUserList = wxUserService.querySignUser();
        model.addAttribute("wxUserList", wxUserList);
        return "signList";
    }
    //未签到的人员列表
    @RequestMapping("/unsignList")
    public String unsignList(Model model) {
        List<WxUser> wxUserList = wxUserService.queryUnSignUser();
        model.addAttribute("wxUserList", wxUserList);
        return "unsignList";
    }
    //未签到的人员列表
    @RequestMapping("/userList")
    public String userList(Model model) {
        List<WxUser> wxUserList = wxUserService.queryAll();
        model.addAttribute("wxUserList", wxUserList);
        return "userList";
    }
    //获取用户信息
    @RequestMapping("/userInfo")
    public void userInfo(String openid, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        if (openid == null) {
            jsonObject.put("result", "error");
            ResponseUtils.renderJson(response, jsonObject.toString());
            return;
        }
        WxUser wxUser = wxUserService.queryByOpenid(openid);

        jsonObject.put("openid", wxUser.getOpenid());
        jsonObject.put("nickname", wxUser.getNickname());
        jsonObject.put("headimgurl", wxUser.getHeadimgurl());
        jsonObject.put("username", wxUser.getUsername());
        ResponseUtils.renderJson(response, jsonObject.toString());


    }
    //更改用户信息
    @RequestMapping("/updateInfo")
    public void updateInfo(WxUser wxUser, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        WxUser wxUserTmp = wxUserService.queryByOpenid(wxUser.getOpenid());
        if (wxUserTmp == null) {
            jsonObject.put("result", "error");
            ResponseUtils.renderJson(response, jsonObject.toString());
            return;
        }
        wxUserTmp.setUsername(wxUser.getUsername());
        wxUserService.update(wxUser);

        jsonObject.put("result", "succcess");
        ResponseUtils.renderJson(response, jsonObject.toString());


    }
    //删除用户
    @RequestMapping("/deleteUser")
    public void deleteUser(WxUser wxUser, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        WxUser wxUserTmp = wxUserService.queryByOpenid(wxUser.getOpenid());
        if (wxUserTmp == null) {
            jsonObject.put("result", "error");
            ResponseUtils.renderJson(response, jsonObject.toString());
            return;
        }
        wxUserService.deleteUser(wxUser.getOpenid());

        jsonObject.put("result", "succcess");
        ResponseUtils.renderJson(response, jsonObject.toString());


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
