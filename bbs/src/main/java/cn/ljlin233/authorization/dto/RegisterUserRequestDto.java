package cn.ljlin233.authorization.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/13 20:29
 */
@Data
@Builder
public class RegisterUserRequestDto {

    private String account;

    private String password;

    private String role;

    private String email;

    private String verificationId;

    private String verificationCode;
}
