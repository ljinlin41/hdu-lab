package cn.ljlin233.authorization.service;

import cn.ljlin233.authorization.entity.UserToken;

/**
 * UserTokenService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserTokenService {

    /**
     * 添加token
     *
     * @param userToken token
     */
    void addToken(UserToken userToken);

    UserToken getUserToken(String token);

}