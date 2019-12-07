package cn.ljlin233.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.user.entity.UserToken;
import cn.ljlin233.user.service.UserLoginService;

/**
 * UserLoginController
 */
@Controller
@RequestMapping("/api")
public class UserLoginController {

    private UserLoginService userLoginService;

    public UserLoginController() {}

    @Autowired
    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public UserToken loginUser(HttpServletRequest request) {
        String identifier = request.getParameter("identifier");
        String credential = request.getParameter("credential");

        UserToken userToken = userLoginService.userLogin(identifier, credential);

        return userToken;
    }

}