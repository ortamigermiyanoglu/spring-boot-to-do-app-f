package com.huawei.todo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author sumutella
 * @time 8:30 PM
 * @since 12/15/2019, Sun
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSourceLogin;

    public SecurityConfiguration(DataSource dataSourceLogin) {
        this.dataSourceLogin = dataSourceLogin;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2_console/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/confirm-account").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/**").hasRole("VERIFIED")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth-user")
                .defaultSuccessUrl("/test", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/my-login?logout").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").permitAll(); // after logout then redirect to login page;

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSourceLogin);
    }
}
