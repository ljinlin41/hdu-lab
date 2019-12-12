package cn.ljlin233.introduce.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * Member
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "intro_member")
public class Member {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    /**
     * TODO 改为枚举类型
     */
    @Column(name = "member_type")
    private String memberType;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "department_id")
    private Integer departmentId;

}