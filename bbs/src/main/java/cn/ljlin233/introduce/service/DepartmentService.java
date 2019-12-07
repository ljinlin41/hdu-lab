package cn.ljlin233.introduce.service;

import java.util.List;

import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.introduce.entity.Member;

/**
 * DepartmentService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface DepartmentService {

    /**
     * 增加一个部门
     *
     * @param name 部门名称
     * @param description 部门描述
     */
    void addDepartment(String name, String description);

    /**
     * 获取所有部门
     *
     * @return 部门列表
     */
    List<Department> getAllDepartments();

    /**
     * 按页获取部门
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 部门列表
     */
    List<Department> getDepartmentsPage(int pageNum, int pageSize);

    /**
     * 根据部门Id获取部门
     *
     * @param id 部门Id
     * @return 部门
     */
    Department getDepartmentById(int id);

    /**
     * 获取部门总数
     *
     * @return 部门总数
     */
    int getDepartmentCount();

    /**
     * 搜索部门名称
     *
     * @param keywords 部门名称
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果列表
     */
    List<Department> searchDepartments(String keywords, int pageNum, int pageSize);

    /**
     * 部门搜索结果数
     *
     * @param keywords 部门名称
     * @return 搜索结果数
     */
    int getSearchCount(String keywords);

    /**
     * 更新部门
     *
     * @param department 部门
     */
    void updateDepartment(Department department);

    /**
     * 删除部门
     *
     * @param id 部门Id
     */
    void deleteDepartment(int id);

    /**
     * 按页获取部门成员
     *
     * @param id 部门Id
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成员列表
     */
    List<Member> getDepartmentMember(int id, int pageNum, int pageSize);

    /**
     * 获取部门成员数
     *
     * @param departmentId 部门Id
     * @return 部门成员数
     */
    int getMembersCount(int departmentId);
}