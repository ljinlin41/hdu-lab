package cn.ljlin233.user.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserOriginDao;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:32
 */
@Repository
public class UserOriginDaoImpl implements UserOriginDao {
    @Override
    public void addUserOrigin(String account) {

    }

    @Override
    public void deleteUserOrigin(String account) {

    }

    @Override
    public Integer getUserOriginId(String account) {
        return null;
    }

    @Override
    public boolean existsAccount(String account) {
        return false;
    }
}
