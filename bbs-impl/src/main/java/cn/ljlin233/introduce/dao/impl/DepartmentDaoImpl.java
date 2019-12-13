package cn.ljlin233.introduce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.introduce.dao.DepartmentDao;
import cn.ljlin233.introduce.dao.mapper.DepartmentMapper;
import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 15:01
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Page<Department> getAllDepartments() {

        List<Department> departmentList = departmentMapper.selectAll();
        PageInfo<Department> departmentPageInfo = new PageInfo<>(departmentList);

        return Page.<Department>builder().totalNum(departmentPageInfo.getTotal())
            .data(new ArrayList<>(departmentList))
            .build();
    }

    @Override
    public Page<Department> getDepartmentsPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Department> departmentList = departmentMapper.selectAll();
        PageInfo<Department> departmentPageInfo = new PageInfo<>(departmentList);

        return Page.<Department>builder().totalNum(departmentPageInfo.getTotal())
            .pageNum(pageNum)
            .pageSize(pageSize)
            .data(new ArrayList<>(departmentList))
            .build();

    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Department> searchDepartments(String keywords, int pageNum, int pageSize) {

        Example example = new Example(Department.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name", "%" + keywords + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<Department> departmentList = departmentMapper.selectByExample(example);
        PageInfo<Department> departmentPageInfo = new PageInfo<>(departmentList);

        return Page.<Department>builder().totalNum(departmentPageInfo.getTotal())
            .pageNum(pageNum)
            .pageSize(pageSize)
            .data(new ArrayList<>(departmentList))
            .build();
    }

    @Override
    public void addDepartment(Department department) {
        departmentMapper.insertSelective(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentMapper.deleteByPrimaryKey(id);
    }
}
