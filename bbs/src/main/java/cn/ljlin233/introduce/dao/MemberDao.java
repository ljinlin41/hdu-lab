package cn.ljlin233.introduce.dao;

import java.util.List;

import cn.ljlin233.introduce.entity.Member;

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
    List<Member> getAllMembers();

    /**
     * 按页获取成员
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 成员列表
     */
    List<Member> getMembersByPage(int pageNum, int pageSize);

    /**
     * 按成员Id获取成员信息
     *
     * @param id 成员Id
     * @return 成员
     */
    Member getMemberById(int id);

    /**
     * 获取成员总数
     *
     * @return 成员数量
     */
    Integer getMemberCounts();

    /**
     * 根据姓名搜索成员
     *
     * @param name 搜索姓名
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 搜索结果列表
     */
    List<Member> searchMemberByName(String name, int pageNum, int pageSize);

    /**
     * 获取搜索结果总数
     *
     * @param name 搜索姓名
     * @return 结果总数
     */
    Integer getSearchCounts(String name);

    /**
     * 添加一个成员
     *
     * @param member 成员
     */
    void addMember(Member member);

    /**
     * 根据成员Id删除一个成员
     *
     * @param id 成员Id
     */
    void deleteMember(int id);

    /**
     * 获取部门所有教师
     *
     * @param departmentId 部门Id
     * @return 教师列表
     */
    List<Member> getTeacherMember(int departmentId);

    //List<Member> getMembersByMemberId(int memberId);

}