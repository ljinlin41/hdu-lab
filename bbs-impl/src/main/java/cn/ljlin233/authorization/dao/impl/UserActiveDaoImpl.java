package cn.ljlin233.authorization.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.ljlin233.authorization.dao.UserActiveDao;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/14 22:06
 */
@Repository
public class UserActiveDaoImpl implements UserActiveDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void storeActive(String active, int userId) {
        redisTemplate.opsForValue().set(active, userId);
    }

    @Override
    public int getUserIdByActive(String active) {
        return (int) redisTemplate.opsForValue().get(active);
    }
}
