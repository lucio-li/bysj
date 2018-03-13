package controller;

import model.menu.Button;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IWxEventService;
import service.IWxMenuService;
import utils.AppConfig;
import utils.ResponseUtils;
import utils.WeixinUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by liqiao on 2018/1/21.
 * 接收微信发送的token的controller
 */
@Controller
@RequestMapping("/wx")
public class WechatController {
    @Resource
    private IWxMenuService wxMenuService;
    @Resource
    private IWxEventService wxEventService;
    /**
     * 验证消息的确来自微信服务器
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public void wechat(String signature, String timestamp, String nonce, String echostr, HttpServletResponse res) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(AppConfig.TOKEN);
        list.add(nonce);
        list.add(timestamp);
        Collections.sort(list);
        String signatureBySha = DigestUtils.shaHex(list.get(0)+list.get(1)+list.get(2));
        AppConfig.timestamp = timestamp;
        AppConfig.nonceStr = nonce;
        AppConfig.signature = signatureBySha;

        System.out.println("获取accetoken");

        if (signature.equals(signatureBySha)) {
            ResponseUtils.renderText(res, echostr);
        } else {
            ResponseUtils.renderText(res, "false");
        }
    }
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void wechat(HttpServletRequest request,  HttpServletResponse res) {

        Map<String, String> req = WeixinUtil.xmlToMap(request);
        String event = req.get("Event");//如果是事件类型
        if ("subscribe".equals(event)) {//有人关注公众号

        } else if ("CLICK".equals(event)) {//菜单点击事件
            String result = wxEventService.menuClick(req);
            System.out.println("xml为");
            System.out.println(result);
            ResponseUtils.renderText(res, result);
        }

//        if ("22".equals(eventKey)) {
//            System.out.println("点击了test");
//            TextMessage textMessage = new TextMessage();
//            textMessage.setToUserName(req.get("FromUserName"));
//            textMessage.setFromUserName(req.get("ToUserName"));
//            textMessage.setCreateTime(Long.parseLong(req.get("CreateTime")));
//            textMessage.setContent("欢迎你test");
//            textMessage.setMsgType("text");
//            ResponseUtils.renderText(res, WeixinUtil.textMessageToXml(textMessage));
//
//        }
//        System.out.println("有post请求" + req.get("EventKey"));
//        ResponseUtils.renderJson(res, "12234");
    }

    @RequestMapping("/test")
    public void test(HttpServletRequest request,  HttpServletResponse res) {

        wxMenuService.createMenu();
        //wxMenuService.queryMenu();
    }

    @RequestMapping(value = "/velocity", method = RequestMethod.GET)
    public String getTest(Model model) {
        List<Button> list = new ArrayList<Button>();
        for (int i =0; i < 3; i++) {
            Button button = new Button();
            button.setName("" + i);
            list.add(button);
        }



        model.addAttribute("list", list);
        return "test";
    }
}
