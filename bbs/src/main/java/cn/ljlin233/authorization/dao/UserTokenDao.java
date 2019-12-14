package cn.ljlin233.authorization.dao;

import cn.ljlin233.authorization.entity.UserToken;

/**
 * UserTokenDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserTokenDao {

    /**
     * 添加一个token
     *
     * @param token token
     * @param userToken userToken
     */
    void addToken(String token, UserToken userToken);

    /**
     * 根据token获取UserToken
     *
     * @param token token
     * @return UserToken
     */
    UserToken getUserToken(String token);


    /**
     * 刷新token过期时间
     *
     * @param token token
     */
    void refreshToken(String token);

}