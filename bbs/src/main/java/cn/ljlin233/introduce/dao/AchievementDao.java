package cn.ljlin233.introduce.dao;

import java.util.List;

import cn.ljlin233.introduce.entity.Achievement;

/**
 * AchievementDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AchievementDao {

    /**
     * 增加一个成果
     *
     * @param achievement 成果
     */
    void addAchievement(Achievement achievement);

    /**
     * 获取所有成果
     *
     * @return 成果列表
     */
    List<Achievement> getAllAchievements();

    /**
     * 按页获取成果
     *
     * @param pageNum 第N页
     * @param pageSize 没页大小
     * @return 成果列表
     */
    List<Achievement> getAchievementsPage(int pageNum, int pageSize);

    /**
     * 根据Id获取成果信息
     *
     * @param id 成果Id
     * @return achievement
     */
    Achievement getAchievementById(int id);

    /**
     * 增加一个成果的访问数
     *
     * @param id 成果Id
     */
    void addVisitCount(int id);

    /**
     * 获取成果总数
     *
     * @return 成果总数
     */
    int getAchievementCount();

    /**
     * 按标题搜索成果
     *
     * @param keywords 标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果
     */
    List<Achievement> searchAchievements(String keywords, int pageNum, int pageSize);

    /**
     * 获取搜索结果数量
     *
     * @param keywords 搜索标题
     * @return 结果数量
     */
    int getSearchCount(String keywords);

    /**
     * 更新一个成果
     *
     * @param achievement 成果
     */
    void updateAchievement(Achievement achievement);

    /**
     * 删除一个成果
     *
     * @param id 成果Id
     */
    void deleteAchievement(int id);
}