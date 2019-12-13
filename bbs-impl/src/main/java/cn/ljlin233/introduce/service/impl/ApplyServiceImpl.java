package cn.ljlin233.introduce.service.impl;

import java.util.ArrayList;
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
import cn.ljlin233.introduce.dto.InsertApplyRequestDto;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.ApplyService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.Page;
import cn.ljlin233.util.exception.entity.DataCheckedException;
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

        try {
            applyDao.addApplys(apply);
        } catch (Exception e) {
            throw new SystemException("提交入部申请失败", e.getMessage());
        }

        Page<Member> teacherPage = memberDao.getTeacherByDepartment(request.getDepartmentId());

        sendEmailToTeacher(teacherPage.getData());
    }

    @Override
    public Apply getApplyById(int id) {
        Apply apply;
        try {
            apply = applyDao.getApplyById(id);
        } catch (Exception e) {
            throw new SystemException("获取入部申请失败", e.getMessage());
        }
        return apply;
    }

    @Override
    public Page<Apply> getApplyByUserId(int userId) {
        Page<Apply> applys;
        try {
            applys = applyDao.getApplyByUserId(userId);
        } catch (Exception e) {
            throw new SystemException("获取入部申请失败", e.getMessage());
        }
        return applys;
    }



    @Override
    public Page<Apply> getPendingApply(int userId) {

        // 首先获取该教师所在的部门
        Page<Member> memberPage = memberDao.getMembersByMemberId(userId);
        List<Member> memberList = new ArrayList<>(memberPage.getData());

        Set<Integer> departmentIdSet = memberList.stream().collect(HashSet::new,
            (set, member) -> set.add(member.getDepartmentId()), HashSet::addAll);
        // 然后获取该部门所有未处理的申请

        return applyDao.getPendingApply(departmentIdSet);
    }

    public void updateApply(int id, String applyStatus) {
        Apply apply = Apply.builder().id(id).build();
        // 数据校验
        if (!(applyStatus.equals("已通过") || applyStatus.equals("已拒绝"))) {
            throw new DataCheckedException("申请表状态错误");
        } else {
            apply.setApplyStatus(applyStatus);
        }
        // 更新Apply状态
        try {
            applyDao.updateApply(apply);
        } catch (Exception e) {
            throw new SystemException("更新入部申请失败", e.getMessage());
        }

        if (applyStatus.equals("已通过")) {
            // 添加部门成员
            Apply applyInfo = applyDao.getApplyById(id);

            Member member = Member.builder()
                .memberId(applyInfo.getUserId())
                .memberType(applyInfo.getApplyType())
                .memberName(applyInfo.getUsername())
                .departmentId(applyInfo.getDepartmentId())
                .build();

            memberDao.addMember(member);
        }

    }

    private void sendEmailToTeacher(List<Member> teacherList) {

        if (teacherList == null || teacherList.size() == 0) {
            throw new SystemException("该部门没有教师，请联系管理员", null);
        }

        for (Member teacher : teacherList) {
            int teacherId = teacher.getMemberId();
            UserInfo teacherInfo = userInfoService.getUserInfo(teacherId);
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