package cn.ljlin233.resource.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.resource.entity.Ban;
import cn.ljlin233.resource.entity.BanResponse;
import cn.ljlin233.resource.service.BanService;

/**
 * BanController
 */
@Controller
@RequestMapping("/api")
public class BanController {

    private BanService banService;

    public BanController() {
        super();
    }

    @Autowired
    public BanController(BanService banService) {
        this.banService = banService;
    }

    @RequestMapping(value = "/res_ban", method = RequestMethod.GET)
    @ResponseBody
    public BanResponse getAllBanUser() {
        List<Ban> bans = banService.getAllBanUser();
        int count = banService.getAllBanUserCount();

        return new BanResponse(count, bans);
    }

    @RequestMapping(value = "/res_ban", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public BanResponse getBanUserByPage(@RequestParam int page) {
        List<Ban> bans = banService.getBanUserByPage(page, 10);
        int count = banService.getAllBanUserCount();

        return new BanResponse(count, bans);
    }

    @RequestMapping(value = "/res_ban", method = RequestMethod.POST)
    public void addBanUser(HttpServletRequest request) {
        int banId = Integer.valueOf(request.getParameter("banId"));
        int addId = Integer.valueOf(request.getParameter("addId"));

        banService.addBanUser(banId, addId);
    }

    @RequestMapping(value = "/res_ban", params = "id", method = RequestMethod.DELETE)
    public void deleteBanUser(@RequestParam int id) {
        banService.deleteBanUser(id);
    }

}