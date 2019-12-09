package cn.ljlin233.introduce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.introduce.entity.JobResponse;
import cn.ljlin233.introduce.service.JobService;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.auth.AdminAuth;
import cn.ljlin233.util.auth.MyselfAuth;
import cn.ljlin233.util.auth.RootAuth;
import cn.ljlin233.util.auth.TeacherAuth;

/**
 * JobController
 */
@Controller
@RequestMapping("/api")
public class JobController {

    private JobService jobService;

    private UserTokenService userTokenService;

    public JobController() {}

    @Autowired
    public JobController(JobService jobService, UserTokenService userTokenService) {
        this.jobService = jobService;
        this.userTokenService = userTokenService;
    }

    // 增加一个招聘信息
    @TeacherAuth
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public void addJob(HttpServletRequest request) {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        //Integer userId = userTokenService.getUserid(request.getHeader("token"));

        jobService.addJob(title, content, 123);

    }

    // 获取所有招聘信息
    @RootAuth
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    @ResponseBody
    public JobResponse getAllJobs() {
        List<Job> all = jobService.getAllJobs();
        int count = jobService.getJobCount();

        return new JobResponse(count, all);
    }

    // 按页获取所有招聘信息
    @RequestMapping(value = "/jobs", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public JobResponse getJobsPage(@RequestParam int page) {

        List<Job> result = jobService.getJobsPage(page, 10);
        int count = jobService.getJobCount();

        return new JobResponse(count, result);
    }

    // 按页搜索招聘信息
    @RequestMapping(value = "/jobs", params = {"search", "page"}, method = RequestMethod.GET)
    @ResponseBody
    public JobResponse searchJobs(@RequestParam String search, @RequestParam int page) {

        List<Job> result = jobService.searchJobs(search, page, 10);
        int count = jobService.getSearchCount(search);

        return new JobResponse(count, result);
    }

    // 获取招聘信息详情
    @RequestMapping(value = "/jobs", params = "id", method = RequestMethod.GET)
    @ResponseBody
    public Job getJobsById(@RequestParam int id) {

        Job result = jobService.getJobById(id);
        return result;
    }

    // 更新招聘信息
    @MyselfAuth(tableName = "intro_job", column = "up_userid")
    @RequestMapping(value = "/jobs", params = "id", method = RequestMethod.PUT)
    public void updateJob(@RequestParam int id, HttpServletRequest request) {
        Job job = new Job();
        job.setId(id);
        job.setTitle(request.getParameter("title"));
        job.setContent(request.getParameter("content"));

        jobService.updateJob(job);
    }

    // 删除招聘信息
    @MyselfAuth(tableName = "intro_job", column = "up_userid")
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/jobs", params = "id", method = RequestMethod.DELETE)
    public void deleteJob(@RequestParam int id) {
        jobService.deleteJob(id);
    }

}