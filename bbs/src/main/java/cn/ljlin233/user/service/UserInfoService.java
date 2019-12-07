package cn.ljlin233.user.service;

import cn.ljlin233.user.entity.UserInfo;

/**
 * UserInfoService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserInfoService {

    /**
     * 获取用户信息
     *
     * @param id 用户Id
     * @return 用户信息
     */
    UserInfo getUserInfo(int id);

    /**
     * 更新用户昵称
     *
     * @param userId 用户Id
     * @param newname 新昵称
     */
    void updateNickname(int userId, String newname);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 删除用户
     *
     * @param id 用户Id
     */
    void deleteUser(int id);

}