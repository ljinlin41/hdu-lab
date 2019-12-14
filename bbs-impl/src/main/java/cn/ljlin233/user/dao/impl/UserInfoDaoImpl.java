package cn.ljlin233.user.dao.impl;

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
    public void addUserInfo(UserInfo userInfo) {
        userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public void deleteUserInfo(int id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUserInfoByPrimaryKey(UserInfo userInfo) {

        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public UserInfo getOneUserInfo(UserInfo userInfo) {
        return userInfoMapper.selectOne(userInfo);
    }



    @Override
    public int getUserIdByActiveId(String activeId) {
        return 0;
    }


    @Override
    public boolean existsEmail(String email) {

        UserInfo userInfo = UserInfo.builder().email(email).build();

        int count = userInfoMapper.selectCount(userInfo);

        return count != 0;
    }

    @Override
    public boolean existsPhone(String phone) {

        UserInfo userInfo = UserInfo.builder().phone(phone).build();

        int count = userInfoMapper.selectCount(userInfo);

        return count != 0;
    }

}
