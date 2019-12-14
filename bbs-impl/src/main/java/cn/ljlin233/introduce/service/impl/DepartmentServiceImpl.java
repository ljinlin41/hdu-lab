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

        departmentDao.addDepartment(department);

    }

    @Override
    public Page<Department> getAllDepartments() {

        return departmentDao.getAllDepartments();
    }

    @Override
    public Page<Department> getDepartmentsPage(int pageNum, int pageSize) {

        return departmentDao.getDepartmentsPage(pageNum, pageSize);
    }

    @Override
    public Department getDepartmentById(int id) {

        return departmentDao.getDepartmentById(id);
    }


    @Override
    public Page<Department> searchDepartments(String keywords, int pageNum, int pageSize) {

        return departmentDao.searchDepartments(keywords, pageNum, pageSize);
    }


    @Override
    public void updateDepartment(UpdateDepartmentRequestDto request) {

        Department department = Department.builder().id(request.getId()).name(request.getName()).description(
            request.getDescription()).build();

        departmentDao.updateDepartment(department);

    }

    @Override
    public void deleteDepartment(int id) {

        departmentDao.deleteDepartment(id);

    }

}