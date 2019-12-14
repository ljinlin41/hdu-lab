package cn.ljlin233.authorization.service;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 23:28
 */
public interface UserRoleService {

    void addUserRole(int userId, String role);

    void deleteUserRoleByUserId(int userId);
}
