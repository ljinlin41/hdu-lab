package cn.ljlin233.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.ljlin233.user.dao.UserAuthsDao;
import cn.ljlin233.user.dao.UserRoleDao;
import cn.ljlin233.user.dto.SecurityUserDto;
import cn.ljlin233.user.entity.UserAuths;
import cn.ljlin233.user.entity.UserRole;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/8 18:19
 */
@Service
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAuthsDao userAuthsDao;

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 根据用户账号从数据库查找具有的权限
     *
     * @param identifier 用户账号（学号，手机号，邮箱）
     */
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {

        UserAuths userAuths = userAuthsDao.getUserAuthsByidentifier(identifier);

        if (userAuths == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 获取用户对应的角色
        int id = userAuths.getId();
        List<UserRole> userRoleList = userRoleDao.getUserRoleByUserId(id);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        }

        SecurityUserDto securityUserDto = new SecurityUserDto(identifier, userAuths.getCredential(), authorities);

        return securityUserDto;
    }
}
