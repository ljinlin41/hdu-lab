package cn.ljlin233.user.dao;

import java.util.List;

import cn.ljlin233.user.entity.UserRole;

/**
 * UserRoleDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserRoleDao {

    /**
     * 增加一个用户角色
     *
     * @param userRole 用户角色
     */
    void addUserRole(UserRole userRole);

    /**
     * 根据用户Id删除用户角色
     *
     * @param userId 用户Id
     */
    void deleteUserRoleByUserId(int userId);

    /**
     * 根据用户Id获取用户角色列表
     *
     * @param userId 用户Id
     * @return 角色列表
     */
    List<UserRole> getUserRoleByUserId(int userId);

}