package cn.ljlin233.authorization.dao;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/14 22:02
 */
public interface UserActiveDao {

    void storeActive(String active, int userId);

    int getUserIdByActive(String active);
}
