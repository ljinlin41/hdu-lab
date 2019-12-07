package cn.ljlin233.introduce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.introduce.entity.AwardResponse;
import cn.ljlin233.introduce.service.AwardService;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.auth.AdminAuth;
import cn.ljlin233.util.auth.MyselfAuth;
import cn.ljlin233.util.auth.RootAuth;
import cn.ljlin233.util.auth.TeacherAuth;

/**
 * AwardController
 */
@Controller
@RequestMapping("/api")
public class AwardController {

    private AwardService awardService;

    private UserTokenService userTokenService;

    public AwardController() {}

    @Autowired
    public AwardController(AwardService awardService, UserTokenService userTokenService) {
        this.awardService = awardService;
        this.userTokenService = userTokenService;
    }

    // 增加一个奖项
    @TeacherAuth
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/awards", method = RequestMethod.POST)
    public void addAward(HttpServletRequest request) {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Integer userId = userTokenService.getUserid(request.getHeader("token"));

        awardService.addAward(title, content, userId);
    }

    // 获取所有奖项
    @RootAuth
    @RequestMapping(value = "/awards", method = RequestMethod.GET)
    @ResponseBody
    public AwardResponse getAllAwards() {
        List<Award> all = awardService.getAllAwards();
        int count = awardService.getAwardCount();

        return new AwardResponse(count, all);
    }

    // 按页获取奖项
    @RequestMapping(value = "/awards", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public AwardResponse getAwardsByPage(@RequestParam int page) {

        List<Award> result = awardService.getAwardsPage(page, 10);
        int count = awardService.getAwardCount();

        return new AwardResponse(count, result);
    }

    // 按页搜索奖项
    @RequestMapping(value = "/awards", params = {"search", "page"}, method = RequestMethod.GET)
    @ResponseBody
    public AwardResponse searchAwards(@RequestParam String search, @RequestParam int page) {

        List<Award> result = awardService.searchAwards(search, page, 10);
        int count = awardService.getSearchCount(search);

        return new AwardResponse(count, result);
    }

    // 获取资源详情
    @RequestMapping(value = "/awards", params = "id", method = RequestMethod.GET)
    @ResponseBody
    public Award getAchievementsById(@RequestParam String id) {
        Integer idInt = Integer.valueOf(id);
        Award result = awardService.getAwardById(idInt);
        return result;
    }

    // 更新资源
    @MyselfAuth(tableName = "intro_award", column = "up_userid")
    @RequestMapping(value = "/awards", params = "id", method = RequestMethod.PUT)
    public void updateAward(@RequestParam String id, HttpServletRequest request) {
        Award award = new Award();
        award.setId(Integer.valueOf(id));
        award.setTitle(request.getParameter("title"));
        award.setContent(request.getParameter("content"));

        awardService.updateAward(award);
    }

    // 删除资源
    @MyselfAuth(tableName = "intro_award", column = "up_userid")
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/awards", params = "id", method = RequestMethod.DELETE)
    public void deleteAchievement(@RequestParam String id) {
        Integer idInt = Integer.valueOf(id);
        awardService.deleteAward(idInt);
    }
}