package cn.ljlin233.introduce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.introduce.service.ApplyService;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.auth.AdminAuth;
import cn.ljlin233.util.auth.MyselfAuth;
import cn.ljlin233.util.auth.RootAuth;
import cn.ljlin233.util.auth.StudentAuth;
import cn.ljlin233.util.auth.TeacherAuth;

/**
 * ApplyController
 */
@Controller
@RequestMapping("/api")
public class ApplyController {

    private ApplyService applyService;

    @Autowired
    private UserTokenService userTokenService;

    public ApplyController() {}

    @Autowired
    public ApplyController(ApplyService applyService) {

        this.applyService = applyService;
    }

    @MyselfAuth(tableName = "intro_apply", column = "user_id")
    @TeacherAuth
    @AdminAuth
    @RootAuth
    @RequestMapping(value = "/applys", params = "userId", method = RequestMethod.GET)
    @ResponseBody
    public List<Apply> getApplysByUserId(@RequestParam int userId) {

        List<Apply> applys = applyService.getApplyByUserId(userId);

        return applys;
    }

    @StudentAuth
    @TeacherAuth
    @RootAuth
    @AdminAuth
    @RequestMapping(value = "/applys", method = RequestMethod.POST)
    public void addApply(HttpServletRequest request) {
        Integer userId = 0;
        Integer departmentId = 0;
        if (request.getParameter("userId") != null) {
            userId = Integer.valueOf(request.getParameter("userId"));
        }
        if (request.getParameter("departmentId") != null) {
            departmentId = Integer.valueOf(request.getParameter("departmentId"));
        }
        String username = request.getParameter("username");
        String applyType = request.getParameter("applyType");

        applyService.addApply(userId, username, applyType, departmentId);
    }

    @StudentAuth
    @TeacherAuth
    @RootAuth
    @AdminAuth
    @RequestMapping(value = "/applys/unhandle", method = RequestMethod.GET)
    @ResponseBody
    public List<Apply> getUnhandleApply(HttpServletRequest request) {
        Integer userId = userTokenService.getUserid(request.getHeader("token"));

        List<Apply> applies = applyService.getUnhandleApply(userId);

        return applies;
    }

}