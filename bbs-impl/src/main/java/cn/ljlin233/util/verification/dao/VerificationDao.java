package cn.ljlin233.util.verification.dao;

/**
 * VerificationDao
 */
public interface VerificationDao {

    void addVerification(String verId, String verCode);

    String getVerificationCode(String verId);

}