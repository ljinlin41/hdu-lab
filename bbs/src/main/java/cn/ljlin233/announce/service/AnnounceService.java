package cn.ljlin233.announce.service;

import cn.ljlin233.announce.dto.InsertAnnounceRequestDto;
import cn.ljlin233.announce.dto.UpdateAnnounceRequestDto;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.util.Page;

/**
 * AnnounceService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AnnounceService {

    /**
     * 获取所有通知
     *
     * @return 通知列表
     */
    Page<Announce> getAllAnnounces();

    /**
     * 按页获取通知
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 通知列表
     */
    Page<Announce> getAnnouncesByPage(int pageNum, int pageSize);

    /**
     * 按通知Id获取通知
     *
     * @param id 通知Id
     * @return 通知
     */
    Announce getAnnounceById(int id);

    /**
     * 按标题查询通知
     *
     * @param title 查询标题
     * @param pageNum 查询结果页数
     * @param pageSize 每页大小
     * @return 查询结果列表
     */
    Page<Announce> searchAnnounces(String title, int pageNum, int pageSize);

    /**
     * 添加一个通知
     *
     * @param request 请求
     */
    void addAnnounce(InsertAnnounceRequestDto request);

    /**
     * 更新一个通知
     *
     * @param request 请求
     */
    void updateAnnounce(UpdateAnnounceRequestDto request);

    /**
     * 删除一个通知
     *
     * @param id 通知Id
     */
    void deleteAnnounce(int id);

}