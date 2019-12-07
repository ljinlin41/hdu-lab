package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * UserInfoServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {

    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl() {}

    ;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao) {

        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = userInfoDao.getUserInfoById(id);
        return userInfo;
    }

    @Override
    public void updateNickname(int userId, String newname) {

        UserInfo user = new UserInfo();
        user.setId(userId);
        user.setNickname(newname);
        try {
            userInfoDao.updateUserInfo(user);

            // 研究成果表
            userInfoDao.updateUsername("intro_achievement", "up_nickname", "newname", "up_userid", userId);
            // 荣誉奖项表
            userInfoDao.updateUsername("intro_award", "up_nickname", "newname", "up_userid", userId);
            // 招聘信息表
            userInfoDao.updateUsername("intro_job", "up_nickname", "newname", "up_userid", userId);
            // 通知公告表
            userInfoDao.updateUsername("anno_announce", "up_nickname", "newname", "up_userid", userId);
            // 资源表
            userInfoDao.updateUsername("res_resource", "up_nickname", "newname", "up_userid", userId);
        } catch (Exception e) {
            throw new SystemException("更新用户名失败", e.getMessage());
        }

    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

        try {
            userInfoDao.updateUserInfo(userInfo);
            //System.out.println(userInfo.getIntroduction());
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
}