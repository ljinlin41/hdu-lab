package cn.ljlin233.config;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import cn.ljlin233.util.common.UserContextUtil;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 19:19
 */
@WebListener
public class UserContextListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        UserContextUtil.removeUserContext();
    }
}
