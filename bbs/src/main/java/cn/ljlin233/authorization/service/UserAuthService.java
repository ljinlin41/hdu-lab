package cn.ljlin233.authorization.service;

import cn.ljlin233.authorization.entity.UserAuths;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 23:18
 */
public interface UserAuthService {

    void addUserAuth(int userId, String identityType, String identifier, String credential);

    void deleteUserAuthByUserId(int userId);

    UserAuths getUserAuthsByIdentifier(String identifier);
}
