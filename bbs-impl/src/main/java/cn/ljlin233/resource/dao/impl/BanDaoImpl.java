package cn.ljlin233.resource.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ljlin233.resource.dao.BanDao;
import cn.ljlin233.resource.entity.Ban;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 16:59
 */
@Repository
public class BanDaoImpl implements BanDao {
    @Override
    public List<Ban> getAllBanUser() {
        return null;
    }

    @Override
    public int getAllBanUserCount() {
        return 0;
    }

    @Override
    public List<Ban> getBanUserByPage(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public void addBanUser(Ban ban) {

    }

    @Override
    public void deleteBanUser(int id) {

    }
}
