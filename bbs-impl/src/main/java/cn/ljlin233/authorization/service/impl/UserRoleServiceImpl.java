package cn.ljlin233.authorization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.authorization.dao.UserRoleDao;
import cn.ljlin233.authorization.entity.UserRole;
import cn.ljlin233.authorization.service.UserRoleService;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 23:29
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void addUserRole(int userId, String role) {

        UserRole userRole = UserRole.builder().userId(userId).role(role).build();

        userRoleDao.addUserRole(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(int userId) {

        UserRole userRole = UserRole.builder().userId(userId).build();

        userRoleDao.deleteUserRole(userRole);
    }

    @Override
    public List<UserRole> getUserRoleByUserId(int userId) {

        UserRole userRole = UserRole.builder().userId(userId).build();

        return userRoleDao.getUserRole(userRole);
    }

}
