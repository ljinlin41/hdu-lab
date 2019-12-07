package cn.ljlin233.util.verification.service;

import cn.ljlin233.util.verification.entity.Verification;

/**
 * VerificationService
 */
public interface VerificationService {

    Verification getVerification();

    boolean checkVerification(String verId, String verCode);

}