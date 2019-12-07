package cn.ljlin233.user.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserTokenDao;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:33
 */
@Repository
public class UserTokenDaoImpl implements UserTokenDao {
    @Override
    public void addToken(String token, String userId) {

    }

    @Override
    public String getUserId(String token) {
        return null;
    }

    @Override
    public void deleteToken(String token) {

    }

    @Override
    public void refreshToken(String token) {

    }

    @Override
    public long getTokenTime(String token) {
        return 0;
    }

    @Override
    public boolean hasToken(String token) {
        return false;
    }
}
