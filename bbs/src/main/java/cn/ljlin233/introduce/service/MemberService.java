package cn.ljlin233.introduce.service;

import cn.ljlin233.introduce.dto.InsertMemberRequestDto;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.util.Page;

/**
 * MemberService
 *
 * @author lvjinlin42@foxmail.com
 */
public interface MemberService {

    /**
     * 获取所有成员
     *
     * @return 成员列表
     */
    Page<Member> getAllMembers();

    /**
     * 按页获取成员
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成员列表
     */
    Page<Member> getAllMembersByPage(int pageNum, int pageSize);


    /**
     * 根据成员Id获取成员
     *
     * @param id 成员Id
     * @return 成员
     */
    Member getMemberById(int id);

    /**
     * 获取部门所有成员
     *
     * @param departmentId 部门Id
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成员列表
     */
    Page<Member> getMemberByDepartment(int departmentId, int pageNum, int pageSize);

    /**
     * 根据成员名称搜索成员
     *
     * @param name 成员名称
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果列表
     */
    Page<Member> searchMembersByName(String name, int pageNum, int pageSize);


    /**
     * 增加一个成员
     *
     * @param request request
     */
    void addMember(InsertMemberRequestDto request);

    /**
     * 删除一个成员
     *
     * @param id 成员Id
     */
    void deleteMember(int id);

}