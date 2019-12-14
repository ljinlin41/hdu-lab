package cn.ljlin233.authorization.service;

import java.util.List;

import cn.ljlin233.authorization.entity.UserRole;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 23:28
 */
public interface UserRoleService {

    void addUserRole(int userId, String role);

    void deleteUserRoleByUserId(int userId);

    List<UserRole> getUserRoleByUserId(int userId);
}
