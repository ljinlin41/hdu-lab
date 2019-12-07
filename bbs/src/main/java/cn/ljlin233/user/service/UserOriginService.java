package cn.ljlin233.user.service;

/**
 * UserOriginService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserOriginService {

    /**
     * 添加原始账号
     *
     * @param account 账号
     */
    void addUserOrigin(String account);

    /**
     * 检查原始账号是否存在
     *
     * @param account 账号
     * @return true or false
     */
    boolean checkUserOrigin(String account);

    /**
     * 删除原始账号
     *
     * @param account 账号
     */
    void deleteUserOrigin(String account);

}