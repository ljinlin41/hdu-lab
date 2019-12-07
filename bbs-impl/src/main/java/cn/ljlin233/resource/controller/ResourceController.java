package cn.ljlin233.resource.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ljlin233.resource.entity.Resource;
import cn.ljlin233.resource.entity.ResourceResponse;
import cn.ljlin233.resource.service.ResourceService;

/**
 * ResourceController
 */
@Controller
@RequestMapping("/api")
public class ResourceController {

    private ResourceService resourceService;

    public ResourceController() {
        super();
    }

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    @ResponseBody
    public ResourceResponse getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        int count = resourceService.getAllResourcesCount();

        return new ResourceResponse(count, resources);
    }

    @RequestMapping(value = "/resources", params = "page", method = RequestMethod.GET)
    @ResponseBody
    public ResourceResponse getResourcesByPage(@RequestParam int page) {
        List<Resource> resources = resourceService.getResourcesByPage(page, 10);
        int count = resourceService.getAllResourcesCount();

        return new ResourceResponse(count, resources);
    }

    @RequestMapping(value = "/resources", params = {"search", "page"}, method = RequestMethod.GET)
    @ResponseBody
    public ResourceResponse searchResources(@RequestParam String search, @RequestParam int page) {

        List<Resource> resources = resourceService.searchResources(search, page, 10);
        int count = resourceService.getSearchCount(search);

        return new ResourceResponse(count, resources);
    }

    @RequestMapping(value = "/resources", params = {"type", "page"}, method = RequestMethod.GET)
    @ResponseBody
    public ResourceResponse getResourcesByType(@RequestParam String type, @RequestParam int page) {

        List<Resource> resources = resourceService.getResourcesByType(type, page, 10);
        int count = resourceService.getTypeCount(type);

        return new ResourceResponse(count, resources);
    }

    @RequestMapping(value = "/resources", params = "id", method = RequestMethod.GET)
    @ResponseBody
    public Resource getResourceById(@RequestParam int id) {
        Resource resource = resourceService.getResourceById(id);
        return resource;
    }

    @RequestMapping(value = "/resources", method = RequestMethod.POST)
    public void addResource(HttpServletRequest request) {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String category = request.getParameter("category");
        int upUserId = Integer.valueOf(request.getParameter("upUserId"));
        String url = request.getParameter("url");

        resourceService.addResource(title, content, category, upUserId, url);
    }

    @RequestMapping(value = "/resources", params = "id", method = RequestMethod.PUT)
    public void updateResource(@RequestParam int id, HttpServletRequest request) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        resourceService.updateResource(id, title, content);
    }

    @RequestMapping(value = "/resources", params = "id", method = RequestMethod.DELETE)
    public void deleteResource(@RequestParam int id) {

        resourceService.deleteResource(id);
    }

}