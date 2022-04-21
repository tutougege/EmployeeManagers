package com.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//开启WebSecurity模式
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //定制请求的授权规则
        //首页所有人可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/emp/**").hasRole("admin")
                .antMatchers("/report/toCheck").hasRole("admin");
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password");
               /* .loginPage("/tologin")
                .loginProcessingUrl("/login");*///登录表单的请求
        //开启自动配置的注销功能
        //logout请求
        //注销成功来到首页
        //http.logout().logoutSuccessUrl("/tologin");
        //http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout
        //定制记住我的参数
       // http.rememberMe().rememberMeParameter("remember");
    }
    //定义认证规则

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //内存中定义，也可以到数据库中去拿
        //现在无法登陆，因为密码没有加密
        //可以设置内存指定的登录的账号密码,指定角色
        //不加.passwordEncoder(new MyPasswordEncoder())
        //就不是以明文的方式进行匹配，会报错
        //.passwordEncoder(new MyPasswordEncoder())。
        //这样，页面提交时候，密码以明文的方式进行匹配。
        auth.inMemoryAuthentication()
                .passwordEncoder(new MyPasswordEncoder())
                .withUser("manager").password("123456").roles("manager")
                .and()
                .withUser("admin").password("123456").roles("admin");
    }
    //权限控制和注销

}
