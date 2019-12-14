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

    Page<UserOrigin> getUserOriginByPage(int pageNum, int pageSize);

}