package cn.ljlin233.authorization.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * UserRole
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * TODO 改为枚举类型
     */
    @Column(name = "role")
    private String role;

}