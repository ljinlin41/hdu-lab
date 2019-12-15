package cn.ljlin233.introduce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.AwardDao;
import cn.ljlin233.introduce.dto.InsertAwardRequestDto;
import cn.ljlin233.introduce.dto.UpdateAwardRequestDto;
import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.introduce.service.AwardService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.common.DateUtil;
import cn.ljlin233.util.common.UserContext;
import cn.ljlin233.util.common.UserContextUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * AwardServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AwardServiceImpl implements AwardService {

    @Autowired
    private AwardDao awardDao;


    @Override
    public Page<Award> getAllAwards() {

        return awardDao.getAllAwards();
    }

    @Override
    public Page<Award> getAwardsPage(int pageNum, int pageSize) {

        return awardDao.getAwardsPage(pageNum, pageSize);
    }

    @Override
    public Award getAwardById(int id) {

        Award result = awardDao.getAwardById(id);

        if (result != null) {
            // 访问次数+1
            addVisitCount(result);
        }

        return result;
    }


    @Override
    public Page<Award> searchAwards(String keywords, int pageNum, int pageSize) {

        return awardDao.searchAwards(keywords, pageNum, pageSize);
    }


    @Override
    public void addAward(InsertAwardRequestDto request) {

        UserContext userContext = UserContextUtil.getUserContext();

        Award award = Award.builder().title(request.getTitle()).content(request.getContent()).upUserId(
            userContext.getId()).nickname(userContext.getNickName()).datetime(DateUtil.getNow()).build();

        awardDao.addAward(award);

    }

    @Override
    public void updateAward(UpdateAwardRequestDto request) {

        Award award = Award.builder()
            .id(request.getAwardId())
            .title(request.getTitle())
            .content(request.getContent())
            .build();

        awardDao.updateAward(award);

    }

    @Override
    public void updateNickname(int userId, String name) {

        Award award = Award.builder().nickname(name).build();
        Example example = new Example(Award.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        awardDao.updateAwardByExample(award, example);
    }

    @Override
    public void deleteAward(int id) {

        Award award = awardDao.getAwardById(id);

        awardDao.deleteAward(award);

    }

    private void addVisitCount(Award award) {

        Award newAward = Award.builder().id(award.getId()).visitCount(award.getVisitCount() + 1).build();

        awardDao.updateAward(newAward);

    }

}