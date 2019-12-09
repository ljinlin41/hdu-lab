package cn.ljlin233.user.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/8 18:12
 */
public class SecurityUserDto extends User {

    public SecurityUserDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
