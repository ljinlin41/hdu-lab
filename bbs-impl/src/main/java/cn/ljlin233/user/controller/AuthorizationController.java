package cn.ljlin233.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.user.dto.RegisterUserRequestDto;
import cn.ljlin233.user.dto.UserLoginRequestDto;
import cn.ljlin233.user.entity.UserToken;
import cn.ljlin233.user.service.UserActiveService;
import cn.ljlin233.user.service.UserLoginService;

/**
 * @author lvjinlin42@foxmail.com
 * UserLoginController
 */
@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @Autowired
    private UserActiveService userActiveService;

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping(value = "/login")
    public UserToken loginUser(@RequestBody UserLoginRequestDto request) {

        return userLoginService.userLogin(request);
    }

    @PostMapping(value = "/register")
    public void registerUser(@RequestBody RegisterUserRequestDto request) {

        //userInfoService.registerUser(request);
    }

    @GetMapping(value = "/user", params = "activeId")
    public void activeUser(@RequestParam String activeId) {

        userActiveService.activeUser(activeId);
    }
}