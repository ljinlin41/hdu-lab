package cn.ljlin233.introduce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ljlin233.introduce.dao.ApplyDao;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.introduce.entity.Department;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.ApplyService;
import cn.ljlin233.introduce.service.DepartmentService;
import cn.ljlin233.introduce.service.MemberService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.email.service.ActiveEmailService;
import cn.ljlin233.util.exception.entity.DataCheckedException;
import cn.ljlin233.util.exception.entity.SystemException;

/**
 * ApplyServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApplyServiceImpl implements ApplyService {

    private ApplyDao applyDao;

    private MemberService memberService;

    private UserInfoService userInfoService;

    private ActiveEmailService activeEmailService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DepartmentService departmentService;

    public ApplyServiceImpl() {}

    @Autowired
    public ApplyServiceImpl(ApplyDao applyDao, MemberService memberService, UserInfoService userInfoService,
        ActiveEmailService activeEmailService) {
        this.applyDao = applyDao;
        this.memberService = memberService;
        this.userInfoService = userInfoService;
        this.activeEmailService = activeEmailService;
    }

    @Override
    public void addApply(int userId, String username, String applyType, int departmentId) {

        if (userId == 0) {
            throw new DataCheckedException("userId is null");
        }
        if (departmentId == 0) {
            throw new DataCheckedException("departmentId is null");
        }
        if (username == null || username.length() == 0) {
            throw new DataCheckedException("username is null");
        }
        if (applyType == null || applyType.length() == 0) {
            throw new DataCheckedException("applyType is null");
        }

        Department department = departmentService.getDepartmentById(departmentId);
        if (department == null) {
            throw new SystemException("部门不存在", null);
        }

        Apply apply = new Apply();
        apply.setUserId(userId);
        apply.setDepartmentId(departmentId);
        apply.setUsername(username);
        apply.setApplyType(applyType);
        apply.setApplyStatus("待审核");

        try {
            applyDao.addApplys(apply);
        } catch (Exception e) {
            throw new SystemException("提交入部申请失败", e.getMessage());
        }

        List<Member> teachers = memberService.getTeacherMember(departmentId);

        if (teachers == null || teachers.size() == 0) {
            throw new SystemException("该部门没有教师，请联系管理员", null);
        } else {

            for (Member teacher : teachers) {
                int teacherId = teacher.getMemberId();
                UserInfo teacherInfo = userInfoService.getUserInfo(teacherId);
                String emailAdress = teacherInfo.getEmail();

                // ActiveEmail email = new ActiveEmail();
                // email.setSendFrom(MailConfig.username);
                // email.setSendTo(emailAdress);
                // email.setMessage("有人申请加入部门，请处理");
                // email.setTitle("实验室成员申请通知");
                //
                // try {
                //     activeEmailService.sendActiveEamil(email);
                // } catch (Exception e) {
                //     throw new SystemException("发送部门申请通知邮件失败", e.getMessage());
                // }

                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("1329540850@qq.com");
                message.setTo(emailAdress);
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

    @Override
    public Apply getApplyById(int id) {
        Apply apply = null;
        try {
            apply = applyDao.getApplyById(id);
        } catch (Exception e) {
            throw new SystemException("获取入部申请失败", e.getMessage());
        }
        return apply;
    }

    @Override
    public List<Apply> getApplyByUserId(int userId) {
        List<Apply> applys = null;
        try {
            applys = applyDao.getApplyByUserId(userId);
        } catch (Exception e) {
            throw new SystemException("获取入部申请失败", e.getMessage());
        }
        return applys;
    }

    // @Override
    // public List<Apply> getUnhandleApply(int userId, int page) {
    //     List<Apply> applies = null;
    //     Page<Apply> p = new Page<>();
    //     try {
    //         List<Member> teacher = memberService.getMembersByMemberId(userId);
    //         List<Integer> departments = new ArrayList<>();
    //         for(Member department : teacher) {
    //             departments.add(department.getDepartmentId());
    //         }
    //         p.setPageNo(page);
    //         applies = applyDao.getUnhandleApply(departments, p);
    //
    //     } catch (Exception e) {
    //         throw new SystemException("获取未处理入部申请失败", e.getMessage());
    //     }
    //     p.setResults(applies);
    //     return applies;
    // }

    @Override
    public void updateApply(int id, String applyStatus) {
        Apply apply = new Apply();
        apply.setId(id);
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
            memberService.addMember(applyInfo.getUserId(), applyInfo.getApplyType(), applyInfo.getUsername(),
                applyInfo.getDepartmentId());
        }

    }

    /**
     * 获取用户所有未处理的申请表
     *
     * @param userId 用户Id
     * @return 申请列表
     */
    @Override
    public List<Apply> getUnhandleApply(int userId) {
        return null;
    }

}