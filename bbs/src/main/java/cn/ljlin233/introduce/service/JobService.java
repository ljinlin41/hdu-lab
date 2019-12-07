package cn.ljlin233.introduce.service;

import java.util.List;

import cn.ljlin233.introduce.entity.Job;

/**
 * JobService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface JobService {

    /**
     * 增加一个招聘
     *
     * @param title 标题
     * @param content 正文
     * @param userId 用户Id
     */
    void addJob(String title, String content, Integer userId);

    /**
     * 获取所有招聘
     *
     * @return 招聘列表
     */
    List<Job> getAllJobs();

    /**
     * 按页获取招聘
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 招聘列表
     */
    List<Job> getJobsPage(int pageNum, int pageSize);

    /**
     * 根据招聘Id获取招聘
     *
     * @param id 招聘Id
     * @return 招聘
     */
    Job getJobById(int id);

    /**
     * 增加招聘的访问数
     *
     * @param id 招聘Id
     */
    void addVisitCount(int id);

    /**
     * 获取招聘总数
     *
     * @return 招聘总数
     */
    int getJobCount();

    /**
     * 按标题搜索招聘
     *
     * @param keywords 招聘标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 招聘列表
     */
    List<Job> searchJobs(String keywords, int pageNum, int pageSize);

    /**
     * 获取搜索结果数量
     *
     * @param keywords 搜索标题
     * @return 搜索结果数量
     */
    int getSearchCount(String keywords);

    /**
     * 更新一个招聘
     *
     * @param job 招聘
     */
    void updateJob(Job job);

    /**
     * 删除一个招聘
     *
     * @param id 招聘Id
     */
    void deleteJob(int id);
}