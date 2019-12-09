package cn.ljlin233.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.user.dto.UserLoginRequestDto;
import cn.ljlin233.user.entity.UserToken;
import cn.ljlin233.user.service.UserLoginService;

/**
 * @author lvjinlin42@foxmail.com
 * UserLoginController
 */
@RestController
@RequestMapping("/api")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping(value = "/login")
    public UserToken loginUser(@RequestBody UserLoginRequestDto request) {

        UserToken userToken = userLoginService.userLogin(request);

        return userToken;
    }

}