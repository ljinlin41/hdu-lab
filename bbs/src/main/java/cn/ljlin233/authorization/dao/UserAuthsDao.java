package cn.ljlin233.authorization.dao;

import cn.ljlin233.authorization.entity.UserAuths;

/**
 * UserAuthsDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserAuthsDao {

    /**
     * 增加用户授权
     *
     * @param userAuths 用户授权
     */
    void addUserAuths(UserAuths userAuths);

    /**
     * 删除用户授权
     *
     * @param userAuths 用户授权
     */
    void deleteUserAuth(UserAuths userAuths);

    UserAuths getUserAuths(UserAuths userAuths);
}