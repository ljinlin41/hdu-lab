package cn.ljlin233.introduce.dao;

import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.util.Page;

/**
 * DepartmentDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface DepartmentDao {


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
     * 根据部门Id获取部门信息
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
     * @return 部门列表
     */
    Page<Department> searchDepartments(String keywords, int pageNum, int pageSize);

    /**
     * 添加一个部门
     *
     * @param department 部门
     */
    void addDepartment(Department department);

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

}