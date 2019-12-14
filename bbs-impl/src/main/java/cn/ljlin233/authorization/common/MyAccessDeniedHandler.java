package cn.ljlin233.authorization.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 处理权限校验异常，未授权
 *
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/9 15:54
 */
@Component
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        String sb = "{\"status\":\"error\",\"msg\":\"" + "未授权!" + "\"}";
        out.write(sb);
        out.flush();

        log.info("未授权");
    }
}
