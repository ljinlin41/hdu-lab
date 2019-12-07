package cn.ljlin233.user.service;

/**
 * UserRegisterService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserRegisterService {

    /**
     * 用户注册
     *
     * @param account 账号
     * @param password 密码
     * @param role 角色
     * @param email 邮箱
     * @param verificationId 验证码Id
     * @param verificationCode 验证码
     */
    void registerUser(String account, String password, String role, String email, String verificationId,
        String verificationCode);

}