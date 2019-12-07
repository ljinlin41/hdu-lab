package cn.ljlin233.resource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.resource.dao.BanDao;
import cn.ljlin233.resource.entity.Ban;
import cn.ljlin233.resource.service.BanService;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * BanServiceImpl
 */
@Service
public class BanServiceImpl implements BanService {

    private BanDao banDao;

    public BanServiceImpl() {
        super();
    }

    @Autowired
    public BanServiceImpl(BanDao banDao) {
        this.banDao = banDao;
    }

    @Override
    public List<Ban> getAllBanUser() {
        List<Ban> results = null;
        try {
            results = banDao.getAllBanUser();
        } catch (Exception e) {
            throw new SystemException("failed to get ban user list", e.getMessage());
        }
        return results;
    }

    @Override
    public int getAllBanUserCount() {
        int count = 0;
        try {
            count = banDao.getAllBanUserCount();
        } catch (Exception e) {
            throw new SystemException("failed to get ban user count", e.getMessage());
        }
        return count;
    }

    @Override
    public List<Ban> getBanUserByPage(int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Ban> results = null;
        try {
            results = banDao.getBanUserByPage(start, pageNum);
        } catch (Exception e) {
            throw new SystemException("failed to get ban user list by page", e.getMessage());
        }
        return results;
    }

    @Override
    public void addBanUser(int banId, int addId) {
        Ban ban = new Ban();
        ban.setBanId(banId);
        ban.setAddId(addId);

        try {
            banDao.addBanUser(ban);
        } catch (Exception e) {
            throw new SystemException("failed to add ban user", e.getMessage());
        }
    }

    @Override
    public void deleteBanUser(int id) {
        try {
            banDao.deleteBanUser(id);
        } catch (Exception e) {
            throw new SystemException("failed to delete ban user", e.getMessage());
        }
    }

}