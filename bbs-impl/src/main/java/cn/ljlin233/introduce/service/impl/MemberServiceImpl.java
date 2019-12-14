package cn.ljlin233.introduce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.introduce.dao.MemberDao;
import cn.ljlin233.introduce.dto.InsertMemberRequestDto;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.MemberService;
import cn.ljlin233.util.Page;

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

        return memberDao.getAllMembers();
    }

    @Override
    public Page<Member> getAllMembersByPage(int pageNum, int pageSize) {

        return memberDao.getMembersByPage(pageNum, pageSize);
    }


    @Override
    public Member getMemberById(int id) {

        return memberDao.getMemberById(id);
    }

    @Override
    public Page<Member> getMemberByDepartment(int departmentId, int pageNum, int pageSize) {

        return memberDao.getMemberByDepartment(departmentId, pageNum, pageSize);
    }


    @Override
    public Page<Member> searchMembersByName(String name, int pageNum, int pageSize) {

        return memberDao.searchMemberByName(name, pageNum, pageSize);
    }

    @Override
    public void addMember(InsertMemberRequestDto request) {
        Member member = Member.builder().memberId(request.getMemberId()).memberName(request.getMemberName()).memberType(
            request.getMemberType()).departmentId(request.getDepartmentId()).build();

        memberDao.addMember(member);

    }

    @Override
    public void deleteMember(int id) {

        Member member = memberDao.getMemberById(id);
        memberDao.deleteMember(member);

    }

}