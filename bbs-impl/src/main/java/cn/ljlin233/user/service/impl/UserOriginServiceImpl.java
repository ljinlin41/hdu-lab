package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.user.dao.UserOriginDao;
import cn.ljlin233.user.dto.DeleteUserOriginRequestDto;
import cn.ljlin233.user.dto.InsertUserOriginRequestDto;
import cn.ljlin233.user.entity.UserOrigin;
import cn.ljlin233.user.service.UserOriginService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.exception.entity.DataCheckedException;

/**
 * UserOriginServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
public class UserOriginServiceImpl implements UserOriginService {

    @Autowired
    private UserOriginDao userOriginDao;


    @Override
    public void addUserOrigin(InsertUserOriginRequestDto request) {

        checkUserOrigin(request.getAccount());

        UserOrigin userOrigin = UserOrigin.builder().account(request.getAccount()).build();

        userOriginDao.addUserOrigin(userOrigin);
    }


    @Override
    public void deleteUserOrigin(DeleteUserOriginRequestDto request) {

        UserOrigin userOrigin = UserOrigin.builder().account(request.getAccount()).build();

        userOriginDao.deleteUserOrigin(userOrigin);
    }


    @Override
    public Page<UserOrigin> getUserOriginByPage(int pageNum, int pageSize) {

        return userOriginDao.getUserOriginByPage(pageNum, pageSize);
    }

    @Override
    public boolean existsAccount(String account) {

        UserOrigin userOrigin = UserOrigin.builder().account(account).build();
        userOrigin = userOriginDao.getOneUserOrigin(userOrigin);
        return userOrigin != null;
    }

    private void checkUserOrigin(String account) {

        boolean accountExist = existsAccount(account);
        if (accountExist) {
            throw new DataCheckedException("原始账号已存在: " + account);
        }
    }
}