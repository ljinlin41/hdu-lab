package cn.ljlin233.introduce.service;

import java.util.List;

import cn.ljlin233.introduce.entity.Member;

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
    List<Member> getAllMembers();

    /**
     * 按页获取成员
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成员列表
     */
    List<Member> getAllMembersByPage(int pageNum, int pageSize);

    /**
     * 获取成员总数
     *
     * @return 成员总数
     */
    int getAllMembersCounts();

    /**
     * 根据成员Id获取成员
     *
     * @param id 成员Id
     * @return 成员
     */
    Member getMemberById(int id);

    /**
     * 根据成员名称搜索成员
     *
     * @param name 成员名称
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果列表
     */
    List<Member> searchMembersByName(String name, int pageNum, int pageSize);

    /**
     * 获取搜索结果数量
     *
     * @param name 搜索名称
     * @return 搜索结果数量
     */
    int getSearchCounts(String name);

    /**
     * 增加一个成员
     *
     * @param memberId 用户Id
     * @param memberType 成员类型
     * @param memberName 成员名称
     * @param departmentId 所属部门Id
     */
    void addMember(int memberId, String memberType, String memberName, int departmentId);

    /**
     * 删除一个成员
     *
     * @param id 成员Id
     */
    void deleteMember(int id);

    /**
     * 获取部门所有成员
     *
     * @param departmentId 部门Id
     * @return 成员列表
     */
    List<Member> getTeacherMember(int departmentId);

}