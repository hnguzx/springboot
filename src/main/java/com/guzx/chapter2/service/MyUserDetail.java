package com.guzx.chapter2.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyUserDetail implements UserDetails {

    private String userName;
    private String password;
    private Boolean active;
    private List<GrantedAuthority> authorities;

//    User id,

    public MyUserDetail(){}

    public MyUserDetail(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
//        this.active = user.getActive();
//        this.authorities = Arrays.steam(user.getRoles().split(",")).
//        map(SimpleGrantedAuthority::new).
//        collect(Collectors.toList());
    }

//    public MyUserDetail(String userName) {
//        this.userName = userName;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否已激活
    @Override
    public boolean isEnabled() {
        return true;
    }
}
