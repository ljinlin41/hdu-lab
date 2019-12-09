package cn.ljlin233.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * UserAuths
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "user_auths")
public class UserAuths {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * TODO 改为枚举类型
     */
    @Column(name = "identity_type")
    private String identityType;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "credential")
    private String credential;

}