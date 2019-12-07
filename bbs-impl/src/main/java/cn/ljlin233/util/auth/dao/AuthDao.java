package cn.ljlin233.util.auth.dao;

/**
 * AuthDao
 */
public interface AuthDao {

    Integer getOwnerId(String tableName, String column, Integer id);

}