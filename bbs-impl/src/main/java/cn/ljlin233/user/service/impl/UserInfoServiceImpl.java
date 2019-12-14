package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.authorization.service.UserAuthService;
import cn.ljlin233.authorization.service.UserRoleService;
import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.dto.UpdateUserInfoRequestDto;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.user.service.UserNicknameService;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * UserInfoServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserNicknameService userNicknameService;

    @Override
    public UserInfo getUserInfoByUserId(int id) {

        UserInfo userInfo = UserInfo.builder().id(id).build();

        return userInfoDao.getOneUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfoByAccount(String account) {

        UserInfo userInfo = UserInfo.builder().account(account).build();

        return userInfoDao.getOneUserInfo(userInfo);
    }

    @Override
    public void updateUserInfo(UpdateUserInfoRequestDto request) {

        checkUpdate(request);

        // 更新nickname
        if (request.getName() != null) {
            userNicknameService.updateNickname(request.getId(), request.getName());
        }

        UserInfo userInfo = UserInfo.builder().id(request.getId()).nickname(request.getName()).introduction(
            request.getDescription()).profilePicture(request.getPicture()).email(request.getEmail()).phone(
            request.getPhone()).build();

        try {
            userInfoDao.updateUserInfoByPrimaryKey(userInfo);
        } catch (Exception e) {
            throw new SystemException("更新用户信息失败", e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            userInfoDao.deleteUserInfo(id);
            userAuthService.deleteUserAuthByUserId(id);
            userRoleService.deleteUserRoleByUserId(id);

        } catch (Exception e) {
            throw new SystemException("删除用户失败", e.getMessage());
        }
    }

    /**
     * 此方法只更新了用户信息表的nickname
     */
    @Override
    public void updateNickname(int userId, String nickname) {
        UserInfo userInfo = UserInfo.builder().id(userId).nickname(nickname).build();

        userInfoDao.updateUserInfoByPrimaryKey(userInfo);
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoDao.addUserInfo(userInfo);
    }

    @Override
    public boolean existsEmail(String email) {

        UserInfo userInfo = UserInfo.builder().email(email).build();

        userInfo = userInfoDao.getOneUserInfo(userInfo);

        return userInfo != null;
    }

    @Override
    public boolean existsPhone(String phone) {
        UserInfo userInfo = UserInfo.builder().phone(phone).build();

        userInfo = userInfoDao.getOneUserInfo(userInfo);

        return userInfo != null;
    }

    private void checkUpdate(UpdateUserInfoRequestDto request) {

        // 检查邮箱
        if (request.getEmail() != null) {

            boolean emailExist = existsEmail(request.getEmail());
            if (emailExist) {
                throw new DataCheckedException("邮箱已注册");
            }
        }

        // 检查手机
        if (request.getPhone() != null) {

            boolean phoneExist = existsPhone(request.getPhone());
            if (phoneExist) {
                throw new DataCheckedException("手机已注册");
            }
        }
    }

}