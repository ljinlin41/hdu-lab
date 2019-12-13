package cn.ljlin233.user.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.ljlin233.announce.dao.mapper.AnnounceMapper;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.introduce.dao.mapper.AchievementMapper;
import cn.ljlin233.introduce.dao.mapper.AwardMapper;
import cn.ljlin233.introduce.dao.mapper.JobMapper;
import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.resource.dao.mapper.ResourceMapper;
import cn.ljlin233.resource.entity.Resource;
import cn.ljlin233.user.dao.UserInfoDao;
import cn.ljlin233.user.dao.mapper.UserInfoMapper;
import cn.ljlin233.user.entity.UserInfo;
import tk.mybatis.mapper.entity.Example;

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
    public void addUserInfo(String account, String email, String registerTime, String activeId) {

    }

    @Override
    public void deleteUserInfo(int id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }


    @Override
    public UserInfo getUserInfoById(int id) {

        return userInfoMapper.selectByPrimaryKey(id);
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

    @Override
    public void updateUsername(int userId, String name) {

        // 研究成果表
        Achievement achievement = Achievement.builder().upNickname(name).build();
        Example example = new Example(Achievement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        achievementMapper.updateByExampleSelective(achievement, example);

        // 荣誉奖项表
        Award award = Award.builder().nickname(name).build();
        example = new Example(Award.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        awardMapper.updateByExampleSelective(award, example);

        // 招聘信息表
        Job job = Job.builder().upNickname(name).build();
        example = new Example(Job.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        jobMapper.updateByExampleSelective(job, example);

        // 通知公告表
        Announce announce = Announce.builder().upUserNickname(name).build();
        example = new Example(Announce.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        announceMapper.updateByExampleSelective(announce, example);

        // 资源表
        Resource resource = Resource.builder().upNickname(name).build();
        example = new Example(Resource.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        resourceMapper.updateByExampleSelective(resource, example);

    }
}
