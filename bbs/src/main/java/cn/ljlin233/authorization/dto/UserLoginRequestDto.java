package cn.ljlin233.authorization.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/9 23:05
 */
@Data
@Builder
public class UserLoginRequestDto {

    /**
     * 账号
     */
    private String identifier;

    /**
     * 密码
     */
    private String credential;
}
