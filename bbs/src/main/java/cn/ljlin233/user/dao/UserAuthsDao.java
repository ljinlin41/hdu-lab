package cn.ljlin233.user.dao;

import cn.ljlin233.user.entity.UserAuths;

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
     * @param userId 用户Id
     */
    void deleteAuthsByUserId(int userId);

    /**
     * 根据账号获取用户授权
     *
     * @param identifier 账号
     * @return 用户授权
     */
    UserAuths getUserAuthsByidentifier(String identifier);

}