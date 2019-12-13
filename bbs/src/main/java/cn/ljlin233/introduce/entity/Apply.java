package cn.ljlin233.introduce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Apply
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "intro_apply")
public class Apply {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    /**
     * TODO 改为枚举类型
     */
    @Column(name = "apply_type")
    private String applyType;

    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * TODO 改为枚举类型
     */
    @Column(name = "apply_status")
    private String applyStatus;

}