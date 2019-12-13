package cn.ljlin233.introduce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.ljlin233.introduce.dao.MemberDao;
import cn.ljlin233.introduce.dao.mapper.MemberMapper;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.util.Page;
import tk.mybatis.mapper.entity.Example;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/12 23:26
 */
@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Page<Member> getAllMembers() {

        List<Member> memberList = memberMapper.selectAll();
        PageInfo<Member> memberPageInfo = new PageInfo<>(memberList);

        return Page.<Member>builder().totalNum(memberPageInfo.getTotal()).data(new ArrayList<>(memberList)).build();
    }

    @Override
    public Page<Member> getMembersByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Member> memberList = memberMapper.selectAll();
        PageInfo<Member> memberPageInfo = new PageInfo<>(memberList);

        return Page.<Member>builder().totalNum(memberPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(memberList)).build();
    }

    @Override
    public Member getMemberById(int id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Member> getMembersByMemberId(int memberId) {

        Member member = Member.builder().memberId(memberId).build();

        List<Member> memberList = memberMapper.select(member);
        PageInfo<Member> memberPageInfo = new PageInfo<>(memberList);

        return Page.<Member>builder().totalNum(memberPageInfo.getTotal()).data(new ArrayList<>(memberList)).build();
    }

    @Override
    public Page<Member> getMemberByDepartment(int departmentId, int pageNum, int pageSize) {

        Member member = Member.builder().departmentId(departmentId).build();

        PageHelper.startPage(pageNum, pageSize);
        List<Member> memberList = memberMapper.select(member);
        PageInfo<Member> memberPageInfo = new PageInfo<>(memberList);

        return Page.<Member>builder().totalNum(memberPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(memberList)).build();
    }

    @Override
    public Page<Member> getTeacherByDepartment(int departmentId) {

        Member member = Member.builder().departmentId(departmentId).memberType("teacher").build();

        List<Member> memberList = memberMapper.select(member);
        PageInfo<Member> memberPageInfo = new PageInfo<>(memberList);

        return Page.<Member>builder().totalNum(memberPageInfo.getTotal()).data(new ArrayList<>(memberList)).build();
    }

    @Override
    public Page<Member> searchMemberByName(String name, int pageNum, int pageSize) {

        Example example = new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("memberName", "%" + name + "%");

        PageHelper.startPage(pageNum, pageSize);
        List<Member> memberList = memberMapper.selectByExample(example);
        PageInfo<Member> memberPageInfo = new PageInfo<>(memberList);

        return Page.<Member>builder().totalNum(memberPageInfo.getTotal()).pageNum(pageNum).pageSize(pageSize).data(
            new ArrayList<>(memberList)).build();
    }

    @Override
    public void addMember(Member member) {
        memberMapper.insertSelective(member);
    }

    @Override
    public void deleteMember(Member member) {
        memberMapper.delete(member);
    }
}
