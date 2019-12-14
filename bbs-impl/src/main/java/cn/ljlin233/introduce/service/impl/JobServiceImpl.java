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
import tk.mybatis.mapper.entity.Example;

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

        jobDao.addJob(job);

    }

    @Override
    public Page<Job> getAllJobs() {

        return jobDao.getAllJobs();
    }

    @Override
    public Page<Job> getJobsPage(int pageNum, int pageSize) {

        return jobDao.getJobsPage(pageNum, pageSize);
    }

    @Override
    public Page<Job> searchJobs(String keywords, int pageNum, int pageSize) {

        return jobDao.searchJobs(keywords, pageNum, pageSize);
    }

    @Override
    public Job getJobById(int id) {
        Job result = jobDao.getJobById(id);
        // 访问次数+1
        addVisitCount(result);

        return result;
    }


    @Override
    public void updateJob(UpdateJobRequestDto request) {

        Job job = Job.builder().id(request.getJobId()).title(request.getTitle()).content(request.getContent()).build();

        jobDao.updateJob(job);

    }

    @Override
    public void updateNickname(int userId, String nickname) {

        Job job = Job.builder().upNickname(nickname).build();
        Example example = new Example(Job.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        jobDao.updateJobByExample(job, example);
    }

    @Override
    public void deleteJob(int id) {

        Job job = jobDao.getJobById(id);

        jobDao.deleteJob(job);

    }

    private void addVisitCount(Job job) {

        Job newJob = Job.builder().id(job.getId()).visitCount(job.getVisitCount() + 1).build();

        jobDao.updateJob(newJob);

    }

}