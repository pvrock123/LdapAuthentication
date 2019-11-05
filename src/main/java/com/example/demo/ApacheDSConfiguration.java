package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;

@Configuration
@EnableLdapRepositories(basePackages = "com.example.demo.repositories")
public class ApacheDSConfiguration {

}
