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

        return announceDao.getAllAnnounces();
    }

    @Override
    public Page<Announce> getAnnouncesByPage(int pageNum, int pageSize) {

        return announceDao.getAnnouncesByPage(pageNum, pageSize);
    }

    @Override
    public Announce getAnnounceById(int id) {
        Announce announce = announceDao.getAnnounceById(id);

        if (announce != null) {
            // 增加一个浏览记录
            addVisitCount(announce);
        }
        return announce;
    }

    @Override
    public Page<Announce> searchAnnounces(String title, int pageNum, int pageSize) {

        return announceDao.searchAnnounce(title, pageNum, pageSize);
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

        announceDao.addAnnounce(announce);

    }

    @Override
    public void updateAnnounce(UpdateAnnounceRequestDto request) {
        Announce announce = Announce.builder().id(request.getAnnounceId()).title(request.getTitle()).content(
            request.getContent()).savePath(request.getSavePath()).build();

        announceDao.updateAnnounce(announce);

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

        Announce announce = getAnnounceById(id);

        announceDao.deleteAnnounce(announce);

    }

    private void addVisitCount(Announce announce) {

        Announce newAnnounce = Announce.builder().id(announce.getId()).visitCount(announce.getVisitCount() + 1).build();

        announceDao.updateAnnounce(newAnnounce);

    }

}