package cn.ljlin233.introduce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.introduce.entity.AchievementResponse;
import cn.ljlin233.introduce.service.AchievementService;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.auth.AdminAuth;
import cn.ljlin233.util.auth.MyselfAuth;
import cn.ljlin233.util.auth.RootAuth;
import cn.ljlin233.util.auth.TeacherAuth;

/**
 * AchievementController
 */
@Controller
@RequestMapping("/api")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    @Autowired
    private UserTokenService userTokenService;

    public AchievementController() {}

    // 增加一个资源
    @TeacherAuth
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/achievements", method = RequestMethod.POST)
    public void addAchievement(HttpServletRequest request) {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Integer userId = userTokenService.getUserid(request.getHeader("token"));

        achievementService.addAchievement(title, content, userId);

    }

    // 获取所有资源
    @RootAuth
    @RequestMapping(value = "/achievements", method = RequestMethod.GET)
    @ResponseBody
    public AchievementResponse getAllAchievements() {
        List<Achievement> all = achievementService.getAllAchievements();
        int count = achievementService.getAchievementCount();

        return new AchievementResponse(count, all);
    }

    // 按页获取所有资源
    @RequestMapping(value = "/achievements", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public AchievementResponse getAchievementsPage(@RequestParam int page) {

        List<Achievement> result = achievementService.getAchievementsPage(page, 10);
        int count = achievementService.getAchievementCount();

        return new AchievementResponse(count, result);
    }

    // 按页搜索资源
    @RequestMapping(value = "/achievements", params = {"search", "page"}, method = RequestMethod.GET)
    @ResponseBody
    public AchievementResponse searchAchievements(@RequestParam String search, @RequestParam int page) {

        List<Achievement> result = achievementService.searchAchievements(search, page, 10);
        int count = achievementService.getSearchCount(search);

        return new AchievementResponse(count, result);
    }

    // 获取资源详情
    @RequestMapping(value = "/achievements", params = "id", method = RequestMethod.GET)
    @ResponseBody
    public Achievement getAchievementsById(@RequestParam int id) {

        Achievement result = achievementService.getAchievementById(id);
        return result;
    }

    // 更新资源
    @MyselfAuth(tableName = "intro_achievement", column = "up_userid")
    @RequestMapping(value = "/achievements", params = "id", method = RequestMethod.PUT)
    public void updateAchievement(@RequestParam int id, HttpServletRequest request) {
        Achievement achievement = new Achievement();
        achievement.setId(id);
        achievement.setTitle(request.getParameter("title"));
        achievement.setContent(request.getParameter("content"));

        achievementService.updateAchievement(achievement);
    }

    // 删除资源
    @MyselfAuth(tableName = "intro_achievement", column = "up_userid")
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/achievements", params = "id", method = RequestMethod.DELETE)
    public void deleteAchievement(@RequestParam int id) {
        achievementService.deleteAchievement(id);
    }

}