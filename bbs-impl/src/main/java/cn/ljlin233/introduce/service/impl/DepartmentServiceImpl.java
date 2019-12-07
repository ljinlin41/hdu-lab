package cn.ljlin233.introduce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.DepartmentDao;
import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.DepartmentService;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * DepartmentServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl() {}

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void addDepartment(String name, String description) {
        if (name == null || name.length() == 0) {
            throw new DataCheckedException("部门名称不能为空!");
        }
        if (description == null || description.length() == 0) {
            throw new DataCheckedException("介绍不能为空!");
        }
        Department department = new Department();
        department.setName(name);
        department.setDescription(description);

        try {
            departmentDao.addDepartment(department);
        } catch (Exception e) {
            throw new SystemException("添加部门失败", e.getMessage());
        }

    }

    @Override
    public List<Department> getAllDepartments() {

        List<Department> all = null;
        try {
            all = departmentDao.getAllDepartments();
        } catch (Exception e) {
            throw new SystemException("获取所有部门失败", e.getMessage());
        }
        return all;
    }

    @Override
    public List<Department> getDepartmentsPage(int page, int pageNum) {
        int start = (page - 1) * pageNum;

        List<Department> all = null;
        try {
            all = departmentDao.getDepartmentsPage(start, pageNum);
        } catch (Exception e) {
            throw new SystemException("按页获取部门失败", e.getMessage());
        }
        return all;
    }

    @Override
    public Department getDepartmentById(int id) {
        Department department = null;
        try {
            department = departmentDao.getDepartmentById(id);
        } catch (Exception e) {
            throw new SystemException("获取部门详情失败", e.getMessage());
        }

        return department;
    }

    @Override
    public int getDepartmentCount() {
        int count = 0;
        try {
            count = departmentDao.getDepartmentCount();
        } catch (Exception e) {
            throw new SystemException("获取部门数量失败", e.getMessage());
        }
        return count;
    }

    @Override
    public List<Department> searchDepartments(String keywords, int page, int pageNum) {
        int start = (page - 1) * pageNum;

        List<Department> all = null;
        try {
            all = departmentDao.searchDepartments(keywords, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("按页搜索部门失败", e.getMessage());
        }
        return all;
    }

    @Override
    public int getSearchCount(String keywords) {
        int count = 0;
        try {
            count = departmentDao.getSearchCount(keywords);
        } catch (Exception e) {
            throw new SystemException("获取部门搜索数量失败", e.getMessage());
        }
        return count;
    }

    @Override
    public void updateDepartment(Department department) {
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
            departmentDao.deleteDepartmentMember(id);
        } catch (Exception e) {
            throw new SystemException("删除部门失败", e.getMessage());
        }
    }

    @Override
    public List<Member> getDepartmentMember(int id, int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Member> all = null;

        try {
            all = departmentDao.getDepartmentMembers(id, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("获取部门成员失败", e.getMessage());
        }
        return all;
    }

    @Override
    public int getMembersCount(int departmentId) {
        int count = 0;
        try {
            count = departmentDao.getMembersCount(departmentId);
        } catch (Exception e) {
            throw new SystemException("获取部门成员数量失败", e.getMessage());
        }
        return count;
    }
}