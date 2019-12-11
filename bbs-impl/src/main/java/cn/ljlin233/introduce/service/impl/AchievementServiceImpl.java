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
import cn.ljlin233.util.exception.entity.SystemException;

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
        Page<Achievement> all;
        try {
            all = achievementDao.getAllAchievements();
        } catch (Exception e) {
            throw new SystemException("服务器获取所有研究成果失败!", e.getMessage());
        }
        return all;
    }

    @Override
    public Page<Achievement> getAchievementsPage(int pageNum, int pageSize) {
        Page<Achievement> result;
        try {
            result = achievementDao.getAchievementsPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("服务器获取研究成果失败!", e.getMessage());
        }

        return result;
    }

    @Override
    public Page<Achievement> searchAchievements(String keywords, int pageNum, int pageSize) {

        Page<Achievement> result;
        try {
            result = achievementDao.searchAchievements(keywords, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("服务器搜索研究成果失败!", e.getMessage());
        }

        return result;
    }

    @Override
    public Achievement getAchievementById(int id) {
        Achievement result;
        try {
            result = achievementDao.getAchievementById(id);
        } catch (Exception e) {
            throw new SystemException("服务器获取研究成果失败!", e.getMessage());
        }
        // 访问次数+1
        addVisitCount(result);

        return result;
    }


    @Override
    public void updateAchievement(int id, UpdateAchievementRequestDto request) {
        try {
            Achievement achievement = Achievement.builder().id(id).title(request.getTitle()).content(
                request.getContent()).build();

            achievementDao.updateAchievement(achievement);
        } catch (Exception e) {
            throw new SystemException("更新研究成果失败!", e.getMessage());
        }
    }

    @Override
    public void deleteAchievement(int id) {
        try {
            achievementDao.deleteAchievement(id);
        } catch (Exception e) {
            throw new SystemException("删除研究成果失败!", e.getMessage());
        }
    }

    private void addVisitCount(Achievement achievement) {
        try {
            Achievement newAchievement = Achievement.builder().id(achievement.getId()).visitCount(
                achievement.getVisitCount() + 1).build();

            achievementDao.updateAchievement(newAchievement);
        } catch (Exception e) {
            throw new SystemException("研究成果访问数错误!", e.getMessage());
        }
    }

}