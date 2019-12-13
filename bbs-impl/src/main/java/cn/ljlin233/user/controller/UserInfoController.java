package cn.ljlin233.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.user.dto.DeleteUserInfoRequestDto;
import cn.ljlin233.user.dto.UpdateUserInfoRequestDto;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;

/**
 * UserInfoController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return result
     */
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root') or authentication.principal.getUserId == #id")
    @GetMapping(value = "/user", params = "id")
    public UserInfo getUserInfo(@RequestParam int id) {

        return userInfoService.getUserInfo(id);
    }

    /**
     * 更新用户信息
     * @param request request
     */
    @PreAuthorize("authentication.principal.getUserId == #request.id")
    @PutMapping(value = "/user")
    public void updateUserInfo(@RequestBody UpdateUserInfoRequestDto request) {

        userInfoService.updateUserInfo(request);
    }

    /**
     * 删除用户
     * @param request request
     */
    @PreAuthorize("authentication.principal.getUserId == #request.id")
    @DeleteMapping(value = "/user")
    public void deleteUser(@RequestBody DeleteUserInfoRequestDto request) {

        userInfoService.deleteUser(request.getId());
    }

}