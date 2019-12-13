package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.dto.UpdateUserInfoRequestDto;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
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


    @Override
    public UserInfo getUserInfo(int id) {

        return userInfoDao.getUserInfoById(id);
    }


    @Override
    public void updateUserInfo(UpdateUserInfoRequestDto request) {

        checkUpdate(request);

        if (request.getName() != null) {
            updateNickname(request.getId(), request.getName());
        }

        UserInfo userInfo = UserInfo.builder().id(request.getId()).nickname(request.getName()).introduction(
            request.getDescription()).profilePicture(request.getPicture()).email(request.getEmail()).phone(
            request.getPhone()).build();

        try {
            userInfoDao.updateUserInfo(userInfo);
        } catch (Exception e) {
            throw new SystemException("更新用户信息失败", e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            userInfoDao.deleteUserInfo(id);
        } catch (Exception e) {
            throw new SystemException("删除用户失败", e.getMessage());
        }
    }

    private void checkUpdate(UpdateUserInfoRequestDto request) {

        // 检查邮箱
        if (request.getEmail() != null) {

            boolean emailExist = userInfoDao.existsEmail(request.getEmail());

            if (emailExist) {
                throw new DataCheckedException("邮箱已注册");
            }
        }

        // 检查手机
        if (request.getPhone() != null) {

            boolean phoneExist = userInfoDao.existsPhone(request.getPhone());

            if (phoneExist) {
                throw new DataCheckedException("手机已注册");
            }
        }
    }

    private void updateNickname(int userId, String name) {

        try {

            userInfoDao.updateUsername(userId, name);
        } catch (Exception e) {
            throw new SystemException("更新用户名失败", e.getMessage());
        }

    }
}