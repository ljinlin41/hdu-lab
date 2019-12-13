package cn.ljlin233.introduce.dao;

import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.util.Page;

/**
 * MemberDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface MemberDao {

    /**
     * 获取所有部门成员
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
    Page<Member> getMembersByPage(int pageNum, int pageSize);

    /**
     * 按成员Id获取成员信息
     *
     * @param id 成员Id
     * @return 成员
     */
    Member getMemberById(int id);

    /**
     * 按用户Id获取成员列表
     *
     * @param memberId 用户Id
     * @return 成员列表
     */
    Page<Member> getMembersByMemberId(int memberId);

    /**
     * 按部门Id获取成员
     *
     * @param departmentId 部门Id
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 教师列表
     */
    Page<Member> getMemberByDepartment(int departmentId, int pageNum, int pageSize);

    /**
     * 获取部门中的教师成员
     *
     * @param departmentId 部门id
     * @return 教师列表
     */
    Page<Member> getTeacherByDepartment(int departmentId);

    /**
     * 根据姓名搜索成员
     *
     * @param name 搜索姓名
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果列表
     */
    Page<Member> searchMemberByName(String name, int pageNum, int pageSize);


    /**
     * 添加一个成员
     *
     * @param member 成员
     */
    void addMember(Member member);

    /**
     * 根据成员Id删除一个成员
     *
     * @param member 成员
     */
    void deleteMember(Member member);

}