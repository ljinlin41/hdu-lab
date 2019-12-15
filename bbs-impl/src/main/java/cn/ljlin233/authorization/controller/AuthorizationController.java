package cn.ljlin233.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.authorization.dto.RegisterUserRequestDto;
import cn.ljlin233.authorization.dto.UserLoginRequestDto;
import cn.ljlin233.authorization.entity.UserToken;
import cn.ljlin233.authorization.service.AuthorizationService;
import cn.ljlin233.authorization.service.UserActiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * AuthorizationController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "用户授权接口")
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
    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "UserLoginRequestDto")
    @PostMapping(value = "/login")
    public UserToken loginUser(@RequestBody UserLoginRequestDto request) {

        return authorizationService.userLogin(request);
    }

    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "RegisterUserRequestDto")
    @PostMapping(value = "/register")
    public void registerUser(@RequestBody RegisterUserRequestDto request) {

        authorizationService.registerUser(request);
    }

    @ApiOperation(value = "用户激活")
    @ApiImplicitParam(name = "activeId", value = "激活码", dataType = "String")
    @GetMapping(value = "/active", params = "activeId")
    public void activeUser(@RequestParam String activeId) {

        userActiveService.activeUser(activeId);
    }
}