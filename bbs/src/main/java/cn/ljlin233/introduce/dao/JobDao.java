package cn.ljlin233.introduce.dao;

import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * JobDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface JobDao {


    /**
     * 获取所有的招聘
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
     * 按招聘Id获取招聘信息
     *
     * @param id 招聘Id
     * @return 招聘
     */
    Job getJobById(int id);


    /**
     * 按标题搜索招聘
     *
     * @param keywords 搜索标题
     * @param pageSize 第N页
     * @param pageNum 每页大小
     * @return 招聘列表
     */
    Page<Job> searchJobs(String keywords, int pageSize, int pageNum);


    /**
     * 添加一个招聘
     *
     * @param job 招聘
     */
    void addJob(Job job);

    /**
     * 更新一个招聘
     *
     * @param job 招聘
     */
    void updateJob(Job job);

    void updateJobByExample(Job job, Example example);
    /**
     * 删除一个招聘
     *
     * @param job 招聘
     */
    void deleteJob(Job job);

}