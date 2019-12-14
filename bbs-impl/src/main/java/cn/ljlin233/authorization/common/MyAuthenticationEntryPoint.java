package cn.ljlin233.authorization.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 处理身份校验异常，未认证登录
 *
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/9 16:16
 */
@Component
@Slf4j
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        String sb = "{\"status\":\"error\",\"msg\":\"" + "未登陆!" + "\"}";
        out.write(sb);
        out.flush();

        log.info("未登录认证");

    }
}
