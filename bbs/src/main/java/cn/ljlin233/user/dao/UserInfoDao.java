package cn.ljlin233.user.dao;

import java.util.List;

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
     * @param account 账号
     * @param email 邮箱
     * @param registerTime 注册时间
     * @param activeId 激活码
     */
    void addUserInfo(String account, String email, String registerTime, String activeId);

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
    void updateUserInfo(UserInfo userInfo);

    /**
     * 按页获取所有用户信息
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 用户信息列表
     */
    List<UserInfo> getAllUserInfo(int pageNum, int pageSize);

    /**
     * 根据用户Id获取用户信息
     *
     * @param id 用户Id
     * @return 用户信息
     */
    UserInfo getUserInfoById(int id);

    /**
     * 根据用户账户获取用户Id
     *
     * @param account 用户账户
     * @return 用户Id
     */
    int getUserIdByAccount(String account);

    /**
     * 根据激活码获取用户Id
     *
     * @param activeId 激活码
     * @return 用户Id
     */
    int getUserIdByActiveId(String activeId);

    /**
     * 验证账号是否存在
     *
     * @param account 账号
     * @return true or false
     */
    boolean existsAccount(String account);

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

    /**
     * 验证账号是否激活
     *
     * @param account 账号
     * @return true or false
     */
    boolean isActive(String account);

    /**
     * 更新其他表中冗余的nickname
     *
     * @param tableName
     * @param nameCol
     * @param newName
     * @param idCol
     * @param userId
     */
    void updateUsername(String tableName, String nameCol, String newName, String idCol, int userId);
}