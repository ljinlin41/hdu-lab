package cn.ljlin233.introduce.service;

import cn.ljlin233.introduce.dto.InsertAchievementRequestDto;
import cn.ljlin233.introduce.dto.UpdateAchievementRequestDto;
import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.util.Page;

/**
 * AchievementService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AchievementService {

    /**
     * 添加一个成果
     *
     * @param request 请求
     */
    void addAchievement(InsertAchievementRequestDto request);

    /**
     * 获取所有的成果
     *
     * @return 成果列表
     */
    Page<Achievement> getAllAchievements();

    /**
     * 按页获取所有的成果
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成果列表
     */
    Page<Achievement> getAchievementsPage(int pageNum, int pageSize);

    /**
     * 按成果Id获取成果
     *
     * @param id 成果Id
     * @return 成果
     */
    Achievement getAchievementById(int id);


    /**
     * 按标题搜索成果
     *
     * @param keywords 搜索标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成果列表
     */
    Page<Achievement> searchAchievements(String keywords, int pageNum, int pageSize);

    /**
     * 更新成果
     *
     * @param request 请求
     */
    void updateAchievement(UpdateAchievementRequestDto request);

    /**
     * 删除成果
     *
     * @param id 成果Id
     */
    void deleteAchievement(int id);

}