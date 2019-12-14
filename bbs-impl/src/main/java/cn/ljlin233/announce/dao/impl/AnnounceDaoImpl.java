package cn.ljlin233.announce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.announce.dao.AnnounceDao;
import cn.ljlin233.announce.dao.mapper.AnnounceMapper;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.util.Page;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/11/5 13:56
 */
@Repository
@Slf4j
public class AnnounceDaoImpl implements AnnounceDao {

    @Autowired
    private AnnounceMapper announceMapper;

    @Override
    public Page<Announce> getAllAnnounces() {
        List<Announce> announceList = announceMapper.selectAll();
        PageInfo<Announce> announcePageInfo = new PageInfo<>(announceList);

        announceList = new ArrayList<>(announceList);

        return Page.<Announce>builder().totalNum(announcePageInfo.getTotal()).data(announceList).build();
    }

    @Override
    public Page<Announce> getAnnouncesByPage(int pageNum, int pageSize) {
        // 分页
        PageHelper.startPage(pageNum, pageSize);
        List<Announce> announceList = announceMapper.selectAll();

        PageInfo<Announce> announcePageInfo = new PageInfo<>(announceList);

        announceList = new ArrayList<>(announcePageInfo.getList());

        return Page.<Announce>builder().totalNum(announcePageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            announceList).build();
    }

    @Override
    public Announce getAnnounceById(int id) {

        return announceMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Announce> searchAnnounce(String keywords, int pageNum, int pageSize) {

        Example example = new Example(Announce.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title", "%" + keywords + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<Announce> announceList = announceMapper.selectByExample(example);

        PageInfo<Announce> announcePageInfo = new PageInfo<>(announceList);
        announceList = new ArrayList<>(announcePageInfo.getList());

        return Page.<Announce>builder().totalNum(announcePageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            announceList).build();
    }

    @Override
    public void addAnnounce(Announce announce) {
        announceMapper.insertSelective(announce);
    }

    @Override
    public void updateAnnounce(Announce announce) {
        announceMapper.updateByPrimaryKeySelective(announce);
    }

    @Override
    public void updateAnnounceByExample(Announce announce, Example example) {
        announceMapper.updateByExampleSelective(announce, example);
    }

    @Override
    public void deleteAnnounce(Announce announce) {
        announceMapper.delete(announce);
    }

}
