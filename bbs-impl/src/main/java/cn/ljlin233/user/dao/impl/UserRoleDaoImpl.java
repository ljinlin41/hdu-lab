package cn.ljlin233.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserRoleDao;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:33
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {
    @Override
    public void addUserRole(int userId, String role) {

    }

    @Override
    public void deleteUserRole(int userId) {

    }

    @Override
    public List<String> getUserRoleByUserId(int userId) {
        return null;
    }
}
