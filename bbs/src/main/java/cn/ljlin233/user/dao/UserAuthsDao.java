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
     * @param userId 用户Id
     * @param identityType 账号类型
     * @param identifier 账号
     * @param credential 密码
     */
    void addUserAuths(int userId, String identityType, String identifier, String credential);

    /**
     * 删除用户授权
     *
     * @param userId 用户Id
     */
    void deleteAuths(int userId);

    /**
     * 根据账号获取用户授权
     *
     * @param identifier 账号
     * @return 用户授权
     */
    UserAuths getUserAuthsByidentifier(String identifier);

}