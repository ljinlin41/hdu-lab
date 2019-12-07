package cn.ljlin233.introduce.dao;

import java.util.List;

import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.introduce.entity.Member;

/**
 * DepartmentDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface DepartmentDao {

    /**
     * 添加一个部门
     *
     * @param department 部门
     */
    void addDepartment(Department department);

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
     * 根据部门Id获取部门信息
     *
     * @param id 部门Id
     * @return 部门
     */
    Department getDepartmentById(int id);

    /**
     * 获取部门数量
     *
     * @return 部门数量
     */
    int getDepartmentCount();

    /**
     * 搜索部门名称
     *
     * @param keywords 部门名称
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 部门列表
     */
    List<Department> searchDepartments(String keywords, int pageNum, int pageSize);

    /**
     * 获取搜索结果数量
     *
     * @param keywords 搜索名称
     * @return 结果数量
     */
    int getSearchCount(String keywords);

    /**
     * 更新一个部门
     *
     * @param department 部门
     */
    void updateDepartment(Department department);

    /**
     * 根据部门Id删除一个部门
     *
     * @param id 部门Id
     */
    void deleteDepartment(int id);

    /**
     * 根据成员Id删除一个部门成员
     *
     * @param id 成员Id
     */
    void deleteDepartmentMember(int id);

    /**
     * 按页获取部门成员
     *
     * @param departmentId 部门Id
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成员列表
     */
    List<Member> getDepartmentMembers(int departmentId, int pageNum, int pageSize);

    /**
     * 获取部门成员总数
     *
     * @param departmentId 部门Id
     * @return 部门成员总数
     */
    int getMembersCount(int departmentId);

}