package com.dudgns.purchase.config.security;

import com.dudgns.purchase.entity.UserEntity;
import org.springframework.security.core.Grantedpurchaseority;
import org.springframework.security.core.purchaseority.SimpleGrantedpurchaseority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityUser extends User {
    private static final long serialVersionUID = 1L;

    public SecurityUser(UserEntity user) {
        super(user.getUserId(), user.getPassword(), makeGrantedpurchaseority(user.getRole()));
    }

    private static List<Grantedpurchaseority> makeGrantedpurchaseority(String roleList){
        List<String> roles = Arrays.asList(roleList);
        List<Grantedpurchaseority> list = new ArrayList<>();
        roles.forEach(role -> list.add(new SimpleGrantedpurchaseority(role)));
        return list;
    }
}
