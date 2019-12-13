package cn.ljlin233.introduce.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageInfo;

import cn.ljlin233.introduce.dao.ApplyDao;
import cn.ljlin233.introduce.dao.mapper.ApplyMapper;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 16:43
 */
@Repository
public class ApplyDaoImpl implements ApplyDao {

    @Autowired
    private ApplyMapper applyMapper;

    @Override
    public void addApplys(Apply apply) {
        applyMapper.insertSelective(apply);
    }

    @Override
    public Apply getApplyById(int id) {
        return applyMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateApply(Apply apply) {
        applyMapper.updateByPrimaryKeySelective(apply);
    }

    @Override
    public Page<Apply> getApplyByUserId(int userId) {

        Apply apply = Apply.builder().userId(userId).build();

        List<Apply> applyList = applyMapper.select(apply);
        PageInfo<Apply> applyPageInfo = new PageInfo<>(applyList);

        return Page.<Apply>builder().totalNum(applyPageInfo.getTotal()).data(new ArrayList<>(applyList)).build();
    }

    @Override
    public Page<Apply> getPendingApply(Collection<Integer> departmentIds) {

        Example example = new Example(Apply.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("departmentId", departmentIds);

        List<Apply> applyList = applyMapper.selectByExample(example);
        PageInfo<Apply> applyPageInfo = new PageInfo<>(applyList);

        return Page.<Apply>builder().totalNum(applyPageInfo.getTotal()).data(new ArrayList<>(applyList)).build();
    }
}
