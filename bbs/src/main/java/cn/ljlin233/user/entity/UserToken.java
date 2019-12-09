package cn.ljlin233.user.entity;

import java.util.List;

import lombok.Data;

/**
 * UserToken
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
public class UserToken {

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * token
     */
    private String token;

    /**
     * 用户角色
     */
    private List<String> role;

}