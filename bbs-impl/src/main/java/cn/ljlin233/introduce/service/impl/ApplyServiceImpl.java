package cn.ljlin233.introduce.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.ApplyDao;
import cn.ljlin233.introduce.dao.MemberDao;
import cn.ljlin233.introduce.dto.AcceptApplyRequestDto;
import cn.ljlin233.introduce.dto.InsertApplyRequestDto;
import cn.ljlin233.introduce.dto.RejectApplyRequestDto;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.ApplyService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * ApplyServiceImpl
 * @author lvjinlin42@foxmail.com
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyDao applyDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void addApply(InsertApplyRequestDto request) {

        Apply apply = Apply.builder().userId(request.getUserId()).departmentId(request.getDepartmentId()).username(
            request.getUsername()).applyType(request.getApplyType()).applyStatus("待审核").build();

        applyDao.addApplys(apply);

        Member member = Member.builder().departmentId(request.getDepartmentId()).memberType("teacher").build();
        Page<Member> teacherPage = memberDao.getMemberList(member);

        sendEmailToTeacher(teacherPage.getData());
    }

    @Override
    public Apply getApplyById(int id) {

        return applyDao.getApplyById(id);
    }

    @Override
    public Page<Apply> getApplyByUserId(int userId) {

        return applyDao.getApplyByUserId(userId);
    }


    @Override
    public Page<Apply> getPendingApply(int userId) {

        // 首先获取该教师所在的部门
        Member memberSelect = Member.builder().memberId(userId).build();

        Page<Member> memberPage = memberDao.getMemberList(memberSelect);
        List<Member> memberList = memberPage.getData();

        Set<Integer> departmentIdSet = memberList.stream().collect(HashSet::new,
            (set, member) -> set.add(member.getDepartmentId()), HashSet::addAll);
        // 然后获取该部门所有未处理的申请

        return applyDao.getPendingApply(departmentIdSet);
    }

    @Override
    public void acceptApply(AcceptApplyRequestDto request) {

        // 更新apply状态
        Apply apply = Apply.builder().id(request.getApplyId()).applyStatus("已通过").build();
        applyDao.updateApply(apply);

        // 将成员加入对应部门
        Apply applyInfo = applyDao.getApplyById(request.getApplyId());

        Member member = Member.builder()
            .memberId(applyInfo.getUserId())
            .memberType(applyInfo.getApplyType())
            .memberName(applyInfo.getUsername())
            .departmentId(applyInfo.getDepartmentId())
            .build();

        memberDao.addMember(member);
    }

    @Override
    public void rejectApply(RejectApplyRequestDto request) {
        // 更新apply状态
        Apply apply = Apply.builder().id(request.getApplyId()).applyStatus("已拒绝").build();
        applyDao.updateApply(apply);
    }

    private void sendEmailToTeacher(List<Member> teacherList) {

        if (teacherList == null || teacherList.size() == 0) {
            throw new SystemException("该部门没有教师，请联系管理员", null);
        }

        for (Member teacher : teacherList) {
            int teacherId = teacher.getMemberId();
            UserInfo teacherInfo = userInfoService.getUserInfoByUserId(teacherId);
            String emailAddress = teacherInfo.getEmail();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1329540850@qq.com");
            message.setTo(emailAddress);
            message.setSubject("实验室成员申请通知");
            message.setText("有人申请加入部门，请处理");

            try {
                javaMailSender.send(message);
            } catch (Exception e) {
                throw new SystemException("激活邮件发送失败，重新注册", e.getMessage());
            }

        }

    }

}