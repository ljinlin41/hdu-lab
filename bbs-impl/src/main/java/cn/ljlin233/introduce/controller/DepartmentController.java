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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * DepartmentController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "部门信息接口")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有部门
     *
     * @return result
     */
    @ApiOperation(value = "获取所有部门")
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
    @ApiOperation(value = "按页获取部门")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @GetMapping(value = "/departmentPage", params = "page")
    public Page<Department> getDepartmentsByPage(@RequestParam int page) {

        return departmentService.getDepartmentsPage(page, 10);
    }

    /**
     * 获取部门详情
     *
     * @param id 部门Id
     * @return result
     */
    @ApiOperation(value = "根据部门Id获取部门")
    @ApiImplicitParam(name = "id", value = "部门Id", dataType = "int")
    @GetMapping(value = "/departmentId", params = "id")
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
    @ApiOperation(value = "按标题搜索部门")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索名称", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/searchDepartment", params = {"search", "page"})
    public Page<Department> searchDepartments(@RequestParam String search, @RequestParam int page) {

        return departmentService.searchDepartments(search, page, 10);
    }

    /**
     * 增加一个部门
     *
     * @param request request
     */
    @ApiOperation(value = "增加一个部门")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertDepartmentRequestDto")
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    @PostMapping(value = "/insertDepartment")
    public void addDepartment(@RequestBody InsertDepartmentRequestDto request) {

        departmentService.addDepartment(request);
    }

    /**
     * 修改部门信息
     *
     * @param request request
     */
    @ApiOperation(value = "修改部门信息")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "UpdateDepartmentRequestDto")
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    @PutMapping(value = "/updateDepartment")
    public void updateDepartments(@RequestBody UpdateDepartmentRequestDto request) {

        departmentService.updateDepartment(request);
    }

    /**
     * 删除
     *
     * @param request request
     */
    @ApiOperation(value = "删除一个部门")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteDepartmentRequestDto")
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    @DeleteMapping(value = "/deleteDepartment")
    public void deleteDepartments(@RequestBody DeleteDepartmentRequestDto request) {

        departmentService.deleteDepartment(request.getId());
    }
}