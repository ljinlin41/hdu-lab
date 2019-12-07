package cn.ljlin233.user.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.user.dao.UserTokenDao;
import cn.ljlin233.user.service.UserTokenService;

/**
 * UserTokenServiceImpl
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    private UserTokenDao userTokenDao;

    public UserTokenServiceImpl() {}

    @Autowired
    public UserTokenServiceImpl(UserTokenDao userTokenDao) {
        this.userTokenDao = userTokenDao;
    }

    @Override
    public String addToken(int userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        userTokenDao.addToken(token, String.valueOf(userId));
        return token;
    }

    @Override
    public void deleteToken(int userId) {
        userTokenDao.deleteToken(String.valueOf(userId));
    }

    @Override
    public boolean checkRefreshToken(String token) {

        boolean result = false;
        boolean keyFlag = userTokenDao.hasToken(token);
        if (keyFlag) {
            result = true;
            userTokenDao.refreshToken(token);
        }
        return result;
    }

    @Override
    public Integer getUserid(String token) {

        String userId = userTokenDao.getUserId(token);

        if (userId != null && userId.length() != 0) {
            return Integer.valueOf(userId);
        }
        return null;
    }
}