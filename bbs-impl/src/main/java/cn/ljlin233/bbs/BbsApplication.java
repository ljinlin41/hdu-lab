package cn.ljlin233.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author lvjinlin42@foxmail.com
 */
@SpringBootApplication(
    scanBasePackages = {"cn.ljlin233.announce.*", "cn.ljlin233.user.*", "cn.ljlin233.util.*", "cn.ljlin233.config",
        "cn.ljlin233.user.*"})
@MapperScan(basePackages = {"cn.ljlin233.introduce.dao.mapper", "cn.ljlin233.announce.dao.mapper",
    "cn.ljlin233.user.dao.mapper"})
@ServletComponentScan(basePackages = "cn.ljlin233.config")
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class, args);
    }

}
