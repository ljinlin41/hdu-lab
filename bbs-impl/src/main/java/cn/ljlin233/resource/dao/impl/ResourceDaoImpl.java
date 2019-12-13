package cn.ljlin233.resource.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.ljlin233.resource.dao.ResourceDao;
import cn.ljlin233.resource.entity.Resource;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 17:00
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {
    @Override
    public List<Resource> getAllResources() {
        return null;
    }

    @Override
    public int getAllResourcesCount() {
        return 0;
    }

    @Override
    public List<Resource> getResourcesByPage(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<Resource> searchResources(String keywords, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public int getSearchCount(String keywords) {
        return 0;
    }

    @Override
    public List<Resource> getResourcesByType(String type, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public int getTypeCount(String type) {
        return 0;
    }

    @Override
    public Resource getResourceById(int id) {
        return null;
    }

    @Override
    public void addVisitCount(int id) {

    }

    @Override
    public void addDownloadCount(int id) {

    }

    @Override
    public void addResource(Resource resource) {

    }

    @Override
    public void updateResource(Resource resource) {

    }

    @Override
    public void deleteResource(int id) {

    }
}
