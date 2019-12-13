package cn.ljlin233.introduce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.introduce.dao.MemberDao;
import cn.ljlin233.introduce.dto.InsertMemberRequestDto;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.MemberService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * MemberServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Page<Member> getAllMembers() {
        Page<Member> all;
        try {
            all = memberDao.getAllMembers();
        } catch (Exception e) {
            throw new SystemException("获取成员介绍失败", e.getMessage());
        }
        return all;
    }

    @Override
    public Page<Member> getAllMembersByPage(int pageNum, int pageSize) {

        Page<Member> result;
        try {
            result = memberDao.getMembersByPage(pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("按页获取成员介绍失败", e.getMessage());
        }
        return result;
    }


    @Override
    public Member getMemberById(int id) {
        Member result;
        try {
            result = memberDao.getMemberById(id);
        } catch (Exception e) {
            throw new SystemException("获取成员信息失败", e.getMessage());
        }

        return result;
    }

    @Override
    public Page<Member> getMemberByDepartment(int departmentId, int pageNum, int pageSize) {

        Page<Member> result;
        try {
            result = memberDao.getMemberByDepartment(departmentId, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("按部门获取成员介绍失败", e.getMessage());
        }
        return result;
    }


    @Override
    public Page<Member> searchMembersByName(String name, int pageNum, int pageSize) {

        Page<Member> result;

        try {
            result = memberDao.searchMemberByName(name, pageNum, pageSize);
        } catch (Exception e) {
            throw new SystemException("搜索成员失败", e.getMessage());
        }

        return result;
    }

    @Override
    public void addMember(InsertMemberRequestDto request) {
        Member member = Member.builder().memberId(request.getMemberId()).memberName(request.getMemberName()).memberType(
            request.getMemberType()).departmentId(request.getDepartmentId()).build();

        try {
            memberDao.addMember(member);
        } catch (Exception e) {
            throw new SystemException("添加成员失败", e.getMessage());
        }
    }

    @Override
    public void deleteMember(int id) {

        try {

            Member member = memberDao.getMemberById(id);
            memberDao.deleteMember(member);
        } catch (Exception e) {
            throw new SystemException("删除成员失败", e.getMessage());
        }
    }

}