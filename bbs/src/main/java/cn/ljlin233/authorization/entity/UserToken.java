package cn.ljlin233.authorization.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserToken
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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