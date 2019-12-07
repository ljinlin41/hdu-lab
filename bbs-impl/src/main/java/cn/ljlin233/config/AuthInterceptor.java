package cn.ljlin233.config;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.ljlin233.user.dao.UserRoleDao;
import cn.ljlin233.user.service.UserTokenService;
import cn.ljlin233.util.auth.AdminAuth;
import cn.ljlin233.util.auth.MyselfAuth;
import cn.ljlin233.util.auth.RootAuth;
import cn.ljlin233.util.auth.StudentAuth;
import cn.ljlin233.util.auth.TeacherAuth;
import cn.ljlin233.util.auth.dao.AuthDao;
import cn.ljlin233.util.exception.entity.DataCheckedException;

/**
 * AuthInterceptor
 *
 * @author lvjinlin42@foxmail.com
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private AuthDao authDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {

        //System.out.println("\nenter Interceptor \n");

        boolean needAuth = false;
        boolean hasAuth = false;
        boolean setMessage = false;
        DataCheckedException ex = new DataCheckedException();

        String token = request.getHeader("token");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Integer userId = null;

        // 需要自有权限
        MyselfAuth myselfAuth = method.getAnnotation(MyselfAuth.class);
        if (myselfAuth != null && !hasAuth) {
            needAuth = true;

            if (userId == null) {
                userId = getUserIdByToken(token);
            }

            String tableName = myselfAuth.tableName();
            String column = myselfAuth.column();
            Integer id = Integer.valueOf(request.getParameter("id"));
            Integer ownerId = authDao.getOwnerId(tableName, column, id);

            if (!ownerId.equals(userId)) {
                if (!setMessage) {
                    ex.setMessage("此操作需本人权限!");
                    setMessage = true;
                }
            } else {
                hasAuth = true;
            }
        }

        // 需要student权限
        StudentAuth studentAuth = method.getAnnotation(StudentAuth.class);
        if (studentAuth != null && !hasAuth) {
            needAuth = true;

            if (userId == null) {
                userId = getUserIdByToken(token);
            }

            List<String> role = userRoleDao.getUserRoleByUserId(userId);
            if (!role.contains("student")) {
                if (!setMessage) {
                    ex.setMessage("此操作需student权限，请联系管理员!");
                    setMessage = true;
                }
            } else {
                hasAuth = true;
            }
        }

        // 需要teacher权限
        TeacherAuth teacherAuth = method.getAnnotation(TeacherAuth.class);
        if (teacherAuth != null && !hasAuth) {
            needAuth = true;
            if (userId == null) {
                userId = getUserIdByToken(token);
            }
            List<String> role = userRoleDao.getUserRoleByUserId(userId);
            if (!role.contains("teacher")) {
                if (!setMessage) {
                    ex.setMessage("此操作需教师权限，请联系管理员!");
                    setMessage = true;
                }
            } else {
                hasAuth = true;
            }
        }
        // 需要管理员权限
        AdminAuth adminAuth = method.getAnnotation(AdminAuth.class);
        if (adminAuth != null && !hasAuth) {
            needAuth = true;
            if (userId == null) {
                userId = getUserIdByToken(token);
            }
            List<String> role = userRoleDao.getUserRoleByUserId(userId);
            if (!role.contains("admin")) {
                if (!setMessage) {
                    ex.setMessage("此操作需管理员权限，请联系超级管理员!");
                    setMessage = true;
                }
            } else {
                hasAuth = true;
            }
        }
        // 需要超级管理员权限
        RootAuth rootAuth = method.getAnnotation(RootAuth.class);
        if (rootAuth != null && !hasAuth) {
            needAuth = true;
            if (userId == null) {
                userId = getUserIdByToken(token);
            }
            List<String> role = userRoleDao.getUserRoleByUserId(userId);
            if (!role.contains("root")) {
                if (!setMessage) {
                    ex.setMessage("此操作需root权限!");
                    setMessage = true;
                }
            } else {
                hasAuth = true;
            }
        }

        if (!needAuth || hasAuth) {
            return true;
        } else {
            throw ex;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception {

    }

    private Integer getUserIdByToken(String token) {
        // 判断是否登录

        if (token == null || token.length() == 0) {
            throw new DataCheckedException("请先登录!");
        }

        Integer userId = userTokenService.getUserid(token);

        if (userId == null) {
            throw new DataCheckedException("登录已过期，请重新登录!");
        }
        return userId;
    }

}