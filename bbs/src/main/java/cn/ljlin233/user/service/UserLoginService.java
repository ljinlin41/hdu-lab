package cn.ljlin233.user.service;

import cn.ljlin233.user.dto.UserLoginRequestDto;
import cn.ljlin233.user.entity.UserToken;

/**
 * UserLoginService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserLoginService {

    /**
     * 用户登录
     *
     * @param request 请求
     * @return token
     */
    UserToken userLogin(UserLoginRequestDto request);

}