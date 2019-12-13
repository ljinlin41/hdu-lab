package cn.ljlin233.introduce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.introduce.dto.InsertApplyRequestDto;
import cn.ljlin233.introduce.entity.Apply;
import cn.ljlin233.introduce.service.ApplyService;
import cn.ljlin233.util.Page;

/**
 * ApplyController TODO 流程未完善
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @PreAuthorize("hasAnyRole('teacher','admin', 'root') or authentication.principal.getUserId == #userId")
    @GetMapping(value = "/apply", params = "userId")
    public Page<Apply> getApplyByUserId(@RequestParam int userId) {

        return applyService.getApplyByUserId(userId);
    }

    @PreAuthorize("hasAnyRole('student', 'teacher','admin', 'root')")
    @PostMapping(value = "/apply")
    public void addApply(@RequestBody InsertApplyRequestDto request) {

        applyService.addApply(request);
    }

    @PreAuthorize("hasAnyRole('teacher','admin', 'root')")
    @GetMapping(value = "/apply/pending", params = "teacherId")
    public Page<Apply> getPendingApply(@RequestParam int teacherId) {

        return applyService.getPendingApply(teacherId);
    }

}