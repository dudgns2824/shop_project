package com.dudgns.backendauth.config.security;

import com.dudgns.backendauth.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;

    public SecurityUser(UserEntity user) {
        super(user.getUserId(), user.getPassword(), makeGrantedAuthority(user.getRole()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(String roleList){
        List<String> roles = Arrays.asList(roleList);
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedAuthority(role)));
        return list;
    }
}
