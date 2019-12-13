package cn.ljlin233.user.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/11 22:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyUserDetail extends User {

    private Integer userId;

    public MyUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
