package cn.ljlin233.user.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserAuthsDao;
import cn.ljlin233.user.entity.UserAuths;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:32
 */
@Repository
public class UserAuthsDaoImpl implements UserAuthsDao {
    @Override
    public void addUserAuths(int userId, String identityType, String identifier, String credential) {

    }

    @Override
    public void deleteAuths(int userId) {

    }

    @Override
    public UserAuths getUserAuthsByidentifier(String identifier) {
        return null;
    }
}
