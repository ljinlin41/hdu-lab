package cn.ljlin233.introduce.service;

import cn.ljlin233.introduce.dto.InsertAwardRequestDto;
import cn.ljlin233.introduce.dto.UpdateAwardRequestDto;
import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.util.Page;

/**
 * AwardService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface AwardService {


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
     * 按奖项Id获取奖项
     *
     * @param id 奖项Id
     * @return 奖项
     */
    Award getAwardById(int id);


    /**
     * 按标题搜索奖项
     *
     * @param keywords 搜索标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 奖项列表
     */
    Page<Award> searchAwards(String keywords, int pageNum, int pageSize);


    /**
     * 添加一个奖项
     *
     * @param request request
     */
    void addAward(InsertAwardRequestDto request);


    /**
     * 更新一个奖项
     *
     * @param request request
     */
    void updateAward(UpdateAwardRequestDto request);

    void updateNickname(int userId, String name);

    /**
     * 根据奖项Id删除一个奖项
     *
     * @param id 奖项Id
     */
    void deleteAward(int id);

}