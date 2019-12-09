package cn.ljlin233.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import cn.ljlin233.user.dao.UserTokenDao;
import cn.ljlin233.user.entity.UserToken;

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
        redisTemplate.opsForValue().set(token, userToken);
    }

    @Override
    public UserToken getUserToken(String token) {

        Object object = redisTemplate.opsForValue().get(token);

        return (UserToken) object;
    }

    @Override
    public void deleteToken(String token) {

    }

    @Override
    public void refreshToken(String token) {

    }

    @Override
    public long getTokenTime(String token) {
        return 0;
    }

    @Override
    public boolean hasToken(String token) {
        return false;
    }
}
