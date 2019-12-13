package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.user.dao.UserRoleDao;
import cn.ljlin233.user.entity.UserRole;
import cn.ljlin233.user.service.UserRoleService;

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
}
