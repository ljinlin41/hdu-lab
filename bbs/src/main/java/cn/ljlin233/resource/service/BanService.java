package cn.ljlin233.resource.service;

import java.util.List;

import cn.ljlin233.resource.entity.Ban;

/**
 * BanService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface BanService {

    /**
     * 获取Ban列表
     *
     * @return Ban列表
     */
    List<Ban> getAllBanUser();

    /**
     * 获取Ban用户总数
     *
     * @return Ban用户总数
     */
    int getAllBanUserCount();

    /**
     * 按页获取Ban用户
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return Ban用户列表
     */
    List<Ban> getBanUserByPage(int pageNum, int pageSize);

    /**
     * 增加一个Ban用户
     *
     * @param banId 被Ban用户Id
     * @param addId 添加用户Id
     */
    void addBanUser(int banId, int addId);

    /**
     * 删除一个Ban用户
     *
     * @param id Band Id
     */
    void deleteBanUser(int id);

}