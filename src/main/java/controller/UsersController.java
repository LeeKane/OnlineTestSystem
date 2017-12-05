package controller;

/**
 * Created by mac on 16/7/16.
 */
import bean.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class UsersController {
    @Autowired
    private userService userService;

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String showUser(@RequestParam("id") String id, ModelMap modelMap) {
        //1.调用BLL层的服务接口
        user user = userService.getUser(id);
        //2.设置模型数据
        modelMap.put("user",user);
        //3.返回逻辑视图名称
        return "showUser";
    }
    @RequestMapping(value = "/overview")
    public String overView() {

        return "index";
    }
    @RequestMapping(value = "/picture")
    public String picture() {

        return "picture";
    }
}