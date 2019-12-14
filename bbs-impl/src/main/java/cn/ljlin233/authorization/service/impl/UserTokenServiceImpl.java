package cn.ljlin233.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.authorization.dao.UserTokenDao;
import cn.ljlin233.authorization.entity.UserToken;
import cn.ljlin233.authorization.service.UserTokenService;

/**
 * @author lvjinlin42@foxmail.com
 * UserTokenServiceImpl
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public void addToken(UserToken userToken) {

        userTokenDao.addToken(userToken.getToken(), userToken);
    }


    @Override
    public UserToken getUserToken(String token) {

        UserToken userToken = userTokenDao.getUserToken(token);

        if (userToken != null) {
            userTokenDao.refreshToken(token);
        }

        return userToken;
    }

}