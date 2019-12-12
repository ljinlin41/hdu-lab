package cn.ljlin233.introduce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.introduce.dao.AwardDao;
import cn.ljlin233.introduce.dao.mapper.AwardMapper;
import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 17:34
 */
@Repository
public class AwardDaoImpl implements AwardDao {

    @Autowired
    private AwardMapper awardMapper;

    @Override
    public Page<Award> getAllAwards() {
        List<Award> awardList = awardMapper.selectAll();
        PageInfo<Award> awardPageInfo = new PageInfo<>(awardList);

        return Page.<Award>builder().totalNum(awardPageInfo.getTotal()).data(new ArrayList<>(awardList)).build();

    }

    @Override
    public Page<Award> getAwardsPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Award> awardList = awardMapper.selectAll();
        PageInfo<Award> awardPageInfo = new PageInfo<>(awardList);

        return Page.<Award>builder().totalNum(awardPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(awardList)).build();
    }

    @Override
    public Award getAwardById(int id) {
        return awardMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Award> searchAwards(String keywords, int pageNum, int pageSize) {

        Example example = new Example(Award.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("title", "%" + keywords + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<Award> awardList = awardMapper.selectByExample(example);
        PageInfo<Award> awardPageInfo = new PageInfo<>(awardList);

        return Page.<Award>builder().totalNum(awardPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(awardList)).build();
    }

    @Override
    public void addAward(Award award) {
        awardMapper.insertSelective(award);
    }

    @Override
    public void updateAward(Award award) {
        awardMapper.updateByPrimaryKeySelective(award);
    }

    @Override
    public void deleteAward(Award award) {
        awardMapper.delete(award);
    }
}
