package cn.ljlin233.introduce.dao;

import java.util.List;

import cn.ljlin233.introduce.entity.Award;

/**
 * AwardDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AwardDao {

    /**
     * 添加一个奖项
     *
     * @param award 奖项
     */
    void addAward(Award award);

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
     * 按Award Id获取奖项信息
     *
     * @param id Award Id
     * @return Award
     */
    Award getAwardById(int id);

    /**
     * 增加一个奖项的访问数
     *
     * @param id Award Id
     */
    void addVisitCount(int id);

    /**
     * 获取所有奖项数量
     *
     * @return 奖项数量
     */
    int getAwardCount();

    /**
     * 按标题搜索奖项
     *
     * @param keywords 搜索关键字
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 奖项列表
     */
    List<Award> searchAwards(String keywords, int pageNum, int pageSize);

    /**
     * 获取搜索结果数量
     *
     * @param keywords 搜索关键字
     * @return 结果数量
     */
    int getSearchCount(String keywords);

    /**
     * 更新一个奖项
     *
     * @param award 奖项
     */
    void updateAward(Award award);

    /**
     * 删除一个奖项
     *
     * @param id 奖项Id
     */
    void deleteAward(int id);
}