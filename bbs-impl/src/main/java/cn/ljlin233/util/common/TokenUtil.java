package cn.ljlin233.util.common;

import java.util.UUID;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/9 23:25
 */
public class TokenUtil {

    public static synchronized String getToken() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
