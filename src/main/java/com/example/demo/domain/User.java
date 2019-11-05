package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(base="ou=users",objectClasses = {
        "top",
        "inetOrgPerson", "person", "organizationalPerson",})
public class User {
    @JsonIgnore
    @Id
    private Name id;
    @JsonProperty("userId")
    private @Attribute(name = "uid") String userId;
    @JsonProperty("pwd")
    private @Attribute(name="userPassword") String pwd;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private @Attribute(name = "cn") String firstname;
    private @Attribute(name="sn") String lastname;

    private boolean access;
    public User() {
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User(String userId,String pwd) {
        this.userId = userId;
        this.pwd=pwd;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }


}
