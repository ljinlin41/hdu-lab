package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserActiveService;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * UserActiveServiceImpl
 */
@Service
public class UserActiveServiceImpl implements UserActiveService {

    private UserInfoDao userInfoDao;

    public UserActiveServiceImpl() {}

    ;

    @Autowired
    public UserActiveServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void activeUser(String activeId) {

        try {
            int id = userInfoDao.getUserIdByActiveId(activeId);

            UserInfo userInfo = new UserInfo();
            userInfo.setId(id);
            userInfo.setActive(1);

            userInfoDao.updateUserInfo(userInfo);
        } catch (Exception e) {
            throw new SystemException("账号激活失败", e.getMessage());
        }
    }

}