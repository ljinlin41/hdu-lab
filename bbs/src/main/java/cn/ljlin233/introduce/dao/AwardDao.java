package cn.ljlin233.introduce.dao;

import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * AwardDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AwardDao {

    /**
     * 获取所有奖项
     *
     * @return 奖项列表
     */
    Page<Award> getAllAwards();

    /**
     * 按页获取所有奖项
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 奖项列表
     */
    Page<Award> getAwardsPage(int pageNum, int pageSize);

    /**
     * 按Award Id获取奖项信息
     *
     * @param id Award Id
     * @return Award
     */
    Award getAwardById(int id);

    /**
     * 按标题搜索奖项
     *
     * @param keywords 搜索关键字
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 奖项列表
     */
    Page<Award> searchAwards(String keywords, int pageNum, int pageSize);

    /**
     * 添加一个奖项
     *
     * @param award 奖项
     */
    void addAward(Award award);

    /**
     * 更新一个奖项
     *
     * @param award 奖项
     */
    void updateAward(Award award);

    /**
     * 更新奖项
     *
     * @param award award
     * @param example example
     */
    void updateAwardByExample(Award award, Example example);
    /**
     * 删除一个奖项
     *
     * @param award 奖项
     */
    void deleteAward(Award award);
}