package cn.ljlin233.authorization.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import cn.ljlin233.authorization.dao.UserAuthsDao;
import cn.ljlin233.authorization.dao.UserRoleDao;
import cn.ljlin233.authorization.dto.RegisterUserRequestDto;
import cn.ljlin233.authorization.dto.UserLoginRequestDto;
import cn.ljlin233.authorization.entity.UserAuths;
import cn.ljlin233.authorization.entity.UserRole;
import cn.ljlin233.authorization.entity.UserToken;
import cn.ljlin233.authorization.service.AuthorizationService;
import cn.ljlin233.authorization.service.UserAuthService;
import cn.ljlin233.authorization.service.UserRoleService;
import cn.ljlin233.authorization.service.UserTokenService;
import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.dao.UserOriginDao;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.common.DateUtil;
import cn.ljlin233.util.common.TokenUtil;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;
import cn.ljlin233.util.verification.service.VerificationService;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 22:08
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private UserAuthsDao userAuthsDao;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private UserOriginDao userOriginDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private JavaMailSender javaMailSender;

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

        // 将Token存入Redis

        return storeToken(userId);
    }

    @Override
    public void registerUser(RegisterUserRequestDto request) {

        // 检查验证码 TODO
        if (!verificationService.checkVerification(request.getVerificationId(), request.getVerificationCode())) {
            throw new DataCheckedException("验证码错误");
        }

        // 检查注册账号是否在起源表
        if (!userOriginDao.existsAccount(request.getAccount())) {
            throw new DataCheckedException("此账号未在原始名单");
        }

        // 检查账号是否已注册
        UserInfo userInfo = userInfoService.getUserInfoByAccount(request.getAccount());
        if (userInfo != null && userInfo.getActive() == 1) {

            throw new DataCheckedException("此账号已注册");
        } else if (userInfo != null && userInfo.getActive() == 0) {

            userInfoService.deleteUser(userInfo.getId());
        }

        // 检查邮箱是否使用
        if (userInfoDao.existsEmail(request.getEmail())) {
            throw new DataCheckedException("此邮箱已注册");
        }

        // 密码MD5加密
        String md5Password = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        // 激活码
        String activeId = UUID.randomUUID().toString().replace("-", "");

        // 数据库存储
        try {

            userInfo = UserInfo.builder().account(request.getAccount()).email(request.getEmail()).registerTime(
                DateUtil.getNow()).activeId(activeId).build();

            userInfoDao.addUserInfo(userInfo);

            userInfo = userInfoService.getUserInfoByAccount(request.getAccount());
            int userId = userInfo.getId();

            userAuthService.addUserAuth(userId, "account", request.getAccount(), md5Password);
            userAuthService.addUserAuth(userId, "email", request.getEmail(), md5Password);

            userRoleService.addUserRole(userId, request.getRole());

        } catch (Exception e) {
            throw new SystemException("数据库添加用户失败", e.getMessage());
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1329540850@qq.com");
        message.setTo(request.getEmail());
        message.setSubject("实验室账号激活链接");
        message.setText("127.0.0.1/lab/user?activeId=" + activeId);

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new SystemException("激活邮件发送失败，重新注册", e.getMessage());
        }

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

    /**
     * 生成UserToken并存入redis
     *
     * @param userId userId
     * @return UserToken
     */
    private UserToken storeToken(int userId) {

        String token = TokenUtil.getToken();
        List<UserRole> userRoleList = userRoleDao.getUserRoleByUserId(userId);

        List<String> roleList = userRoleList.stream().collect(ArrayList::new, (list, userRole) -> {
            String role = userRole.getRole();
            list.add(role);
        }, ArrayList::addAll);

        UserInfo userInfo = userInfoService.getUserInfoByUserId(userId);

        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setNickName(userInfo.getNickname());
        userToken.setToken(token);
        userToken.setRole(roleList);

        userTokenService.addToken(userToken);

        return userToken;
    }

}
