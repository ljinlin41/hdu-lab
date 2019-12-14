package cn.ljlin233.user.dao;

import cn.ljlin233.user.entity.UserInfo;

/**
 * UserInfoDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserInfoDao {

    /**
     * 添加用户信息
     *
     * @param userInfo 用户信息
     */
    void addUserInfo(UserInfo userInfo);

    /**
     * 删除用户信息
     *
     * @param id 用户Id
     */
    void deleteUserInfo(int id);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     */
    void updateUserInfoByPrimaryKey(UserInfo userInfo);

    /**
     * 获取用户信息
     * @param userInfo 条件
     * @return 用户信息
     */
    UserInfo getOneUserInfo(UserInfo userInfo);


    /**
     * 根据激活码获取用户Id
     *
     * @param activeId 激活码
     * @return 用户Id
     */
    int getUserIdByActiveId(String activeId);

}