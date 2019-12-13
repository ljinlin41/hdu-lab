package cn.ljlin233.introduce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.introduce.dto.AcceptApplyRequestDto;
import cn.ljlin233.introduce.dto.InsertApplyRequestDto;
import cn.ljlin233.introduce.dto.RejectApplyRequestDto;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.introduce.service.ApplyService;
import cn.ljlin233.util.Page;

/**
 * ApplyController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    /**
     * 根据申请id查看申请
     *
     * @param id 申请id
     * @return result
     */
    @PostAuthorize("hasAnyRole('teacher','admin', 'root') or authentication.principal.getUserId == returnObject.userId")
    @GetMapping(value = "/apply/pending", params = "id")
    public Apply getApplyById(@RequestParam int id) {

        return applyService.getApplyById(id);
    }

    /**
     * 根据用户id查看所有申请
     *
     * @param userId 用户Id
     * @return result
     */
    @PreAuthorize("hasAnyRole('teacher','admin', 'root') or authentication.principal.getUserId == #userId")
    @GetMapping(value = "/apply", params = "userId")
    public Page<Apply> getApplyByUserId(@RequestParam int userId) {

        return applyService.getApplyByUserId(userId);
    }

    /**
     * 添加用户申请
     * @param request request
     */
    @PreAuthorize("hasAnyRole('student', 'teacher','admin', 'root')")
    @PostMapping(value = "/apply")
    public void addApply(@RequestBody InsertApplyRequestDto request) {

        applyService.addApply(request);
    }

    /**
     * 获取用户未处理的申请，对于管理员
     * @param teacherId 管理员id
     * @return result
     */
    @PreAuthorize("hasAnyRole('teacher','admin', 'root')")
    @GetMapping(value = "/apply/pending", params = "teacherId")
    public Page<Apply> getPendingApply(@RequestParam int teacherId) {

        return applyService.getPendingApply(teacherId);
    }

    /**
     * 接受入部申请
     *
     * @param request request
     */
    @PreAuthorize("hasAnyRole('teacher','admin', 'root')")
    @PostMapping(value = "/apply/accept")
    public void acceptApply(@RequestBody AcceptApplyRequestDto request) {

        applyService.acceptApply(request);
    }

    /**
     * 拒绝入部申请
     *
     * @param request request
     */
    @PreAuthorize("hasAnyRole('teacher','admin', 'root')")
    @PostMapping(value = "/apply/reject")
    public void rejectApply(@RequestBody RejectApplyRequestDto request) {

        applyService.rejectApply(request);
    }
}