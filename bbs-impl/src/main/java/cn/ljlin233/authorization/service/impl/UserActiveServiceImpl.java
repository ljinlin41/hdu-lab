package cn.ljlin233.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.authorization.dao.UserActiveDao;
import cn.ljlin233.authorization.service.UserActiveService;
import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * UserActiveServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserActiveServiceImpl implements UserActiveService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserActiveDao userActiveDao;

    @Override
    public void activeUser(String activeId) {

        try {
            int id = userActiveDao.getUserIdByActive(activeId);

            UserInfo userInfo = UserInfo.builder().build();
            userInfo.setId(id);
            userInfo.setActive(1);

            userInfoDao.updateUserInfoByPrimaryKey(userInfo);
        } catch (Exception e) {
            throw new SystemException("账号激活失败", e.getMessage());
        }
    }

    @Override
    public void storeActive(String active, int userId) {
        userActiveDao.storeActive(active, userId);
    }

}