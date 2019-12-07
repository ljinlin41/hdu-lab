package cn.ljlin233.user.service;

/**
 * UserTokenService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserTokenService {

    /**
     * 添加token
     *
     * @param userId 用户Id
     * @return token
     */
    String addToken(int userId);

    /**
     * 删除token
     *
     * @param userId 用户Id
     */
    void deleteToken(int userId);

    /**
     * 检查token是否存在，存在则刷新
     *
     * @param token token
     * @return true or false
     */
    boolean checkRefreshToken(String token);

    /**
     * 根据token获取用户Id
     *
     * @param token token
     * @return 用户id
     */
    Integer getUserid(String token);
}