package cn.ljlin233.user.service;

import cn.ljlin233.user.dto.UpdateUserInfoRequestDto;
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
    UserInfo getUserInfoByUserId(int id);

    /**
     * 根据账号获取用户信息
     *
     * @param account 账号
     * @return userInfo
     */
    UserInfo getUserInfoByAccount(String account);

    /**
     * 更新用户信息
     *
     * @param request request
     */
    void updateUserInfo(UpdateUserInfoRequestDto request);

    /**
     * 删除用户
     *
     * @param id 用户Id
     */
    void deleteUser(int id);

    /**
     * 更新nickname
     *
     * @param userId userId
     * @param nickname nickname
     */
    void updateNickname(int userId, String nickname);

    void addUserInfo(UserInfo userInfo);

    boolean existsEmail(String email);

    boolean existsPhone(String phone);
}