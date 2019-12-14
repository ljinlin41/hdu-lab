package cn.ljlin233.announce.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.announce.dao.AnnounceDao;
import cn.ljlin233.announce.dto.InsertAnnounceRequestDto;
import cn.ljlin233.announce.dto.UpdateAnnounceRequestDto;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.announce.service.AnnounceService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.common.DateUtil;
import cn.ljlin233.util.common.UserContext;
import cn.ljlin233.util.common.UserContextUtil;
import cn.ljlin233.util.exception.entity.QueryException;
import cn.ljlin233.util.exception.entity.SystemException;
import tk.mybatis.mapper.entity.Example;

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
            addVisitCount(announce);
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
        UserContext userContext = UserContextUtil.getUserContext();

        Announce announce = Announce.builder()
            .upUserNickname(userContext.getNickName())
            .upDate(DateUtil.getNow())
            .build();
        BeanUtils.copyProperties(request, announce);

        try {
            announceDao.addAnnounce(announce);
        } catch (Exception e) {
            throw new SystemException("failed to add Announce to server", e.getMessage());
        }
    }

    @Override
    public void updateAnnounce(UpdateAnnounceRequestDto request) {
        Announce announce = Announce.builder().id(request.getAnnounceId()).title(request.getTitle()).content(
            request.getContent()).savePath(request.getSavePath()).build();


        try {
            announceDao.updateAnnounce(announce);
        } catch (Exception e) {
            throw new SystemException("failed to update announce", e.getMessage());
        }
    }

    @Override
    public void updateNickname(int userId, String nickname) {

        Announce announce = Announce.builder().upUserNickname(nickname).build();
        Example example = new Example(Announce.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        announceDao.updateAnnounceByExample(announce, example);
    }

    @Override
    public void deleteAnnounce(int id) {

        try {
            Announce announce = getAnnounceById(id);
            announceDao.deleteAnnounce(announce);
        } catch (Exception e) {
            throw new SystemException("failed to delete announce", e.getMessage());
        }
    }

    private void addVisitCount(Announce announce) {

        Announce newAnnounce = Announce.builder().id(announce.getId()).visitCount(announce.getVisitCount() + 1).build();

        announceDao.updateAnnounce(newAnnounce);

    }

}