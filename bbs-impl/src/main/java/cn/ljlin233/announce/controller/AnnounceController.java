package cn.ljlin233.announce.controller;

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

import cn.ljlin233.announce.dto.DeleteAnnounceRequestDto;
import cn.ljlin233.announce.dto.InsertAnnounceRequestDto;
import cn.ljlin233.announce.dto.UpdateAnnounceRequestDto;
import cn.ljlin233.announce.entity.Announce;
import cn.ljlin233.announce.service.AnnounceService;
import cn.ljlin233.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * AnnounceController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "通知公告接口") // 注意不要带中文，扩展时按钮无效
public class AnnounceController {

    @Autowired
    private AnnounceService announceService;

    /**
     * 获取通知列表
     *
     * @return result
     */
    @ApiOperation(value = "获取通知列表")
    @GetMapping(value = "/announceList")
    public Page<Announce> getAllAnnounce() {

        return announceService.getAllAnnounces();
    }

    /**
     * 按页获取通知列表
     *
     * @param page 第N页
     * @return result
     */
    @ApiOperation(value = "按页获取通知列表")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @GetMapping(value = "/announcesPage", params = "page")
    public Page<Announce> getAnnouncesByPage(@RequestParam int page) {

        return announceService.getAnnouncesByPage(page, 10);
    }

    /**
     * 根据通知Id获取通知
     *
     * @param id 通知Id
     * @return result
     */
    @ApiOperation(value = "根据通知Id获取通知")
    @ApiImplicitParam(name = "id", value = "通知Id", dataType = "int")
    @GetMapping(value = "/announcesId", params = "id")
    public Announce getAnnounceById(@RequestParam int id) {

        return announceService.getAnnounceById(id);
    }

    /**
     * 按标题搜索通知
     *
     * @param search 搜索标题
     * @param page 第N页结果
     * @return result
     */
    @ApiOperation(value = "按标题搜索通知")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索标题", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/searchAnnounce", params = {"search", "page"})
    public Page<Announce> searchAnnounces(@RequestParam String search, @RequestParam int page) {

        return announceService.searchAnnounces(search, page, 10);
    }

    /**
     * 添加一个通知
     *
     * @param request request
     */
    @ApiOperation(value = "添加一个通知")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertAnnounceRequestDto")
    @PostMapping(value = "/insertAnnounce")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    public void addAnnounce(@RequestBody InsertAnnounceRequestDto request) {

        announceService.addAnnounce(request);
    }

    /**
     * 更新一个通知
     *
     * @param request request
     */
    @ApiOperation(value = "更新一个通知")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "UpdateAnnounceRequestDto")
    @PutMapping(value = "/updateAnnounce")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId() == #request.upUserId")
    public void updateAnnounce(@RequestBody UpdateAnnounceRequestDto request) {

        announceService.updateAnnounce(request);
    }

    /**
     * 删除一个通知
     *
     * @param request request
     */
    @ApiOperation(value = "删除一个通知")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteAnnounceRequestDto")
    @DeleteMapping(value = "/deleteAnnounce")
    @PreAuthorize("hasAnyRole('admin', 'root') or authentication.principal.getUserId() == #request.upUserId")
    public void deleteAnnounce(@RequestBody DeleteAnnounceRequestDto request) {
        announceService.deleteAnnounce(request.getAnnounceId());
    }

}