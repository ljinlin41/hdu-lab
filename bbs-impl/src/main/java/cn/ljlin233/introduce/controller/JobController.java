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

import cn.ljlin233.introduce.dto.DeleteJobRequestDto;
import cn.ljlin233.introduce.dto.InsertJobRequestDto;
import cn.ljlin233.introduce.dto.UpdateJobRequestDto;
import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.introduce.service.JobService;
import cn.ljlin233.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * JobController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "招聘信息接口")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 获取所有招聘信息
     *
     * @return result
     */
    @ApiOperation(value = "获取招聘信息")
    @GetMapping(value = "/jobs")
    public Page<Job> getAllJobs() {

        return jobService.getAllJobs();
    }

    /**
     * 按页获取所有招聘信息
     *
     * @param page 第N页
     * @return result
     */
    @ApiOperation(value = "按页获取所有招聘信息")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @GetMapping(value = "/jobPage", params = "page")
    public Page<Job> getJobsPage(@RequestParam int page) {

        return jobService.getJobsPage(page, 10);
    }

    /**
     * 按页搜索招聘信息
     *
     * @param search 搜索标题
     * @param page 第N页
     * @return result
     */
    @ApiOperation(value = "按标题搜索招聘信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索标题", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/searchJob", params = {"search", "page"})
    public Page<Job> searchJobs(@RequestParam String search, @RequestParam int page) {

        return jobService.searchJobs(search, page, 10);
    }

    /**
     * 获取招聘信息详情
     *
     * @param id 招聘id
     * @return result
     */
    @ApiOperation(value = "根据招聘Id获取招聘")
    @ApiImplicitParam(name = "id", value = "招聘Id", dataType = "int")
    @GetMapping(value = "/jobId", params = "id")
    public Job getJobsById(@RequestParam int id) {

        return jobService.getJobById(id);
    }

    /**
     * 增加一个招聘信息
     *
     * @param request request
     */
    @ApiOperation(value = "增加一个招聘信息")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertJobRequestDto")
    @PostMapping(value = "/insertJob")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    public void addJob(@RequestBody InsertJobRequestDto request) {

        jobService.addJob(request);
    }

    /**
     * 更新招聘信息
     *
     * @param request request
     */
    @ApiOperation(value = "更新招聘信息")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "UpdateJobRequestDto")
    @PutMapping(value = "/updateJob")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId() == #request.upUserId")
    public void updateJob(@RequestBody UpdateJobRequestDto request) {

        jobService.updateJob(request);
    }

    /**
     * 删除招聘信息
     *
     * @param request request
     */
    @ApiOperation(value = "删除招聘信息")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteJobRequestDto")
    @DeleteMapping(value = "/deleteJob")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId() == #request.upUserId")
    public void deleteJob(@RequestBody DeleteJobRequestDto request) {
        jobService.deleteJob(request.getJobId());
    }

}