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
import cn.ljlin233.util.exception.entity.SystemException;

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
        Page<Award> all;
        try {
            all = awardDao.getAllAwards();
        } catch (Exception e) {
            throw new SystemException("服务器获取所有奖项失败!", e.getMessage());
        }
        return all;
    }

    @Override
    public Page<Award> getAwardsPage(int pageNum, int pageSize) {
        Page<Award> result;
        try {
            result = awardDao.getAwardsPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("服务器获取奖项失败!", e.getMessage());
        }
        return result;
    }

    @Override
    public Award getAwardById(int id) {
        Award result;
        try {
            result = awardDao.getAwardById(id);
        } catch (Exception e) {
            throw new SystemException("服务器获取奖项失败!", e.getMessage());
        }
        // 访问次数+1
        addVisitCount(result);

        return result;
    }


    @Override
    public Page<Award> searchAwards(String keywords, int pageNum, int pageSize) {

        Page<Award> result;
        try {
            result = awardDao.searchAwards(keywords, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("服务器搜索奖项失败!", e.getMessage());
        }
        return result;
    }


    @Override
    public void addAward(InsertAwardRequestDto request) {

        UserContext userContext = UserContextUtil.getUserContext();

        Award award = Award.builder().title(request.getTitle()).content(request.getContent()).upUserId(
            userContext.getId()).nickname(userContext.getNickName()).datetime(DateUtil.getNow()).build();

        try {
            awardDao.addAward(award);
        } catch (Exception e) {
            throw new SystemException("服务器存储奖项失败", e.getMessage());
        }
    }

    @Override
    public void updateAward(UpdateAwardRequestDto request) {
        try {

            Award award = Award.builder().id(request.getAwardId()).title(request.getTitle()).content(
                request.getContent()).build();

            awardDao.updateAward(award);
        } catch (Exception e) {
            throw new SystemException("更新奖项失败!", e.getMessage());
        }
    }

    @Override
    public void deleteAward(int id) {

        Award award = awardDao.getAwardById(id);

        try {
            awardDao.deleteAward(award);
        } catch (Exception e) {
            throw new SystemException("删除奖项失败!", e.getMessage());
        }
    }

    private void addVisitCount(Award award) {
        try {

            Award newAward = Award.builder().id(award.getId()).visitCount(award.getVisitCount() + 1).build();

            awardDao.updateAward(newAward);
        } catch (Exception e) {
            throw new SystemException("奖项访问数错误!", e.getMessage());
        }
    }

}