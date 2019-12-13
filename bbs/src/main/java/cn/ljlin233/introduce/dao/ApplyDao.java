package cn.ljlin233.introduce.dao;

import java.util.Collection;

import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.util.Page;

/**
 * ApplyDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface ApplyDao {

    /**
     * 添加一个申请
     *
     * @param apply 申请
     */
    void addApplys(Apply apply);

    /**
     * 根据申请Id获取一个申请
     *
     * @param id apply Id
     * @return 申请
     */
    Apply getApplyById(int id);

    /**
     * 更新一个申请
     *
     * @param apply 申请
     */
    void updateApply(Apply apply);

    /**
     * 根据用户Id获取所有申请
     *
     * @param userId 用户Id
     * @return 用户申请列表
     */
    Page<Apply> getApplyByUserId(int userId);

    /**
     * 根据部门Id集合获取所有未处理的申请
     *
     * @param departmentIds 部门Id集合
     * @return 申请列表
     */
    Page<Apply> getPendingApply(Collection<Integer> departmentIds);

}