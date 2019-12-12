package cn.ljlin233.introduce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ljlin233.introduce.dto.DeleteMemberRequestDto;
import cn.ljlin233.introduce.dto.InsertMemberRequestDto;
import cn.ljlin233.introduce.entity.Member;
import cn.ljlin233.introduce.service.MemberService;
import cn.ljlin233.util.Page;

/**
 * MemberController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/members")
    public Page<Member> getAllMembers() {

        return memberService.getAllMembers();
    }

    @GetMapping(value = "/members", params = "page")
    public Page<Member> getMembersByPage(@RequestParam int page) {

        return memberService.getAllMembersByPage(page, 10);
    }

    @GetMapping(value = "/members", params = "id")
    public Member getMembersById(@RequestParam int id) {

        return memberService.getMemberById(id);
    }

    @GetMapping(value = "/members", params = {"departmentId", "page"})
    public Page<Member> getMembersByDepartment(@RequestParam int departmentId, @RequestParam int page) {

        return memberService.getMemberByDepartment(departmentId, page, 10);
    }

    @GetMapping(value = "/members", params = {"search", "page"})
    public Page<Member> searchMembers(@RequestParam String search, @RequestParam int page) {

        return memberService.searchMembersByName(search, page, 10);
    }

    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @PostMapping(value = "/members")
    public void addMember(@RequestBody InsertMemberRequestDto request) {

        memberService.addMember(request);
    }

    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @DeleteMapping(value = "/members")
    public void deleteMember(@RequestBody DeleteMemberRequestDto request) {

        memberService.deleteMember(request.getMemberId());
    }

}