package cn.ljlin233.introduce.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.JobDao;
import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.introduce.service.JobService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * JobServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {

    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private JobDao jobDao;

    private UserInfoService userInfoService;

    public JobServiceImpl() {
    }

    @Autowired
    public JobServiceImpl(JobDao jobDao, UserInfoService userInfoService) {
        this.jobDao = jobDao;
        this.userInfoService = userInfoService;
    }

    @Override
    public void addJob(String title, String content, Integer userId) {

        if (title == null || title.length() == 0) {
            throw new DataCheckedException("标题不能为空!");
        }
        if (content == null || content.length() == 0) {
            throw new DataCheckedException("内容不能为空!");
        }
        UserInfo userInfo = userInfoService.getUserInfo(userId);
        if (userId == null) {
            throw new DataCheckedException("账号不存在!");
        }

        Job job = new Job();
        job.setTitle(title);
        job.setContent(content);
        job.setUpUserId(userId);
        job.setUpNickname(userInfo.getNickname());
        String upDate = dateformat.format(new Date());
        job.setUpDate(upDate);
        try {
            jobDao.addJob(job);
        } catch (Exception e) {
            throw new SystemException("服务器存储文章失败", e.getMessage());
        }

    }

    @Override
    public List<Job> getAllJobs() {
        List<Job> all = null;
        try {
            all = jobDao.getAllJobs();
        } catch (Exception e) {
            throw new SystemException("服务器获取所有招聘信息失败!", e.getMessage());
        }
        return all;
    }

    @Override
    public List<Job> getJobsPage(int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Job> result = null;
        try {
            result = jobDao.getJobsPage(start, pageNum);
        } catch (Exception e) {
            throw new SystemException("服务器获取招聘信息失败!", e.getMessage());
        }

        return result;
    }

    @Override
    public List<Job> searchJobs(String keywords, int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Job> result = null;
        try {
            result = jobDao.searchJobs(keywords, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("服务器搜索招聘信息失败!", e.getMessage());
        }

        return result;
    }

    @Override
    public Job getJobById(int id) {
        Job result = null;
        try {
            result = jobDao.getJobById(id);
        } catch (Exception e) {
            throw new SystemException("服务器获取招聘信息失败!", e.getMessage());
        }
        // 访问次数+1
        addVisitCount(id);

        return result;
    }

    @Override
    public void addVisitCount(int id) {
        try {
            jobDao.addVisitCount(id);
        } catch (Exception e) {
            throw new SystemException("招聘信息访问数错误!", e.getMessage());
        }
    }

    @Override
    public int getJobCount() {
        int count = 0;
        try {
            count = jobDao.getJobCount();
        } catch (Exception e) {
            throw new SystemException("读取招聘信息数量错误!", e.getMessage());
        }
        return count;
    }

    @Override
    public int getSearchCount(String keywords) {
        int count = 0;
        try {
            count = jobDao.getSearchCount(keywords);
        } catch (Exception e) {
            throw new SystemException("读取招聘信息搜索数量错误!", e.getMessage());
        }
        return count;
    }

    @Override
    public void updateJob(Job job) {
        try {
            jobDao.updateJob(job);
        } catch (Exception e) {
            throw new SystemException("更新招聘信息失败!", e.getMessage());
        }
    }

    @Override
    public void deleteJob(int id) {
        try {
            jobDao.deleteJob(id);
        } catch (Exception e) {
            throw new SystemException("删除招聘信息失败!", e.getMessage());
        }
    }

}