package com.example.demo.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.model.TcRole;
import com.example.demo.model.TcUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullname;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public UserPrinciple(Long userId, String fullname, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(TcUser tcUser, List<TcRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (TcRole tcPerfil : roles) {
        	authorities.add(new SimpleGrantedAuthority("ROLE_" + tcPerfil.getCode()));
		}
        return new UserPrinciple(tcUser.getUserId(), tcUser.getFullname(), tcUser.getUsername(), tcUser.getEmail(), tcUser.getPassword(), authorities);
    }

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
        return username;
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

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}