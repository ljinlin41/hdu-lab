package cn.ljlin233.introduce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.AchievementDao;
import cn.ljlin233.introduce.dto.InsertAchievementRequestDto;
import cn.ljlin233.introduce.dto.UpdateAchievementRequestDto;
import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.introduce.service.AchievementService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.common.DateUtil;
import cn.ljlin233.util.common.UserContext;
import cn.ljlin233.util.common.UserContextUtil;
import tk.mybatis.mapper.entity.Example;

/**
 * AchievementServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementDao achievementDao;

    @Override
    public void addAchievement(InsertAchievementRequestDto request) {

        UserContext userContext = UserContextUtil.getUserContext();

        int upId = userContext.getId();
        String nickName = userContext.getNickName();

        Achievement achievement = Achievement.builder().upUserId(upId).title(request.getTitle()).content(
            request.getContent()).upDate(DateUtil.getNow()).upNickname(nickName).build();

        achievementDao.addAchievement(achievement);
    }

    @Override
    public Page<Achievement> getAllAchievements() {
        return achievementDao.getAllAchievements();
    }

    @Override
    public Page<Achievement> getAchievementsPage(int pageNum, int pageSize) {

        return achievementDao.getAchievementsPage(pageNum, pageSize);
    }

    @Override
    public Page<Achievement> searchAchievements(String keywords, int pageNum, int pageSize) {

        return achievementDao.searchAchievements(keywords, pageNum, pageSize);
    }

    @Override
    public Achievement getAchievementById(int id) {
        Achievement result = achievementDao.getAchievementById(id);

        if (result != null) {
            // 访问次数+1
            addVisitCount(result);
        }

        return result;
    }


    @Override
    public void updateAchievement(UpdateAchievementRequestDto request) {

        Achievement achievement = Achievement.builder()
            .id(request.getAchievementId())
            .title(request.getTitle())
            .content(request.getContent())
            .build();

        achievementDao.updateAchievement(achievement);

    }

    @Override
    public void updateNickname(int userId, String nickname) {

        Achievement achievement = Achievement.builder().upNickname(nickname).build();
        Example example = new Example(Achievement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        achievementDao.updateAchievementByExample(achievement, example);
    }

    @Override
    public void deleteAchievement(int id) {

        Achievement achievement = achievementDao.getAchievementById(id);
        achievementDao.deleteAchievement(achievement);

    }

    private void addVisitCount(Achievement achievement) {

        Achievement newAchievement = Achievement.builder().id(achievement.getId()).visitCount(
            achievement.getVisitCount() + 1).build();

        achievementDao.updateAchievement(newAchievement);

    }

}