package cn.ljlin233.introduce.service;

import cn.ljlin233.introduce.dto.InsertDepartmentRequestDto;
import cn.ljlin233.introduce.dto.UpdateDepartmentRequestDto;
import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.util.Page;

/**
 * DepartmentService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface DepartmentService {

    /**
     * 增加一个部门
     *
     * @param request request
     */
    void addDepartment(InsertDepartmentRequestDto request);

    /**
     * 获取所有部门
     *
     * @return 部门列表
     */
    Page<Department> getAllDepartments();

    /**
     * 按页获取部门
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 部门列表
     */
    Page<Department> getDepartmentsPage(int pageNum, int pageSize);

    /**
     * 根据部门Id获取部门
     *
     * @param id 部门Id
     * @return 部门
     */
    Department getDepartmentById(int id);


    /**
     * 搜索部门名称
     *
     * @param keywords 部门名称
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果列表
     */
    Page<Department> searchDepartments(String keywords, int pageNum, int pageSize);


    /**
     * 更新部门
     *
     * @param request request
     */
    void updateDepartment(UpdateDepartmentRequestDto request);


    /**
     * 删除部门
     *
     * @param id 部门Id
     */
    void deleteDepartment(int id);

}