package cn.ljlin233.resource.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ljlin233.resource.dao.ResourceDao;
import cn.ljlin233.resource.entity.Resource;
import cn.ljlin233.resource.service.ResourceService;
import cn.ljlin233.user.entity.UserInfo;
import cn.ljlin233.user.service.UserInfoService;
import cn.ljlin233.util.exception.entity.SystemException;
import tk.mybatis.mapper.entity.Example;

/**
 * ResourceServiceImpl
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceDao resourceDao;

    private UserInfoService userInfoService;

    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ResourceServiceImpl() {
        super();
    }

    @Autowired
    public ResourceServiceImpl(ResourceDao resourceDao, UserInfoService userInfoService) {
        this.resourceDao = resourceDao;
        this.userInfoService = userInfoService;
    }

    @Override
    public List<Resource> getAllResources() {
        List<Resource> results = null;
        try {
            results = resourceDao.getAllResources();
        } catch (Exception e) {
            throw new SystemException("failed to get all resources", e.getMessage());
        }
        return results;
    }

    @Override
    public int getAllResourcesCount() {
        int count = 0;
        try {
            count = resourceDao.getAllResourcesCount();
        } catch (Exception e) {
            throw new SystemException("failed to get all resources count", e.getMessage());
        }
        return count;
    }

    @Override
    public List<Resource> getResourcesByPage(int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Resource> results = null;
        try {
            results = resourceDao.getResourcesByPage(start, pageNum);
        } catch (Exception e) {
            throw new SystemException("failed to get resources by page", e.getMessage());
        }
        return results;
    }

    @Override
    public List<Resource> searchResources(String keywords, int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Resource> results = null;
        try {
            results = resourceDao.searchResources(keywords, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("failed to search resources", e.getMessage());
        }
        return results;
    }

    @Override
    public int getSearchCount(String keywords) {
        int count = 0;
        try {
            count = resourceDao.getSearchCount(keywords);
        } catch (Exception e) {
            throw new SystemException("failed to get search count", e.getMessage());
        }
        return count;
    }

    @Override
    public List<Resource> getResourcesByType(String type, int page, int pageNum) {
        int start = (page - 1) * pageNum;
        List<Resource> results = null;
        try {
            results = resourceDao.getResourcesByType(type, start, pageNum);
        } catch (Exception e) {
            throw new SystemException("failed to get resources by type", e.getMessage());
        }
        return results;
    }

    @Override
    public int getTypeCount(String type) {
        int count = 0;
        try {
            count = resourceDao.getTypeCount(type);
        } catch (Exception e) {
            throw new SystemException("failed to get resources type count", e.getMessage());
        }

        return count;
    }

    @Override
    public Resource getResourceById(int id) {
        Resource result = null;
        try {
            result = resourceDao.getResourceById(id);
        } catch (Exception e) {
            throw new SystemException("failed to get resource", e.getMessage());
        }

        addVisitCount(id);

        return result;
    }

    @Override
    public void addVisitCount(int id) {
        try {
            resourceDao.addVisitCount(id);
        } catch (Exception e) {
            throw new SystemException("failed to update visit count", e.getMessage());
        }
    }

    @Override
    public void addDownloadCount(int id) {
        try {
            resourceDao.addDownloadCount(id);
        } catch (Exception e) {
            throw new SystemException("failed to update download count", e.getMessage());
        }
    }

    @Override
    public void addResource(String title, String content, String category, int upUserId, String url) {
        UserInfo up = userInfoService.getUserInfoByUserId(upUserId);
        Resource resource = Resource.builder().build();
        resource.setTitle(title);
        resource.setContent(content);
        resource.setCategory(category);
        resource.setUpUserId(upUserId);
        resource.setUpNickname(up.getNickname());
        resource.setUpDate(dateformat.format(new Date()));
        resource.setUrl(url);

        try {
            resourceDao.addResource(resource);
        } catch (Exception e) {
            throw new SystemException("failed to add resource to server", e.getMessage());
        }

    }

    @Override
    public void updateResource(int id, String title, String content) {
        Resource resource = Resource.builder().build();
        resource.setId(id);
        resource.setTitle(title);
        resource.setContent(content);

        try {
            resourceDao.updateResource(resource);
        } catch (Exception e) {
            throw new SystemException("failed to update resource", e.getMessage());
        }
    }

    @Override
    public void updateNickname(int userId, String nickname) {

        Resource resource = Resource.builder().upNickname(nickname).build();
        Example example = new Example(Resource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("up_userid", userId);

        resourceDao.updateResourceByExample(resource, example);
    }

    @Override
    public void deleteResource(int id) {
        try {
            resourceDao.deleteResource(id);
        } catch (Exception e) {
            throw new SystemException("failed to delete resource", e.getMessage());
        }
    }

}