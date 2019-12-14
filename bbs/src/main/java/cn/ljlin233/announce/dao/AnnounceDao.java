package cn.ljlin233.announce.dao;

import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * AnnounceDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AnnounceDao {

    /**
     * 获取公告
     *
     * @return 全部公告列表
     */
    Page<Announce> getAllAnnounces();

    /**
     * 按页获取公告
     *
     * @param pageNum 获取第N页
     * @param pageSize 每页大小
     * @return 获取的公告列表
     */
    Page<Announce> getAnnouncesByPage(int pageNum, int pageSize);

    /**
     * 按公告Id获取公告
     *
     * @param id 公告Id
     * @return 公告信息
     */
    Announce getAnnounceById(int id);

    /**
     * 按标题查询公告
     *
     * @param keywords 查询标题
     * @param pageNum 获取第N页查询结果
     * @param pageSize 每页大小
     * @return 查询结果列表
     */
    Page<Announce> searchAnnounce(String keywords, int pageNum, int pageSize);

    /**
     * 增加公告
     *
     * @param announce 公告
     */
    void addAnnounce(Announce announce);

    /**
     * 修改公告
     *
     * @param announce 公告
     */
    void updateAnnounce(Announce announce);

    void updateAnnounceByExample(Announce announce, Example example);
    /**
     * 删除公告
     *
     * @param announce 公告
     */
    void deleteAnnounce(Announce announce);
}