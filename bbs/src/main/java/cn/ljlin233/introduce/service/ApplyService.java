package cn.ljlin233.introduce.service;

import java.util.List;

import cn.ljlin233.introduce.entity.Apply;

/**
 * ApplyService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface ApplyService {

    /**
     * 添加一个申请
     *
     * @param userId 申请人Id
     * @param username 申请人姓名
     * @param applyType 申请类型(学生，教师)
     * @param departmentId 申请部门Id
     */
    void addApply(int userId, String username, String applyType, int departmentId);

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
    List<Apply> getApplyByUserId(int userId);

    /**
     * 更新申请表状态
     *
     * @param id 申请Id
     * @param applyStatus 申请表状态
     */
    void updateApply(int id, String applyStatus);

    /**
     * 获取用户所有未处理的申请表
     *
     * @param userId 用户Id
     * @return 申请列表
     */
    List<Apply> getUnhandleApply(int userId);

}