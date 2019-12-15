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

import cn.ljlin233.introduce.dto.DeleteAwardRequestDto;
import cn.ljlin233.introduce.dto.InsertAwardRequestDto;
import cn.ljlin233.introduce.dto.UpdateAwardRequestDto;
import cn.ljlin233.introduce.entity.Award;
import cn.ljlin233.introduce.service.AwardService;
import cn.ljlin233.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * AwardController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "荣誉奖项接口")
public class AwardController {

    @Autowired
    private AwardService awardService;

    /**
     * 获取所有奖项
     */
    @ApiOperation(value = "获取所有奖项")
    @GetMapping(value = "/awards")
    public Page<Award> getAllAwards() {

        return awardService.getAllAwards();
    }

    /**
     * 按页获取奖项
     *
     * @param page 第N页
     * @return result
     */
    @ApiOperation(value = "按页获取奖项")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @GetMapping(value = "/awardPage", params = "page")
    public Page<Award> getAwardsByPage(@RequestParam int page) {

        return awardService.getAwardsPage(page, 10);
    }

    /**
     * 按页搜索奖项
     *
     * @param search 搜索标题
     * @param page 第N页
     * @return result
     */
    @ApiOperation(value = "按标题搜索奖项")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索标题", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/searchAward", params = {"search", "page"})
    public Page<Award> searchAwards(@RequestParam String search, @RequestParam int page) {

        return awardService.searchAwards(search, page, 10);
    }

    /**
     * 获取奖项详情
     *
     * @param id 奖项id
     * @return result
     */
    @ApiOperation(value = "根据奖项Id获取奖项")
    @ApiImplicitParam(name = "id", value = "奖项Id", dataType = "int")
    @GetMapping(value = "/awardId", params = "id")
    public Award getAchievementsById(@RequestParam int id) {

        return awardService.getAwardById(id);
    }

    /**
     * 增加一个奖项
     *
     * @param request request
     */
    @ApiOperation(value = "增加一个奖项")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertAwardRequestDto")
    @PostMapping(value = "/insertAward")
    @PreAuthorize("hasAnyRole('teacher' , 'admin', 'root')")
    public void addAward(@RequestBody InsertAwardRequestDto request) {

        awardService.addAward(request);
    }

    /**
     * 更新奖项
     *
     * @param request request
     */
    @ApiOperation(value = "更新一个奖项")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "UpdateAwardRequestDto")
    @PutMapping(value = "/updateAward")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId == #request.upUserId")
    public void updateAward(@RequestBody UpdateAwardRequestDto request) {

        awardService.updateAward(request);
    }

    /**
     * 删除奖项
     *
     * @param request request
     */
    @ApiOperation(value = "删除一个奖项")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteAwardRequestDto")
    @DeleteMapping(value = "/deleteAward")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId == #request.upUserId")
    public void deleteAchievement(@RequestBody DeleteAwardRequestDto request) {

        awardService.deleteAward(request.getAwardId());
    }
}