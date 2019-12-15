package cn.ljlin233.util.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * String日期转换工具类, 单例模式
 *
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/5 17:27
 */
public class DateUtil {

    private DateUtil() {}

    private static class DateUtilHolder {
        private static final DateTimeFormatter INSTANCE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    private static DateTimeFormatter getInstance() {
        return DateUtilHolder.INSTANCE;
    }

    public static synchronized String getNow() {

        return getInstance().format(LocalDateTime.now());
    }
}
