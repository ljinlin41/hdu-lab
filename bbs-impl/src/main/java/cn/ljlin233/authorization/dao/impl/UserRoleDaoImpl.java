package cn.ljlin233.authorization.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.authorization.dao.UserRoleDao;
import cn.ljlin233.authorization.dao.mapper.UserRoleMapper;
import cn.ljlin233.authorization.entity.UserRole;

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
    public void deleteUserRole(UserRole userRole) {
        userRoleMapper.delete(userRole);
    }

    @Override
    public List<UserRole> getUserRole(UserRole userRole) {

        List<UserRole> userRoleList = userRoleMapper.select(userRole);

        return new ArrayList<>(userRoleList);
    }
}
