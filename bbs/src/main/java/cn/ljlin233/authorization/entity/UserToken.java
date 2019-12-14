package cn.ljlin233.authorization.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * UserToken
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
public class UserToken {

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * nickName
     */
    private String nickName;

    /**
     * 用户角色
     */
    private List<String> role;

    /**
     * token
     */
    private String token;

}