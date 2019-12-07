package cn.ljlin233.announce.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.announce.dao.AnnounceDao;
import cn.ljlin233.announce.dto.InsertAnnounceRequestDto;
import cn.ljlin233.announce.dto.UpdateAnnounceRequestDto;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.announce.service.AnnounceService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.DateUtil;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.exception.entity.QueryException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * AnnounceServiceImpl
 *
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AnnounceServiceImpl implements AnnounceService {

    @Autowired
    private AnnounceDao announceDao;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Page<Announce> getAllAnnounces() {
        Page<Announce> results;

        try {
            results = announceDao.getAllAnnounces();
        } catch (Exception e) {
            throw new QueryException("Get all announces fail", e.getMessage());
        }

        return results;
    }

    @Override
    public Page<Announce> getAnnouncesByPage(int pageNum, int pageSize) {
        Page<Announce> results;

        try {
            results = announceDao.getAnnouncesByPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("fail to get announces by page", e.getMessage());
        }
        return results;
    }

    @Override
    public Announce getAnnounceById(int id) {
        Announce announce;

        try {
            announce = announceDao.getAnnounceById(id);
            // 增加一个浏览记录
            announceDao.addVisitCount(announce);
        } catch (Exception e) {
            throw new QueryException("failed to get announce by Id", e.getMessage());
        }

        return announce;
    }

    @Override
    public Page<Announce> searchAnnounces(String title, int pageNum, int pageSize) {

        Page<Announce> results;
        try {
            results = announceDao.searchAnnounce(title, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("failed to search announces", e.getMessage());
        }

        return results;
    }

    @Override
    public void addAnnounce(InsertAnnounceRequestDto request) {

        // 获取上传用户信息
        UserInfo info = userInfoService.getUserInfo(request.getUpUserId());

        Announce announce = Announce.builder().upUserNickname(info.getNickname()).upDate(
            DateUtil.getInstance().format(LocalDateTime.now())).build();
        BeanUtils.copyProperties(request, announce);

        try {
            announceDao.addAnnounce(announce);
        } catch (Exception e) {
            throw new SystemException("failed to add Announce to server", e.getMessage());
        }
    }

    @Override
    public void updateAnnounce(int id, UpdateAnnounceRequestDto request) {
        Announce announce = Announce.builder().id(id).build();
        BeanUtils.copyProperties(request, announce);

        try {
            announceDao.updateAnnounce(announce);
        } catch (Exception e) {
            throw new SystemException("failed to update announce", e.getMessage());
        }
    }

    @Override
    public void deleteAnnounce(int id) {
        try {
            announceDao.deleteAnnounce(id);
        } catch (Exception e) {
            throw new SystemException("failed to delete announce", e.getMessage());
        }
    }

}