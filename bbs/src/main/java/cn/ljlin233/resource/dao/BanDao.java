package cn.ljlin233.resource.dao;

import java.util.List;

import cn.ljlin233.resource.entity.Ban;

/**
 * BanDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface BanDao {

    /**
     * 获取所有被Ban用户
     *
     * @return Ban列表
     */
    List<Ban> getAllBanUser();

    /**
     * 获取被Ban用户总数
     *
     * @return Ban用户总数
     */
    int getAllBanUserCount();

    /**
     * 按页获取被Ban用户
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return Ban用户列表
     */
    List<Ban> getBanUserByPage(int pageNum, int pageSize);

    /**
     * 添加一个Ban用户
     *
     * @param ban Ban
     */
    void addBanUser(Ban ban);

    /**
     * 删除一个Ban用户
     *
     * @param id Ban Id
     */
    void deleteBanUser(int id);

}