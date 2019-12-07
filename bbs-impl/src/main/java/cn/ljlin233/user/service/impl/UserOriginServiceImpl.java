package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.user.dao.UserOriginDao;
import cn.ljlin233.user.service.UserOriginService;

/**
 * UserOriginServiceImpl
 */
@Service
public class UserOriginServiceImpl implements UserOriginService {

    private UserOriginDao userOriginDao;

    public UserOriginServiceImpl() {}

    @Autowired
    public UserOriginServiceImpl(UserOriginDao userOriginDao) {
        this.userOriginDao = userOriginDao;
    }

    @Override
    public void addUserOrigin(String account) {
        userOriginDao.addUserOrigin(account);
    }

    @Override
    public boolean checkUserOrigin(String account) {
        boolean count = userOriginDao.existsAccount(account);
        return count;
    }

    @Override
    public void deleteUserOrigin(String account) {
        userOriginDao.deleteUserOrigin(account);
    }

}