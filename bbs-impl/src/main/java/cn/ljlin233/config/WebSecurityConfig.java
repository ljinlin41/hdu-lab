package cn.ljlin233.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.ljlin233.user.common.MyAccessDeniedHandler;
import cn.ljlin233.user.common.MyAuthenticationEntryPoint;
import cn.ljlin233.user.common.MyPasswordEncoder;
import cn.ljlin233.user.common.TokenFilter;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/8 16:52
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenFilter tokenFilter;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * UserDetailsService存在多个实例，使用@Resource选择指定bean
     */
    @Resource(name = "myUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
            .and()
            .csrf()
            .disable()
            //基于token，所以不需要session
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest()
            .permitAll();

        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(
            myAccessDeniedHandler);
    }

    /**
     * 读取数据库中的用户
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
    }
}
