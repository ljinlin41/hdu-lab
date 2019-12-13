package cn.ljlin233.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserOriginDao;
import cn.ljlin233.user.dao.mapper.UserOriginMapper;
import cn.ljlin233.user.entity.UserOrigin;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:32
 */
@Repository
public class UserOriginDaoImpl implements UserOriginDao {

    @Autowired
    private UserOriginMapper userOriginMapper;

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

        UserOrigin userOrigin = UserOrigin.builder().account(account).build();

        int count = userOriginMapper.selectCount(userOrigin);

        return count != 0;
    }
}
