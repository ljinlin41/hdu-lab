package cn.ljlin233.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.ljlin233.user.dao.UserTokenDao;
import cn.ljlin233.user.entity.UserToken;
import cn.ljlin233.util.common.UserContext;
import cn.ljlin233.util.common.UserContextUtil;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 19:06
 */
@WebFilter
public class UserContextFilter extends OncePerRequestFilter {

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        String token = request.getHeader("token");

        if (token != null) {

            UserToken userToken = userTokenDao.getUserToken(token);

            if (userToken != null) {

                UserContext userContext = UserContext.builder().id(userToken.getUserId()).nickName(
                    userToken.getNickName()).build();
                UserContextUtil.setUserContext(userContext);
            }

        }

        filterChain.doFilter(request, response);
    }
}
