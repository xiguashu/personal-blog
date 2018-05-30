package com.blog.myblog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()   //任何人都可以访问
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/music_manage").access("hasRole('ROLE_chuci')")
                .antMatchers("/").permitAll()
                .and()
                 .formLogin().loginPage("/login").usernameParameter("username")
                    .passwordParameter("password").and()
                 .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("x")
            .password("x")
            .authorities("ROLE_ADMIN");

        auth
                .inMemoryAuthentication()
                .withUser("xx")
                .password("xx")
                .authorities("ROLE_chuci");


    }
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }}
    /*
@Bean
@Override
public UserDetailsService userDetailsService() {
    UserDetails user =
            User.withDefaultPasswordEncoder()
                    .username("root")
                    .password("root")
                    .roles("ADMIN")
                    .build();

    return new InMemoryUserDetailsManager(user);
}


}*/