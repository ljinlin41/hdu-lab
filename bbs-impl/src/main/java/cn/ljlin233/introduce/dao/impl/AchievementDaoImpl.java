package cn.ljlin233.introduce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.introduce.dao.AchievementDao;
import cn.ljlin233.introduce.dao.mapper.AchievementMapper;
import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 19:50
 */
public class AchievementDaoImpl implements AchievementDao {

    @Autowired
    private AchievementMapper achievementMapper;

    @Override
    public void addAchievement(Achievement achievement) {
        achievementMapper.insertSelective(achievement);
    }

    @Override
    public Page<Achievement> getAllAchievements() {

        List<Achievement> achievementList = achievementMapper.selectAll();
        PageInfo<Achievement> achievementPageInfo = new PageInfo<>(achievementList);

        return Page.<Achievement>builder().totalNum(achievementPageInfo.getTotal()).data(
            new ArrayList<>(achievementList)).build();
    }

    @Override
    public Page<Achievement> getAchievementsPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Achievement> achievementList = achievementMapper.selectAll();
        PageInfo<Achievement> achievementPageInfo = new PageInfo<>(achievementList);

        return Page.<Achievement>builder().totalNum(achievementPageInfo.getTotal())
            .pageNum(pageNum)
            .pageSize(pageSize)
            .data(new ArrayList<>(achievementList))
            .build();
    }

    @Override
    public Achievement getAchievementById(int id) {
        return achievementMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Achievement> searchAchievements(String keywords, int pageNum, int pageSize) {

        Example example = new Example(Achievement.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andLike("title", "%" + keywords + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<Achievement> achievementList = achievementMapper.selectByExample(example);

        PageInfo<Achievement> achievementPageInfo = new PageInfo<>(achievementList);

        return Page.<Achievement>builder().totalNum(achievementPageInfo.getTotal())
            .pageSize(pageSize)
            .pageNum(pageNum)
            .data(new ArrayList<>(achievementList))
            .build();
    }

    @Override
    public void updateAchievement(Achievement achievement) {
        achievementMapper.updateByPrimaryKeySelective(achievement);
    }

    @Override
    public void deleteAchievement(Achievement achievement) {

        achievementMapper.delete(achievement);
    }
}
