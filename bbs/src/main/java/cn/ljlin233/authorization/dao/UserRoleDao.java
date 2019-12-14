package cn.ljlin233.authorization.dao;

import java.util.List;

import cn.ljlin233.authorization.entity.UserRole;

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


    void deleteUserRole(UserRole userRole);

    List<UserRole> getUserRole(UserRole userRole);
}