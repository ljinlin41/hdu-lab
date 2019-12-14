package cn.ljlin233.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.authorization.dao.UserActiveDao;
import cn.ljlin233.authorization.service.UserActiveService;
import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.entity.UserInfo;

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

        int id = userActiveDao.getUserIdByActive(activeId);

        UserInfo userInfo = UserInfo.builder().id(id).active(1).build();

        userInfoDao.updateUserInfoByPrimaryKey(userInfo);

    }

    @Override
    public void storeActive(String active, int userId) {
        userActiveDao.storeActive(active, userId);
    }

}