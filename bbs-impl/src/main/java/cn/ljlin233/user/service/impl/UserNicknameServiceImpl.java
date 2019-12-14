package cn.ljlin233.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.announce.service.AnnounceService;
import cn.ljlin233.introduce.service.AchievementService;
import cn.ljlin233.introduce.service.AwardService;
import cn.ljlin233.introduce.service.JobService;
import cn.ljlin233.resource.service.ResourceService;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.user.service.UserNicknameService;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/14 15:40
 */
@Service
public class UserNicknameServiceImpl implements UserNicknameService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AchievementService achievementService;

    @Autowired
    private AwardService awardService;

    @Autowired
    private JobService jobService;

    @Autowired
    private AnnounceService announceService;

    @Autowired
    private ResourceService resourceService;

    @Override
    public void updateNickname(int userId, String nickname) {

        // 用户信息表
        userInfoService.updateNickname(userId, nickname);
        // 研究成果表
        achievementService.updateNickname(userId, nickname);
        // 荣誉奖项表
        awardService.updateNickname(userId, nickname);
        // 招聘信息表
        jobService.updateNickname(userId, nickname);
        // 通知公告表
        announceService.updateNickname(userId, nickname);
        // 资源表
        resourceService.updateNickname(userId, nickname);
    }
}
