package cn.ljlin233.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.user.dao.UserOriginDao;
import cn.ljlin233.user.dao.mapper.UserOriginMapper;
import cn.ljlin233.user.entity.UserOrigin;
import cn.ljlin233.util.Page;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:32
 */
@Repository
public class UserOriginDaoImpl implements UserOriginDao {

    @Autowired
    private UserOriginMapper userOriginMapper;

    @Override
    public Page<UserOrigin> getUserOriginByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<UserOrigin> userOriginList = userOriginMapper.selectAll();
        PageInfo<UserOrigin> userOriginPageInfo = new PageInfo<>(userOriginList);

        return Page.<UserOrigin>builder().totalNum(userOriginPageInfo.getTotal())
            .pageNum(pageNum)
            .pageSize(pageSize)
            .data(new ArrayList<>(userOriginList))
            .build();
    }

    @Override
    public void addUserOrigin(UserOrigin userOrigin) {
        userOriginMapper.insertSelective(userOrigin);
    }

    @Override
    public void deleteUserOrigin(UserOrigin userOrigin) {
        userOriginMapper.delete(userOrigin);
    }

    @Override
    public boolean existsAccount(String account) {

        UserOrigin userOrigin = UserOrigin.builder().account(account).build();

        int count = userOriginMapper.selectCount(userOrigin);

        return count != 0;
    }
}
