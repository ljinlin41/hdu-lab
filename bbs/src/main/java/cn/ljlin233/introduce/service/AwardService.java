package cn.ljlin233.introduce.service;

import java.util.List;

import cn.ljlin233.introduce.entity.Award;

/**
 * AwardService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AwardService {

    /**
     * 添加一个奖项
     *
     * @param title 标题
     * @param content 正文
     * @param userId 用户Id
     */
    void addAward(String title, String content, Integer userId);

    /**
     * 获取所有奖项
     *
     * @return 奖项列表
     */
    List<Award> getAllAwards();

    /**
     * 按页获取所有奖项
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 奖项列表
     */
    List<Award> getAwardsPage(int pageNum, int pageSize);

    /**
     * 按奖项Id获取奖项
     *
     * @param id 奖项Id
     * @return 奖项
     */
    Award getAwardById(int id);

    /**
     * 增加一个奖项的访问数
     *
     * @param id 奖项Id
     */
    void addVisitCount(int id);

    /**
     * 获取奖项总数
     *
     * @return 奖项总数
     */
    int getAwardCount();

    /**
     * 按标题搜索奖项
     *
     * @param keywords 搜索标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 奖项列表
     */
    List<Award> searchAwards(String keywords, int pageNum, int pageSize);

    /**
     * 获取搜索结果数
     *
     * @param keywords 搜索标题
     * @return 搜索结果数
     */
    int getSearchCount(String keywords);

    /**
     * 更新一个奖项
     *
     * @param award 奖项
     */
    void updateAward(Award award);

    /**
     * 根据奖项Id删除一个奖项
     *
     * @param id 奖项Id
     */
    void deleteAward(int id);

}