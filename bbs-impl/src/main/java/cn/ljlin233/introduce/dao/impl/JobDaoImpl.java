package cn.ljlin233.introduce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.introduce.dao.JobDao;
import cn.ljlin233.introduce.dao.mapper.JobMapper;
import cn.ljlin233.introduce.entity.Job;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 22:42
 */
@Repository
public class JobDaoImpl implements JobDao {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public Page<Job> getAllJobs() {

        List<Job> jobList = jobMapper.selectAll();
        PageInfo<Job> jobPageInfo = new PageInfo<>(jobList);

        return Page.<Job>builder().totalNum(jobPageInfo.getTotal()).data(new ArrayList<>(jobList)).build();
    }

    @Override
    public Page<Job> getJobsPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Job> jobList = jobMapper.selectAll();
        PageInfo<Job> jobPageInfo = new PageInfo<>(jobList);

        return Page.<Job>builder().totalNum(jobPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(jobList)).build();
    }

    @Override
    public Job getJobById(int id) {
        return jobMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Job> searchJobs(String keywords, int pageSize, int pageNum) {

        Example example = new Example(Job.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title", "%" + keywords + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<Job> jobList = jobMapper.selectByExample(example);
        PageInfo<Job> jobPageInfo = new PageInfo<>(jobList);

        return Page.<Job>builder().totalNum(jobPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(jobList)).build();
    }

    @Override
    public void addJob(Job job) {
        jobMapper.insertSelective(job);
    }

    @Override
    public void updateJob(Job job) {
        jobMapper.updateByPrimaryKeySelective(job);
    }

    @Override
    public void deleteJob(Job job) {
        jobMapper.delete(job);
    }
}
