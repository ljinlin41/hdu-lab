package cn.ljlin233.introduce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.introduce.dto.DeleteAchievementRequestDto;
import cn.ljlin233.introduce.dto.InsertAchievementRequestDto;
import cn.ljlin233.introduce.dto.UpdateAchievementRequestDto;
import cn.ljlin233.introduce.entity.Achievement;
import cn.ljlin233.introduce.service.AchievementService;
import cn.ljlin233.util.Page;

/**
 * AchievementController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    /**
     * 获取所有成就
     *
     * @return result
     */
    @GetMapping(value = "/achievements")
    public Page<Achievement> getAllAchievements() {

        return achievementService.getAllAchievements();
    }

    /**
     * 按页获取所有成就
     *
     * @param page 第N页
     * @return result
     */
    @GetMapping(value = "/achievements", params = "page")
    public Page<Achievement> getAchievementsPage(@RequestParam int page) {

        return achievementService.getAchievementsPage(page, 10);
    }

    /**
     * 增加一个成就
     */
    @PostMapping(value = "/achievements")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    public void addAchievement(@RequestBody InsertAchievementRequestDto request) {

        achievementService.addAchievement(request);
    }

    /**
     * 按页搜索成就
     *
     * @param search 搜索标题
     * @param page 第N页
     * @return result
     */
    @GetMapping(value = "/achievements", params = {"search", "page"})
    public Page<Achievement> searchAchievements(@RequestParam String search, @RequestParam int page) {

        return achievementService.searchAchievements(search, page, 10);
    }

    /**
     * 获取成就详情
     *
     * @param id id
     * @return result
     */
    @GetMapping(value = "/achievements", params = "id")
    public Achievement getAchievementsById(@RequestParam int id) {

        return achievementService.getAchievementById(id);
    }

    /**
     * 更新成就
     */
    @PutMapping(value = "/achievements")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId == #request.upUserId")
    public void updateAchievement(@RequestBody UpdateAchievementRequestDto request) {

        achievementService.updateAchievement(request);
    }

    /**
     * 删除成就
     *
     * @param request 请求
     */
    @DeleteMapping(value = "/achievements")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId == #request.upUserId")
    public void deleteAchievement(@RequestBody DeleteAchievementRequestDto request) {
        achievementService.deleteAchievement(request.getAchievementId());
    }

}