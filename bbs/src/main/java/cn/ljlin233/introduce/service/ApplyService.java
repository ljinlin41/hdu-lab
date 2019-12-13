package cn.ljlin233.introduce.service;

import cn.ljlin233.introduce.dto.AcceptApplyRequestDto;
import cn.ljlin233.introduce.dto.InsertApplyRequestDto;
import cn.ljlin233.introduce.dto.RejectApplyRequestDto;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.util.Page;

/**
 * ApplyService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface ApplyService {

    /**
     * 添加一个申请
     *
     * @param request request
     */
    void addApply(InsertApplyRequestDto request);

    /**
     * 根据申请Id获取申请
     *
     * @param id 申请Id
     * @return 申请
     */
    Apply getApplyById(int id);

    /**
     * 根据用户Id获取所有申请
     *
     * @param userId 用户Id
     * @return 申请列表
     */
    Page<Apply> getApplyByUserId(int userId);


    /**
     * 获取教师所有未处理的申请表
     *
     * @param teacherId 教师Id
     * @return 申请列表
     */
    Page<Apply> getPendingApply(int teacherId);

    /**
     * 接受申请
     *
     * @param request request
     */
    void acceptApply(AcceptApplyRequestDto request);

    /**
     * 拒绝申请
     *
     * @param request request
     */
    void rejectApply(RejectApplyRequestDto request);
}