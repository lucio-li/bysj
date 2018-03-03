package service.impl;

import dao.ArticlesMapper;
import dao.ButtonMapper;
import dao.ImageMapper;
import dao.MessageMapper;
import model.menu.Button;
import model.message.Article;
import model.message.Image;
import org.springframework.stereotype.Service;
import service.IWxEventService;
import utils.WeixinUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liqiao on 2018/1/28.
 */
@Service("wxEventService")
public class WxEventServiceImpl implements IWxEventService{
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private ButtonMapper buttonMapper;
    @Resource
    private ImageMapper imageMapper;
    @Resource
    private ArticlesMapper articlesMapper;
    /**
     * 菜单的点击事件,回复文本消息
     * @param req
     * @return
     */
    public String menuClick(Map<String, String> req) {
        String eventKey = req.get("EventKey");//拿到key值

        try {
            Button button = buttonMapper.queryByKey(eventKey).get(0);
            if ("image".equals(button.getMsg_type())) {//菜单响应的消息类型是图片
                Image tmpImage = imageMapper.queryByKey(eventKey);
                tmpImage.setMediaId(tmpImage.getMedia_id());


                Image image = new Image();
                image.setToUserName(req.get("FromUserName"));
                image.setFromUserName(req.get("ToUserName"));
                image.setCreateTime(Long.parseLong(req.get("CreateTime")));
                image.setImage(tmpImage);
                image.setMsgType("image");
                return WeixinUtil.objectToXml(image);

            } else  if("article".equals(button.getMsg_type())){//图文消息
                Article articleTmp = articlesMapper.queryByKey(eventKey);
//                Item item = new Item();
//                item.setTitle(articleTmp.getTitle());
//                item.setDescription(articleTmp.getContent());
//                item.setPicUrl(articleTmp.getUrl());
//                item.setUrl(articleTmp.getContent_source_url());
//                List<Item> itemList = new ArrayList<Item>();
//                itemList.add(item);
//                articleTmp.setItem(itemList);
//                Article article = new Article();
//                article.setToUserName(req.get("FromUserName"));
//
//                article.setFromUserName(req.get("ToUserName"));
//                article.setCreateTime(Long.parseLong(req.get("CreateTime")));
//                article.setArticles(articleTmp);
//                article.setMsgType("news");
//                article.setArticleCount("1");
                Map map = new HashMap();
                map.put("ToUserName", req.get("FromUserName"));
                map.put("FromUserName", req.get("ToUserName"));
                map.put("CreateTime", Long.parseLong(req.get("CreateTime")));
                map.put("MsgType", "news");
                map.put("ArticleCount", "1");
                List<Map> itemList = new ArrayList<Map>();
                Map itemXml = new HashMap();

                Map itemMap = new HashMap();
                itemMap.put("Title", articleTmp.getTitle());
                itemMap.put("Description", articleTmp.getContent());
                itemMap.put("PicUrl", "http://mmbiz.qpic.cn/mmbiz_jpg/bRtVZ8IpYIc5ZFJqk4liazNIHUVjKJicl8Naric9lHHTia7DroiahuI5T7jAd3z5WpT46mP3x3tRvCEcVuddvvnDx4Q/0?wx_fmt=jpeg");
                itemMap.put("Url", articleTmp.getContent_source_url());

                itemXml.put("item", itemMap);
                itemList.add(itemXml);
                map.put("Articles", itemList);
                return WeixinUtil.mapToXml(map);

            }
//            TextMessage textMessage = messageMapper.queryOneByKey(eventKey);
//            textMessage.setToUserName(req.get("FromUserName"));
//            textMessage.setFromUserName(req.get("ToUserName"));
//            textMessage.setCreateTime(Long.parseLong(req.get("CreateTime")));
//            return WeixinUtil.objectToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
