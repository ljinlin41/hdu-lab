package cn.ljlin233.user.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserRoleDao;
import cn.ljlin233.user.dao.mapper.UserRoleMapper;
import cn.ljlin233.user.entity.UserRole;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:33
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void addUserRole(UserRole userRole) {
        userRoleMapper.insertSelective(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(int userId) {

    }

    @Override
    public List<UserRole> getUserRoleByUserId(int userId) {

        UserRole userRole = UserRole.builder().userId(userId).build();

        List<UserRole> userRoleList = userRoleMapper.select(userRole);

        return userRoleList;
    }
}
