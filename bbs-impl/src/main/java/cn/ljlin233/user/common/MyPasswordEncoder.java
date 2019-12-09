package cn.ljlin233.user.common;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/8 19:10
 */
@Slf4j
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        log.info("encode");
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    /**
     * 密码比较
     *
     * @param inputPassword 输入的密码
     * @param truePassword 数据库中的正确密码
     */
    @Override
    public boolean matches(CharSequence inputPassword, String truePassword) {

        String md5Password = DigestUtils.md5DigestAsHex(inputPassword.toString().getBytes());

        log.info(inputPassword + " " + truePassword);

        return true;
        //return md5Password.equals(truePassword);
    }
}
