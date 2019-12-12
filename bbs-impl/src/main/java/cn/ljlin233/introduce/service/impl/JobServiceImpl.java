package cn.ljlin233.introduce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.JobDao;
import cn.ljlin233.introduce.dto.InsertJobRequestDto;
import cn.ljlin233.introduce.dto.UpdateJobRequestDto;
import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.introduce.service.JobService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.common.DateUtil;
import cn.ljlin233.util.common.UserContext;
import cn.ljlin233.util.common.UserContextUtil;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * JobServiceImpl
 *
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Override
    public void addJob(InsertJobRequestDto request) {

        UserContext userContext = UserContextUtil.getUserContext();

        Job job = Job.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .upUserId(userContext.getId())
            .upNickname(userContext.getNickName())
            .upDate(DateUtil.getNow())
            .build();

        try {
            jobDao.addJob(job);
        } catch (Exception e) {
            throw new SystemException("服务器存储文章失败", e.getMessage());
        }

    }

    @Override
    public Page<Job> getAllJobs() {
        Page<Job> all;
        try {
            all = jobDao.getAllJobs();
        } catch (Exception e) {
            throw new SystemException("服务器获取所有招聘信息失败!", e.getMessage());
        }
        return all;
    }

    @Override
    public Page<Job> getJobsPage(int pageNum, int pageSize) {
        Page<Job> result;
        try {
            result = jobDao.getJobsPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("服务器获取招聘信息失败!", e.getMessage());
        }

        return result;
    }

    @Override
    public Page<Job> searchJobs(String keywords, int pageNum, int pageSize) {

        Page<Job> result;
        try {
            result = jobDao.searchJobs(keywords, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("服务器搜索招聘信息失败!", e.getMessage());
        }

        return result;
    }

    @Override
    public Job getJobById(int id) {
        Job result;
        try {
            result = jobDao.getJobById(id);
        } catch (Exception e) {
            throw new SystemException("服务器获取招聘信息失败!", e.getMessage());
        }
        // 访问次数+1
        addVisitCount(result);

        return result;
    }


    @Override
    public void updateJob(UpdateJobRequestDto request) {

        Job job = Job.builder().id(request.getJobId()).title(request.getTitle()).content(request.getContent()).build();

        try {
            jobDao.updateJob(job);
        } catch (Exception e) {
            throw new SystemException("更新招聘信息失败!", e.getMessage());
        }
    }

    @Override
    public void deleteJob(int id) {

        Job job = jobDao.getJobById(id);

        try {
            jobDao.deleteJob(job);
        } catch (Exception e) {
            throw new SystemException("删除招聘信息失败!", e.getMessage());
        }
    }

    private void addVisitCount(Job job) {

        Job newJob = Job.builder().id(job.getId()).visitCount(job.getVisitCount() + 1).build();

        try {
            jobDao.updateJob(newJob);
        } catch (Exception e) {
            throw new SystemException("招聘信息访问数错误!", e.getMessage());
        }
    }

}