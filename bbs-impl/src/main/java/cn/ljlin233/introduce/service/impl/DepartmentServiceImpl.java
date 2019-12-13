package cn.ljlin233.introduce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.DepartmentDao;
import cn.ljlin233.introduce.dto.InsertDepartmentRequestDto;
import cn.ljlin233.introduce.dto.UpdateDepartmentRequestDto;
import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.introduce.service.DepartmentService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * DepartmentServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public void addDepartment(InsertDepartmentRequestDto request) {

        Department department = Department.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();

        try {
            departmentDao.addDepartment(department);
        } catch (Exception e) {
            throw new SystemException("添加部门失败", e.getMessage());
        }

    }

    @Override
    public Page<Department> getAllDepartments() {

        Page<Department> all;
        try {
            all = departmentDao.getAllDepartments();
        } catch (Exception e) {
            throw new SystemException("获取所有部门失败", e.getMessage());
        }
        return all;
    }

    @Override
    public Page<Department> getDepartmentsPage(int pageNum, int pageSize) {

        Page<Department> all;
        try {
            all = departmentDao.getDepartmentsPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("按页获取部门失败", e.getMessage());
        }
        return all;
    }

    @Override
    public Department getDepartmentById(int id) {
        Department department;
        try {
            department = departmentDao.getDepartmentById(id);
        } catch (Exception e) {
            throw new SystemException("获取部门详情失败", e.getMessage());
        }

        return department;
    }


    @Override
    public Page<Department> searchDepartments(String keywords, int pageNum, int pageSize) {

        Page<Department> all;
        try {
            all = departmentDao.searchDepartments(keywords, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("按页搜索部门失败", e.getMessage());
        }
        return all;
    }


    @Override
    public void updateDepartment(UpdateDepartmentRequestDto request) {

        Department department = Department.builder().id(request.getId()).name(request.getName()).description(
            request.getDescription()).build();

        try {
            departmentDao.updateDepartment(department);
        } catch (Exception e) {
            throw new SystemException("更新部门信息失败", e.getMessage());
        }
    }

    @Override
    public void deleteDepartment(int id) {
        try {
            departmentDao.deleteDepartment(id);
        } catch (Exception e) {
            throw new SystemException("删除部门失败", e.getMessage());
        }
    }

}