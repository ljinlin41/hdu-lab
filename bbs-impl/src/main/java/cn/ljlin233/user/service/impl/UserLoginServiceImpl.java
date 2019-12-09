package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.ljlin233.user.dao.UserAuthsDao;
import cn.ljlin233.user.dao.UserRoleDao;
import cn.ljlin233.user.entity.UserAuths;
import cn.ljlin233.user.entity.UserToken;
import cn.ljlin233.user.service.UserLoginService;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.exception.entity.DataCheckedException;

/**
 * UserLoginServiceImpl
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    private UserAuthsDao userAuthsDao;

    private UserTokenService userTokenService;

    private UserRoleDao userRoleDao;

    public UserLoginServiceImpl() {}

    @Autowired
    public UserLoginServiceImpl(UserAuthsDao userAuthsDao, UserTokenService userTokenService, UserRoleDao userRoleDao) {
        this.userAuthsDao = userAuthsDao;
        this.userTokenService = userTokenService;
        this.userRoleDao = userRoleDao;
    }

    @Override
    public UserToken userLogin(String identifier, String credential) {

        UserAuths userAuths = userAuthsDao.getUserAuthsByidentifier(identifier);
        if (userAuths == null) {
            throw new DataCheckedException("登录账号错误");
        }
        // 把用户密码加密
        String md5Credential = DigestUtils.md5DigestAsHex(credential.getBytes());

        if (!userAuths.getCredential().equals(md5Credential)) {
            throw new DataCheckedException("密码错误");
        }

        UserToken userToken = new UserToken();
        // 获取并存储userId
        int userId = userAuths.getUserId();
        userToken.setUserId(userId);
        // 获取并存储token
        String token = userTokenService.addToken(userId);
        userToken.setToken(token);
        // 获取并存储role
        //List<String> role = userRoleDao.getUserRoleByUserId(userId);
        //userToken.setUserRole(role);

        return userToken;
    }
}