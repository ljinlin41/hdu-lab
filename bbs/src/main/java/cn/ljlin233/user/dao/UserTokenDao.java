package cn.ljlin233.user.dao;

/**
 * UserTokenDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserTokenDao {

    /**
     * 添加一个token
     *
     * @param token token
     * @param userId 用户Id
     */
    void addToken(String token, String userId);

    /**
     * 根据token获取用户Id
     *
     * @param token token
     * @return 用户Id
     */
    String getUserId(String token);

    /**
     * 删除token
     *
     * @param token token
     */
    void deleteToken(String token);

    /**
     * 刷新token过期时间
     *
     * @param token token
     */
    void refreshToken(String token);

    /**
     * 获取token开始时间
     *
     * @param token token
     * @return 开始时间
     */
    long getTokenTime(String token);

    /**
     * 判断token是否存在
     *
     * @param token token
     * @return true or false
     */
    boolean hasToken(String token);

}