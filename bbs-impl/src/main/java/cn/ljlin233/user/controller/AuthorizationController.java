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
import cn.ljlin233.user.service.AuthorizationService;
import cn.ljlin233.user.service.UserActiveService;

/**
 * AuthorizationController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class AuthorizationController {

    @Autowired
    private UserActiveService userActiveService;


    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 用户登录
     *
     * @param request request
     * @return result
     */
    @PostMapping(value = "/login")
    public UserToken loginUser(@RequestBody UserLoginRequestDto request) {

        return authorizationService.userLogin(request);
    }


    @PostMapping(value = "/register")
    public void registerUser(@RequestBody RegisterUserRequestDto request) {

        authorizationService.registerUser(request);
    }

    @GetMapping(value = "/user", params = "activeId")
    public void activeUser(@RequestParam String activeId) {

        userActiveService.activeUser(activeId);
    }
}