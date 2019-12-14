package cn.ljlin233.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.announce.dao.mapper.AnnounceMapper;
import cn.ljlin233.introduce.dao.mapper.AchievementMapper;
import cn.ljlin233.introduce.dao.mapper.AwardMapper;
import cn.ljlin233.introduce.dao.mapper.JobMapper;
import cn.ljlin233.resource.dao.mapper.ResourceMapper;
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

    @Autowired
    private AchievementMapper achievementMapper;

    @Autowired
    private AwardMapper awardMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private AnnounceMapper announceMapper;

    @Autowired
    private ResourceMapper resourceMapper;

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
    public UserInfo getUserInfoById(int id) {

        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo getUserInfoByAccount(String account) {

        UserInfo userInfo = UserInfo.builder().account(account).build();

        return userInfoMapper.selectOne(userInfo);
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

    @Override
    public boolean isActive(String account) {
        return false;
    }

}
