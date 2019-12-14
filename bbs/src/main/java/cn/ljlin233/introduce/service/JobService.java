package cn.ljlin233.introduce.service;

import cn.ljlin233.introduce.dto.InsertJobRequestDto;
import cn.ljlin233.introduce.dto.UpdateJobRequestDto;
import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.util.Page;

/**
 * JobService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface JobService {


    /**
     * 获取所有招聘
     *
     * @return 招聘列表
     */
    Page<Job> getAllJobs();

    /**
     * 按页获取招聘
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 招聘列表
     */
    Page<Job> getJobsPage(int pageNum, int pageSize);

    /**
     * 根据招聘Id获取招聘
     *
     * @param id 招聘Id
     * @return 招聘
     */
    Job getJobById(int id);


    /**
     * 按标题搜索招聘
     *
     * @param keywords 招聘标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 招聘列表
     */
    Page<Job> searchJobs(String keywords, int pageNum, int pageSize);


    /**
     * 增加一个招聘
     *
     * @param request request
     */
    void addJob(InsertJobRequestDto request);

    /**
     * 更新一个招聘
     *
     * @param request request
     */
    void updateJob(UpdateJobRequestDto request);

    /**
     * 更新nickname
     *
     * @param userId userId
     * @param nickname nickname
     */
    void updateNickname(int userId, String nickname);

    /**
     * 删除一个招聘
     *
     * @param id 招聘Id
     */
    void deleteJob(int id);
}