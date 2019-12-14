package cn.ljlin233.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

/**
 * UserInfo
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@Builder
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "account")
    private String account;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "register_time")
    private String registerTime;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "active")
    private Integer active;

    @Column(name = "isban")
    private Integer isban;

}