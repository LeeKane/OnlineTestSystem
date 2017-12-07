package controller;

/**
 * Created by mac on 16/7/16.
 */
import bean.user;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UsersController {
    @Autowired
    private userService userService;

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String showUser(@RequestParam("id") String id, ModelMap modelMap) {
        //1.调用BLL层的服务接口
//        user user = userService.getUser(id);
        //2.设置模型数据
//        modelMap.put("user",user);
        //3.返回逻辑视图名称
        return "showUser";
    }
    @RequestMapping(value = "/overview")
    public String overView() {

        return "index";
    }
    @ResponseBody
    @RequestMapping(value = "/getAllJson",method = RequestMethod.GET)
    public Map getAllJson(HttpServletResponse response){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("json","sss");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/postUser",method = RequestMethod.POST)
    public Map postUser (@RequestBody Map<String,String> requestUser ){
        String userName = requestUser.get("userName");
        System.out.println(userName);
        return requestUser;
    }

}