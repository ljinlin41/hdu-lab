package cn.ljlin233.announce.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.ljlin233.announce.dao.AnnounceDao;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.bbs.BbsApplication;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.common.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/11/5 15:44
 */
@Slf4j
@SpringBootTest(classes = BbsApplication.class)
@RunWith(SpringRunner.class)
public class AnnounceDaoImplTest {

    @Autowired
    private AnnounceDao announceDao;

    @Test
    public void getAnnouncesByPage() {

        Page<Announce> result = announceDao.getAnnouncesByPage(2, 1);

        log.info(result.toString());

    }

    @Test
    public void getAllAnnounces() {

        Page<Announce> result = announceDao.getAllAnnounces();

        log.info(result.toString());
    }

    @Test
    public void getAnnounceById() {

        Announce announce = announceDao.getAnnounceById(2);

        if (announce != null) {
            log.info(announce.toString());
        }

    }

    @Test
    public void searchAnnounce() {

        Page<Announce> announcePage = announceDao.searchAnnounce("2", 1, 10);
        log.info(announcePage.toString());

    }

    @Test
    public void addAnnounce() {

        Announce announce = Announce.builder()
            .title("123")
            .content("123")
            .upUserId(111)
            .savePath("111111").upUserNickname("hhhhh").upDate(DateUtil.getNow())
            .build();

        announceDao.addAnnounce(announce);
    }

    @Test
    public void updateAnnounce() {

        Announce announce = Announce.builder()
            .id(4)
            .title("123")
            .content("123")
            .upUserId(111).upUserNickname("123").upDate(DateUtil.getNow())
            .visitCount(1)
            .build();

        announceDao.updateAnnounce(announce);
    }

    @Test
    public void test() {
        new Announce();
    }

}