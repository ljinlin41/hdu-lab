package cn.ljlin233.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.authorization.dao.UserAuthsDao;
import cn.ljlin233.authorization.entity.UserAuths;
import cn.ljlin233.authorization.service.UserAuthService;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 23:22
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthsDao userAuthsDao;

    @Override
    public void addUserAuth(int userId, String identityType, String identifier, String credential) {

        UserAuths userAuths = UserAuths.builder()
            .userId(userId)
            .identityType(identityType)
            .identifier(identifier)
            .credential(credential)
            .build();

        userAuthsDao.addUserAuths(userAuths);

    }

    @Override
    public void deleteUserAuthByUserId(int userId) {
        UserAuths userAuths = UserAuths.builder().userId(userId).build();

        userAuthsDao.deleteUserAuth(userAuths);
    }
}
