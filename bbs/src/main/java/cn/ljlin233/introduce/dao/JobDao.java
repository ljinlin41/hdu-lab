package cn.ljlin233.introduce.dao;

import java.util.List;

import cn.ljlin233.introduce.entity.Job;

/**
 * JobDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface JobDao {

    /**
     * 添加一个招聘
     *
     * @param job 招聘
     */
    void addJob(Job job);

    /**
     * 获取所有的招聘
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
     * 按招聘Id获取招聘信息
     *
     * @param id 招聘Id
     * @return 招聘
     */
    Job getJobById(int id);

    /**
     * 增加一个招聘的访问数
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
     * @param keywords 搜索标题
     * @param pageSize 第N页
     * @param pageNum 每页大小
     * @return 招聘列表
     */
    List<Job> searchJobs(String keywords, int pageSize, int pageNum);

    /**
     * 搜索结果数
     *
     * @param keywords 搜索标题
     * @return 结果数
     */
    int getSearchCount(String keywords);

    /**
     * 更新一个招聘
     *
     * @param job 招聘
     */
    void updateJob(Job job);

    /**
     * 根据招聘Id删除一个招聘
     *
     * @param id 招聘Id
     */
    void deleteJob(int id);

}