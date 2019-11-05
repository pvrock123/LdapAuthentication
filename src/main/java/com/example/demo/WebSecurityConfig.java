package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();

        contextSource.setUrl(env.getRequiredProperty("spring.ldap.urls"));
        contextSource.setBase(
                env.getRequiredProperty("spring.ldap.base"));
        contextSource.setUserDn(
                env.getRequiredProperty("spring.ldap.username"));
        contextSource.setPassword(
                env.getRequiredProperty("spring.ldap.password"));

        return contextSource;
    }
    @Autowired
    private LdapContextSource ldapContextSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .contextSource(ldapContextSource)
                .userDnPatterns("uid={0},ou=users")
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }
}
