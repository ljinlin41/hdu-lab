package cn.ljlin233.introduce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.introduce.dto.DeleteDepartmentRequestDto;
import cn.ljlin233.introduce.dto.InsertDepartmentRequestDto;
import cn.ljlin233.introduce.dto.UpdateDepartmentRequestDto;
import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.introduce.service.DepartmentService;
import cn.ljlin233.util.Page;

/**
 * DepartmentController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public Page<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    /**
     * 按页获取所有部门
     *
     * @param page 第N页
     * @return result
     */
    @GetMapping(value = "/departments", params = "page")
    public Page<Department> getDepartmentsByPage(@RequestParam int page) {

        return departmentService.getDepartmentsPage(page, 10);
    }

    /**
     * 获取部门详情
     *
     * @param id 部门Id
     * @return result
     */
    @GetMapping(value = "/departments", params = "id")
    public Department getDepartmentsById(@RequestParam int id) {

        return departmentService.getDepartmentById(id);
    }

    /**
     * 搜索部门
     *
     * @param search 搜索名称
     * @param page 第N页
     * @return result
     */
    @GetMapping(value = "/departments", params = {"search", "page"})
    public Page<Department> searchDepartments(@RequestParam String search, @RequestParam int page) {

        return departmentService.searchDepartments(search, page, 10);
    }

    /**
     * 增加一个部门
     *
     * @param request request
     */
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    @PostMapping(value = "/departments")
    public void addDepartment(@RequestBody InsertDepartmentRequestDto request) {

        departmentService.addDepartment(request);
    }

    /**
     * 修改部门信息
     *
     * @param request request
     */
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    @PutMapping(value = "/departments")
    public void updateDepartments(@RequestBody UpdateDepartmentRequestDto request) {

        departmentService.updateDepartment(request);
    }

    /**
     * 删除
     *
     * @param request request
     */
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    @DeleteMapping(value = "/departments")
    public void deleteDepartments(@RequestBody DeleteDepartmentRequestDto request) {

        departmentService.deleteDepartment(request.getId());
    }
}