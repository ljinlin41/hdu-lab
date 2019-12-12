package cn.ljlin233.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.ljlin233.user.dao.UserAuthsDao;
import cn.ljlin233.user.dao.UserRoleDao;
import cn.ljlin233.user.dto.UserLoginRequestDto;
import cn.ljlin233.user.entity.UserAuths;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.entity.UserRole;
import cn.ljlin233.user.entity.UserToken;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.user.service.UserLoginService;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.common.TokenUtil;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * UserLoginServiceImpl
 */
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserAuthsDao userAuthsDao;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 校验登录信息是否正确，并把UserToken存入Redis
     *
     * @param request 请求
     * @return token
     */
    @Override
    public UserToken userLogin(UserLoginRequestDto request) {

        // TODO token校验刷新时间

        int userId = checkLogin(request);
        String token = TokenUtil.getToken();
        List<UserRole> userRoleList = userRoleDao.getUserRoleByUserId(userId);

        List<String> roleList = userRoleList.stream().collect(ArrayList::new, (list, userRole) -> {
            String role = userRole.getRole();
            list.add(role);
        }, ArrayList::addAll);

        UserInfo userInfo = userInfoService.getUserInfo(userId);

        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setNickName(userInfo.getNickname());
        userToken.setToken(token);
        userToken.setRole(roleList);

        userTokenService.addToken(userToken);

        return userToken;
    }

    /**
     * 验证账号密码是否正确
     *
     * @param request 账号密码Dto
     * @return 用户id
     */
    private int checkLogin(UserLoginRequestDto request) {

        UserAuths userAuths = userAuthsDao.getUserAuthsByidentifier(request.getIdentifier());
        if (userAuths == null) {
            throw new DataCheckedException("登录账号错误");
        }
        // 把用户密码加密
        String md5Credential = DigestUtils.md5DigestAsHex(request.getCredential().getBytes());

        if (!userAuths.getCredential().equals(md5Credential)) {
            throw new DataCheckedException("密码错误");
        }

        return userAuths.getId();
    }
}