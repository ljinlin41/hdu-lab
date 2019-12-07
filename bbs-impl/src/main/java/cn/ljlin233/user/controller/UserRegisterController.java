package cn.ljlin233.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ljlin233.user.service.UserActiveService;
import cn.ljlin233.user.service.UserRegisterService;

/**
 * UserRegisterController
 */
@Controller
@RequestMapping("/api")
public class UserRegisterController {

    private UserRegisterService userRegisterService;

    private UserActiveService userActiveService;

    public UserRegisterController() {}

    ;

    @Autowired
    public UserRegisterController(UserRegisterService userRegisterService, UserActiveService userActiveService) {
        this.userRegisterService = userRegisterService;
        this.userActiveService = userActiveService;
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.POST)
    public void registerUser(HttpServletRequest request) throws Exception {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String verId = request.getParameter("verId");
        String verCode = request.getParameter("verCode");

        userRegisterService.registerUser(account, password, role, email, verId, verCode);

    }

    @RequestMapping(value = {"/user"}, params = {"activeId"}, method = RequestMethod.GET)
    public void activeUser(@RequestParam String activeId) {
        userActiveService.activeUser(activeId);
    }

}