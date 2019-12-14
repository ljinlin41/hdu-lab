package cn.ljlin233.authorization.service;

/**
 * UserActiveService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserActiveService {

    /**
     * 激活用户
     *
     * @param activeId 激活码
     */
    void activeUser(String activeId);

    void storeActive(String active, int userId);
}