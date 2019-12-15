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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * AchievementController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "研究成果接口")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    /**
     * 获取所有成就
     *
     * @return result
     */
    @ApiOperation(value = "获取成就列表")
    @GetMapping(value = "/achievementList")
    public Page<Achievement> getAllAchievements() {

        return achievementService.getAllAchievements();
    }

    /**
     * 按页获取所有成就
     *
     * @param page 第N页
     * @return result
     */
    @ApiOperation(value = "按页获取成就列表")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @GetMapping(value = "/achievementPage", params = "page")
    public Page<Achievement> getAchievementsPage(@RequestParam int page) {

        return achievementService.getAchievementsPage(page, 10);
    }

    /**
     * 增加一个成就
     */
    @ApiOperation(value = "增加一个成就")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertAchievementRequestDto")
    @PostMapping(value = "/insertAchievement")
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
    @ApiOperation(value = "按标题搜索成就")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索标题", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/searchAchievement", params = {"search", "page"})
    public Page<Achievement> searchAchievements(@RequestParam String search, @RequestParam int page) {

        return achievementService.searchAchievements(search, page, 10);
    }

    /**
     * 获取成就详情
     *
     * @param id id
     * @return result
     */
    @ApiOperation(value = "根据成就Id获取成就")
    @ApiImplicitParam(name = "id", value = "成就Id", dataType = "int")
    @GetMapping(value = "/achievementId", params = "id")
    public Achievement getAchievementsById(@RequestParam int id) {

        return achievementService.getAchievementById(id);
    }

    /**
     * 更新成就
     */
    @ApiOperation(value = "更新一个成就")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "UpdateAchievementRequestDto")
    @PutMapping(value = "/updateAchievement")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId == #request.upUserId")
    public void updateAchievement(@RequestBody UpdateAchievementRequestDto request) {

        achievementService.updateAchievement(request);
    }

    /**
     * 删除成就
     *
     * @param request 请求
     */
    @ApiOperation(value = "删除一个成就")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteAchievementRequestDto")
    @DeleteMapping(value = "/deleteAchievement")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId == #request.upUserId")
    public void deleteAchievement(@RequestBody DeleteAchievementRequestDto request) {
        achievementService.deleteAchievement(request.getAchievementId());
    }

}