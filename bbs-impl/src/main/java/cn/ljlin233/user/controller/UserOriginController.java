package cn.ljlin233.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.user.dto.DeleteUserOriginRequestDto;
import cn.ljlin233.user.dto.InsertUserOriginRequestDto;
import cn.ljlin233.user.entity.UserOrigin;
import cn.ljlin233.user.service.UserOriginService;
import cn.ljlin233.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/14 16:30
 */
@RestController
@RequestMapping("/api")
@Api(tags = "原始账号接口")
public class UserOriginController {

    @Autowired
    private UserOriginService userOriginService;

    /**
     * 添加原始账号
     *
     * @param reqeust request
     */
    @ApiOperation(value = "添加原始账号")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertUserOriginRequestDto")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @PostMapping(value = "/userOrigin")
    public void addUserOrigin(@RequestBody InsertUserOriginRequestDto reqeust) {
        userOriginService.addUserOrigin(reqeust);
    }

    /**
     * 按页获取原始账号
     *
     * @param page 页数
     * @return result
     */
    @ApiOperation(value = "按页获取原始账号")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @GetMapping(value = "/userOrigin", params = "page")
    public Page<UserOrigin> getUserOriginByPage(int page) {

        return userOriginService.getUserOriginByPage(page, 10);
    }

    /**
     * 删除原始账号
     *
     * @param request request
     */
    @ApiOperation(value = "删除原始账号")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteUserOriginRequestDto")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @DeleteMapping(value = "/userOrigin")
    public void deleteUserOrigin(@RequestBody DeleteUserOriginRequestDto request) {
        userOriginService.deleteUserOrigin(request);
    }
}
