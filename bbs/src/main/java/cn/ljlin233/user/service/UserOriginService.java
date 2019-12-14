package cn.ljlin233.user.service;

import cn.ljlin233.user.dto.DeleteUserOriginRequestDto;
import cn.ljlin233.user.dto.InsertUserOriginRequestDto;
import cn.ljlin233.user.entity.UserOrigin;
import cn.ljlin233.util.Page;

/**
 * UserOriginService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface UserOriginService {

    /**
     * 添加原始账号
     *
     * @param request 账号
     */
    void addUserOrigin(InsertUserOriginRequestDto request);


    /**
     * 删除原始账号
     *
     * @param request 账号
     */
    void deleteUserOrigin(DeleteUserOriginRequestDto request);

    /**
     * 按页获取原始账号
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return result
     */
    Page<UserOrigin> getUserOriginByPage(int pageNum, int pageSize);

    boolean existsAccount(String account);
}