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

    /**
     * 验证邮箱是否存在
     *
     * @param email 邮箱
     * @return true or false
     */
    boolean existsEmail(String email);

    /**
     * 验证手机号是否存在
     *
     * @param phone 手机号
     * @return true or false
     */
    boolean existsPhone(String phone);

}