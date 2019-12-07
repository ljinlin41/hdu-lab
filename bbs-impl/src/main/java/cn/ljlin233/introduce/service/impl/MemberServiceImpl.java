package cn.ljlin233.introduce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.introduce.dao.MemberDao;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.MemberService;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * MemberServiceImpl
 */
@Service
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;

    public MemberServiceImpl() {}

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> all;
        try {
            all = memberDao.getAllMembers();
        } catch (Exception e) {
            throw new SystemException("获取成员介绍失败", e.getMessage());
        }
        return all;
    }

    @Override
    public List<Member> getAllMembersByPage(int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Member> result = null;
        try {
            result = memberDao.getMembersByPage(start, pageNum);
        } catch (Exception e) {
            throw new SystemException("按页获取成员介绍失败", e.getMessage());
        }
        return result;
    }

    @Override
    public int getAllMembersCounts() {
        int count = 0;
        try {
            count = memberDao.getMemberCounts();
        } catch (Exception e) {
            throw new SystemException("获取成员数量失败", e.getMessage());
        }
        return count;
    }

    @Override
    public Member getMemberById(int id) {
        Member result = null;
        try {
            result = memberDao.getMemberById(id);
        } catch (Exception e) {
            throw new SystemException("获取成员信息失败", e.getMessage());
        }

        return result;
    }

    @Override
    public List<Member> searchMembersByName(String name, int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Member> result = null;

        try {
            result = memberDao.searchMemberByName(name, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("搜索成员失败", e.getMessage());
        }

        return result;
    }

    @Override
    public int getSearchCounts(String name) {
        int count = 0;
        try {
            count = memberDao.getSearchCounts(name);
        } catch (Exception e) {
            throw new SystemException("获取搜索成员数量失败", e.getMessage());
        }
        return count;
    }

    @Override
    public void addMember(int memberId, String memberType, String memberName, int departmentId) {
        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberType(memberType);
        member.setMemberName(memberName);
        member.setDepartmentId(departmentId);

        try {
            memberDao.addMember(member);
        } catch (Exception e) {
            throw new SystemException("添加成员失败", e.getMessage());
        }
    }

    @Override
    public void deleteMember(int id) {
        try {
            memberDao.deleteMember(id);
        } catch (Exception e) {
            throw new SystemException("删除成员失败", e.getMessage());
        }
    }

    @Override
    public List<Member> getTeacherMember(int departmentId) {
        List<Member> result = null;
        try {
            result = memberDao.getTeacherMember(departmentId);
        } catch (Exception e) {
            throw new SystemException("获取部门教师失败", e.getMessage());
        }

        return result;
    }

    // @Override
    // public List<Member> getMembersByMemberId(int memberId) {
    //     List<Member> result = null;
    //     try {
    //         result = memberDao.getMembersByMemberId(memberId);
    //     } catch (Exception e) {
    //         throw new SystemException("获取教师所在部门失败", e.getMessage());
    //     }
    //
    //     return result;
    // }
}