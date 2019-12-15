package cn.ljlin233.util.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 18:43
 */
@Slf4j
public class UserContextUtil {

    private static ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<>();

    public static void setUserContext(UserContext userContext) {

        log.info("当前用户: " + userContext);
        userContextThreadLocal.set(userContext);
    }

    public static UserContext getUserContext() {

        return userContextThreadLocal.get();
    }

    public static void removeUserContext() {
        userContextThreadLocal.remove();
    }
}
