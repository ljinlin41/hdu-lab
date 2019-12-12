package cn.ljlin233.introduce.dao;

import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.util.Page;

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
    Page<Achievement> getAllAchievements();

    /**
     * 按页获取成果
     *
     * @param pageNum 第N页
     * @param pageSize 没页大小
     * @return 成果列表
     */
    Page<Achievement> getAchievementsPage(int pageNum, int pageSize);

    /**
     * 根据Id获取成果信息
     *
     * @param id 成果Id
     * @return achievement
     */
    Achievement getAchievementById(int id);

    /**
     * 按标题搜索成果
     *
     * @param keywords 标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果
     */
    Page<Achievement> searchAchievements(String keywords, int pageNum, int pageSize);

    /**
     * 更新一个成果
     *
     * @param achievement 成果
     */
    void updateAchievement(Achievement achievement);

    /**
     * 删除一个成果
     *
     * @param achievement 成果
     */
    void deleteAchievement(Achievement achievement);
}