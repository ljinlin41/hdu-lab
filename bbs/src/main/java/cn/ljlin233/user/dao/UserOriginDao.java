package cn.ljlin233.user.dao;

/**
 * UserOriginDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserOriginDao {

    /**
     * 增加原始账号
     *
     * @param account 账号
     */
    void addUserOrigin(String account);

    /**
     * 删除原始账号
     *
     * @param account 账号
     */
    void deleteUserOrigin(String account);

    /**
     * 根据账号获取账号Id
     *
     * @param account 账号
     * @return 账号id
     */
    Integer getUserOriginId(String account);

    /**
     * 判断账号是否存在
     *
     * @param account 账号
     * @return true or false
     */
    boolean existsAccount(String account);
}