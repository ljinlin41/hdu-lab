package cn.ljlin233.introduce.service;

import java.util.List;

import cn.ljlin233.introduce.entity.Achievement;

/**
 * AchievementService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AchievementService {

    /**
     * 添加一个成果
     *
     * @param title 标题
     * @param content 正文
     * @param userId 用户Id
     */
    void addAchievement(String title, String content, Integer userId);

    /**
     * 获取所有的成果
     *
     * @return 成果列表
     */
    List<Achievement> getAllAchievements();

    /**
     * 按页获取所有的成果
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成果列表
     */
    List<Achievement> getAchievementsPage(int pageNum, int pageSize);

    /**
     * 按成果Id获取成果
     *
     * @param id 成果Id
     * @return 成果
     */
    Achievement getAchievementById(int id);

    /**
     * 增加一个成果的访问数
     *
     * @param id 成果Id
     */
    void addVisitCount(int id);

    /**
     * 获取一个成果的访问数
     *
     * @return 成果Id
     */
    int getAchievementCount();

    /**
     * 按标题搜索成果
     *
     * @param keywords 搜索标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成果列表
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
     * 更新成果
     *
     * @param achievement 成果
     */
    void updateAchievement(Achievement achievement);

    /**
     * 删除成果
     *
     * @param id 成果Id
     */
    void deleteAchievement(int id);

}