package cn.ljlin233.user.service;

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
     * @param identifier 账号
     * @param credential 密码
     * @return token
     */
    UserToken userLogin(String identifier, String credential);

}