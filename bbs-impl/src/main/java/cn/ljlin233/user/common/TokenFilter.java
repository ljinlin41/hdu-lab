package cn.ljlin233.user.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.ljlin233.user.dao.UserTokenDao;
import cn.ljlin233.user.entity.UserToken;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/9 14:47
 */
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserTokenDao userTokenDao;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        String token = request.getHeader("token");

        if (token != null) {

            UserToken userToken = userTokenDao.getUserToken(token);

            if (userToken != null) {

                List<SimpleGrantedAuthority> simpleGrantedAuthorityList = userToken.getRole().stream().collect(
                    ArrayList::new, (list, role) -> list.add(new SimpleGrantedAuthority("ROLE_" + role)),
                    ArrayList::addAll);

                MyUserDetail user = new MyUserDetail("000", "", simpleGrantedAuthorityList);
                user.setUserId(userToken.getUserId());

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    "", simpleGrantedAuthorityList);

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }

        filterChain.doFilter(request, response);

    }
}
