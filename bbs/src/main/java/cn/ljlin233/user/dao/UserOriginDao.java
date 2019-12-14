package cn.ljlin233.user.dao;

import cn.ljlin233.user.entity.UserOrigin;
import cn.ljlin233.util.Page;

/**
 * UserOriginDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserOriginDao {

    Page<UserOrigin> getUserOriginByPage(int pageNum, int pageSize);

    /**
     * 增加原始账号
     *
     * @param userOrigin 原始账号
     */
    void addUserOrigin(UserOrigin userOrigin);

    /**
     * 删除原始账号
     *
     * @param userOrigin 原始账号
     */
    void deleteUserOrigin(UserOrigin userOrigin);


    /**
     * 判断账号是否存在
     *
     * @param account 账号
     * @return true or false
     */
    boolean existsAccount(String account);
}