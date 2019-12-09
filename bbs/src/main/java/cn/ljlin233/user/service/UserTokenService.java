package cn.ljlin233.user.service;

import cn.ljlin233.user.entity.UserToken;

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
     * @return token
     */
    void addToken(UserToken userToken);

    /**
     * 删除token
     *
     * @param userId 用户Id
     */
    void deleteToken(int userId);

    /**
     * 检查token是否存在，存在则刷新
     *
     * @param token token
     * @return true or false
     */
    boolean checkRefreshToken(String token);

}