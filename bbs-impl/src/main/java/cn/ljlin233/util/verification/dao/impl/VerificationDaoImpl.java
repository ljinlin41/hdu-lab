package cn.ljlin233.util.verification.dao.impl;

import org.springframework.stereotype.Repository;

import cn.ljlin233.util.verification.dao.VerificationDao;

/**
 * VerificationDaoImpl
 */
@Repository
public class VerificationDaoImpl implements VerificationDao {

    @Override
    public void addVerification(String verId, String verCode) {
        //stringRedisTemplate.opsForValue().set(verId, verCode, 2, TimeUnit.MINUTES);
    }

    @Override
    public String getVerificationCode(String verId) {
        return null;
    }

}