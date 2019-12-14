package cn.ljlin233.resource.dao;

import java.util.List;

import cn.ljlin233.resource.entity.Resource;
import tk.mybatis.mapper.entity.Example;

/**
 * ResourceDao
 *
 * @author lvjinlin42@foxmail.com
 */
public interface ResourceDao {

    /**
     * 获取所有资源
     *
     * @return 资源列表
     */
    List<Resource> getAllResources();

    /**
     * 获取资源总数
     *
     * @return 资源总数
     */
    int getAllResourcesCount();

    /**
     * 按页获取资源
     *
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 资源列表
     */
    List<Resource> getResourcesByPage(int pageNum, int pageSize);

    /**
     * 按标题搜索资源
     *
     * @param keywords 搜索标题
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 资源列表
     */
    List<Resource> searchResources(String keywords, int pageNum, int pageSize);

    /**
     * 按标题搜索资源结果数
     *
     * @param keywords 搜索标题
     * @return 搜索结果数
     */
    int getSearchCount(String keywords);

    /**
     * 按类型获取资源
     *
     * @param type 资源类型
     * @param pageNum 第N页
     * @param pageSize 每页大小
     * @return 资源列表
     */
    List<Resource> getResourcesByType(String type, int pageNum, int pageSize);

    /**
     * 按类型获取资源数量
     *
     * @param type 资源类型
     * @return 资源数量
     */
    int getTypeCount(String type);

    /**
     * 按资源Id搜索资源
     *
     * @param id 资源Id
     * @return 资源
     */
    Resource getResourceById(int id);

    /**
     * 增加一个资源的访问数
     *
     * @param id 资源Id
     */
    void addVisitCount(int id);

    /**
     * 增加一个资源的下载数
     *
     * @param id 资源Id
     */
    void addDownloadCount(int id);

    /**
     * 添加一个资源
     *
     * @param resource 资源
     */
    void addResource(Resource resource);

    /**
     * 更新一个资源
     *
     * @param resource 资源
     */
    void updateResource(Resource resource);

    void updateResourceByExample(Resource resource, Example example);
    /**
     * 删除一个资源
     *
     * @param id 资源Id
     */
    void deleteResource(int id);

}