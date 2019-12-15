package cn.ljlin233.authorization.dao.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.ljlin233.authorization.dao.UserTokenDao;
import cn.ljlin233.authorization.entity.UserToken;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/7 18:33
 */
@Repository
public class UserTokenDaoImpl implements UserTokenDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addToken(String token, UserToken userToken) {
        redisTemplate.opsForValue().set(token, userToken, 30, TimeUnit.DAYS);
    }

    @Override
    public UserToken getUserToken(String token) {

        Object object = redisTemplate.opsForValue().get(token);

        return (UserToken) object;
    }


    @Override
    public void refreshToken(String token) {
        redisTemplate.expire(token, 30, TimeUnit.DAYS);
    }
}
