package cn.ljlin233.user.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.ljlin233.user.dto.SecurityUserDto;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/9 14:47
 */
@Slf4j
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        String token = request.getHeader("token");
        if (token != null) {

            List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_student"));

            SecurityUserDto securityUserDto = new SecurityUserDto("1", "202cb962ac59075b964b07152d234b70",
                simpleGrantedAuthorityList);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                securityUserDto, securityUserDto.getPassword(), securityUserDto.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            log.info("enter token filter");

        }

        filterChain.doFilter(request, response);

    }
}
