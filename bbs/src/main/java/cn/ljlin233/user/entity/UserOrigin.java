package cn.ljlin233.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserOrigin
 *
 * @author lvjinlin42@foxmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_origin")
public class UserOrigin {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "account")
    private String account;
}