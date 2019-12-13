package cn.ljlin233.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserAuthsDao;
import cn.ljlin233.user.dao.mapper.UserAuthsMapper;
import cn.ljlin233.user.entity.UserAuths;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:32
 */
@Repository
public class UserAuthsDaoImpl implements UserAuthsDao {

    @Autowired
    private UserAuthsMapper userAuthsMapper;

    @Override
    public void addUserAuths(UserAuths userAuths) {

        userAuthsMapper.insertSelective(userAuths);
    }

    @Override
    public void deleteAuthsByUserId(int userId) {

    }

    @Override
    public UserAuths getUserAuthsByidentifier(String identifier) {

        UserAuths userAuths = UserAuths.builder().identifier(identifier).build();

        userAuths = userAuthsMapper.selectOne(userAuths);

        return userAuths;
    }
}
