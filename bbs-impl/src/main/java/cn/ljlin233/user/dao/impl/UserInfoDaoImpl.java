package cn.ljlin233.user.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.dao.mapper.UserInfoMapper;
import cn.ljlin233.user.entity.UserInfo;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:04
 */
@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void addUserInfo(String account, String email, String registerTime, String activeId) {

    }

    @Override
    public void deleteUserInfo(int id) {

    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

    }

    @Override
    public List<UserInfo> getAllUserInfo(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return userInfo;
    }

    @Override
    public int getUserIdByAccount(String account) {
        return 0;
    }

    @Override
    public int getUserIdByActiveId(String activeId) {
        return 0;
    }

    @Override
    public boolean existsAccount(String account) {
        return false;
    }

    @Override
    public boolean existsEmail(String email) {
        return false;
    }

    @Override
    public boolean existsPhone(String phone) {
        return false;
    }

    @Override
    public boolean isActive(String account) {
        return false;
    }

    @Override
    public void updateUsername(String tableName, String nameCol, String newName, String idCol, int userId) {

    }
}
