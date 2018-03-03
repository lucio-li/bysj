package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lq on 2017/12/10.
 */
@Controller
public class Test {
    @Resource
    private UserService userService;
    private Logger logger = Logger.getLogger(Test.class);
    @RequestMapping("/test")
    public String  index(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
    	logger.info("sadfas");
    	try {
            userService.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> userList;
        userList = userService.queryAll();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(userList);
        ResponseUtils.renderJson(response, str);
        return null;
    }
    @org.junit.Test
    public void test() {
    	logger.info("logger测试");
    }
}
