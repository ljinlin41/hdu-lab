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

/**
 * JobController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 获取所有招聘信息
     *
     * @return result
     */
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
    @GetMapping(value = "/jobs", params = "page")
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
    @GetMapping(value = "/jobs", params = {"search", "page"})
    public Page<Job> searchJobs(@RequestParam String search, @RequestParam int page) {

        return jobService.searchJobs(search, page, 10);
    }

    /**
     * 获取招聘信息详情
     *
     * @param id 招聘id
     * @return result
     */
    @GetMapping(value = "/jobs", params = "id")
    public Job getJobsById(@RequestParam int id) {

        return jobService.getJobById(id);
    }

    /**
     * 增加一个招聘信息
     *
     * @param request request
     */
    @PostMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    public void addJob(@RequestBody InsertJobRequestDto request) {

        jobService.addJob(request);
    }

    /**
     * 更新招聘信息
     *
     * @param request request
     */
    @PutMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId() == #request.upUserId")
    public void updateJob(@RequestBody UpdateJobRequestDto request) {

        jobService.updateJob(request);
    }

    /**
     * 删除招聘信息
     *
     * @param request request
     */
    @DeleteMapping(value = "/jobs")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId() == #request.upUserId")
    public void deleteJob(@RequestBody DeleteJobRequestDto request) {
        jobService.deleteJob(request.getJobId());
    }

}