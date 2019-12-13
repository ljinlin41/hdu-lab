package cn.ljlin233.user.service;

import cn.ljlin233.user.dto.RegisterUserRequestDto;
import cn.ljlin233.user.dto.UserLoginRequestDto;
import cn.ljlin233.user.entity.UserToken;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 22:07
 */
public interface AuthorizationService {

    /**
     * 用户登录
     *
     * @param request 请求
     * @return token
     */
    UserToken userLogin(UserLoginRequestDto request);

    /**
     * 用户注册
     *
     * @param request request
     */
    void registerUser(RegisterUserRequestDto request);
}
