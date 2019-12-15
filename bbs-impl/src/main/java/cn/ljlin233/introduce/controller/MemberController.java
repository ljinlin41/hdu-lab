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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * MemberController
 * @author lvjinlin42@foxmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags = "部门成员接口")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 获取所有成员信息
     *
     * @return result
     */
    @ApiOperation(value = "获取所有成员信息")
    @GetMapping(value = "/members")
    public Page<Member> getAllMembers() {

        return memberService.getAllMembers();
    }

    /**
     * 按页获取所有成员
     *
     * @param page 页数
     * @return result
     */
    @ApiOperation(value = "按页获取所有成员")
    @ApiImplicitParam(name = "page", value = "页数", dataType = "int")
    @GetMapping(value = "/memberPage", params = "page")
    public Page<Member> getMembersByPage(@RequestParam int page) {

        return memberService.getAllMembersByPage(page, 10);
    }

    /**
     * 根据成员Id获取成员
     *
     * @param id 成员id
     * @return result
     */
    @ApiOperation(value = "根据成员Id获取成员")
    @ApiImplicitParam(name = "id", value = "成员Id", dataType = "int")
    @GetMapping(value = "/memberId", params = "id")
    public Member getMembersById(@RequestParam int id) {

        return memberService.getMemberById(id);
    }

    /**
     * 按照部门获取成员
     *
     * @param departmentId 部门Id
     * @param page 页数
     * @return result
     */
    @ApiOperation(value = "按照部门获取成员")
    @ApiImplicitParams({@ApiImplicitParam(name = "departmentId", value = "部门Id", dataType = "int"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/departmentMemberPage", params = {"departmentId", "page"})
    public Page<Member> getMembersByDepartment(@RequestParam int departmentId, @RequestParam int page) {

        return memberService.getMemberByDepartment(departmentId, page, 10);
    }

    /**
     * 按照名称搜索成员
     *
     * @param search 搜索名称
     * @param page 页数
     * @return result
     */
    @ApiOperation(value = "按照名称搜索成员")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "搜索标题", dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页数", dataType = "int")})
    @GetMapping(value = "/searchMember", params = {"search", "page"})
    public Page<Member> searchMembers(@RequestParam String search, @RequestParam int page) {

        return memberService.searchMembersByName(search, page, 10);
    }

    /**
     * 添加一个成员
     *
     * @param request request
     */
    @ApiOperation(value = "添加一个成员")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "InsertMemberRequestDto")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @PostMapping(value = "/insertMember")
    public void addMember(@RequestBody InsertMemberRequestDto request) {

        memberService.addMember(request);
    }

    /**
     * 删除一个成员
     *
     * @param request request
     */
    @ApiOperation(value = "删除一个成员")
    @ApiImplicitParam(name = "request", value = "请求", dataType = "DeleteMemberRequestDto")
    @PreAuthorize("hasAnyRole('teacher', 'admin', 'root')")
    @DeleteMapping(value = "/deleteMember")
    public void deleteMember(@RequestBody DeleteMemberRequestDto request) {

        memberService.deleteMember(request.getMemberId());
    }

}