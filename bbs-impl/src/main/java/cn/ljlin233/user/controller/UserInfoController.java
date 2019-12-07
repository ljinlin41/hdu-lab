package cn.ljlin233.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.auth.AdminAuth;
import cn.ljlin233.util.auth.MyselfAuth;
import cn.ljlin233.util.auth.RootAuth;

/**
 * UserInfoController
 */
@Controller
@RequestMapping("/api")
public class UserInfoController {

    private UserInfoService userInfoService;

    public UserInfoController() {}

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String getAllUserInfo() {
        return "ALL";
    }

    @MyselfAuth(tableName = "user_info", column = "id")
    @AdminAuth
    @RootAuth
    @RequestMapping(value = {"/user"}, params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam int id) {
        UserInfo userInfo = userInfoService.getUserInfo(id);
        return userInfo;
    }

    @MyselfAuth(tableName = "user_info", column = "id")
    @RequestMapping(value = {"/user"}, params = {"id", "newname"}, method = RequestMethod.PUT)
    public void updateUserNickname(@RequestParam int id, @RequestParam String newname) {

        userInfoService.updateNickname(id, newname);

    }

    @MyselfAuth(tableName = "user_info", column = "id")
    @RequestMapping(value = {"/user"}, params = {"id", "description"}, method = RequestMethod.PUT)
    public void updateUserDescription(@RequestParam int id, @RequestParam String description) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setIntroduction(description);
        //System.out.println(123);
        userInfoService.updateUserInfo(userInfo);
    }

    @MyselfAuth(tableName = "user_info", column = "id")
    @RequestMapping(value = {"/user"}, params = {"id", "picture"}, method = RequestMethod.PUT)
    public void updateUserPicture(@RequestParam int id, @RequestParam String picture) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setProfilePicture(picture);

        userInfoService.updateUserInfo(userInfo);

    }

    @MyselfAuth(tableName = "user_info", column = "id")
    @RequestMapping(value = {"/user"}, params = {"id"}, method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int id) {

        userInfoService.deleteUser(id);
    }

}