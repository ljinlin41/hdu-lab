package cn.ljlin233.introduce.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.AwardDao;
import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.introduce.service.AwardService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * AwardServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AwardServiceImpl implements AwardService {

    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private AwardDao awardDao;

    private UserInfoService userInfoService;

    public AwardServiceImpl() {}

    @Autowired
    public AwardServiceImpl(AwardDao awardDao, UserInfoService userInfoService) {
        this.awardDao = awardDao;
        this.userInfoService = userInfoService;
    }

    @Override
    public void addAward(String title, String content, Integer userId) {
        if (title == null || title.length() == 0) {
            throw new DataCheckedException("标题不能为空!");
        }
        if (content == null || content.length() == 0) {
            throw new DataCheckedException("内容不能为空!");
        }
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        if (userId == null) {
            throw new DataCheckedException("账号不存在!");
        }

        Award award = new Award();
        award.setTitle(title);
        award.setContent(content);
        award.setUpUserId(userId);
        award.setNickname(userInfo.getNickname());
        award.setDatetime(dateformat.format(new Date()));

        try {
            awardDao.addAward(award);
        } catch (Exception e) {
            throw new SystemException("服务器存储奖项失败", e.getMessage());
        }
    }

    @Override
    public List<Award> getAllAwards() {
        List<Award> all = null;
        try {
            all = awardDao.getAllAwards();
        } catch (Exception e) {
            throw new SystemException("服务器获取所有奖项失败!", e.getMessage());
        }
        return all;
    }

    @Override
    public List<Award> getAwardsPage(int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Award> result = null;
        try {
            result = awardDao.getAwardsPage(start, pageNum);
        } catch (Exception e) {
            throw new SystemException("服务器获取奖项失败!", e.getMessage());
        }
        return result;
    }

    @Override
    public Award getAwardById(int id) {
        Award result = null;
        try {
            result = awardDao.getAwardById(id);
        } catch (Exception e) {
            throw new SystemException("服务器获取奖项失败!", e.getMessage());
        }
        // 访问次数+1
        addVisitCount(id);

        return result;
    }

    @Override
    public void addVisitCount(int id) {
        try {
            awardDao.addVisitCount(id);
        } catch (Exception e) {
            throw new SystemException("奖项访问数错误!", e.getMessage());
        }
    }

    @Override
    public int getAwardCount() {
        int count = 0;
        try {
            count = awardDao.getAwardCount();
        } catch (Exception e) {
            throw new SystemException("读取奖项数量错误!", e.getMessage());
        }
        return count;
    }

    @Override
    public List<Award> searchAwards(String keywords, int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Award> result = null;
        try {
            result = awardDao.searchAwards(keywords, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("服务器搜索奖项失败!", e.getMessage());
        }
        return result;
    }

    @Override
    public int getSearchCount(String keywords) {
        int count = 0;
        try {
            count = awardDao.getSearchCount(keywords);
        } catch (Exception e) {
            throw new SystemException("读取奖项搜索数量错误!", e.getMessage());
        }
        return count;
    }

    @Override
    public void updateAward(Award award) {
        try {
            awardDao.updateAward(award);
        } catch (Exception e) {
            throw new SystemException("更新奖项失败!", e.getMessage());
        }
    }

    @Override
    public void deleteAward(int id) {
        try {
            awardDao.deleteAward(id);
        } catch (Exception e) {
            throw new SystemException("删除奖项失败!", e.getMessage());
        }
    }

}