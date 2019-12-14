package cn.ljlin233.user.dao;

import cn.ljlin233.user.entity.UserOrigin;
import cn.ljlin233.util.Page;

/**
 * UserOriginDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserOriginDao {

    /**
     * 按页获取原始账号
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return result
     */
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

    UserOrigin getOneUserOrigin(UserOrigin userOrigin);

}