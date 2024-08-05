package com.dkay229.multisql.rest.server.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MultiUserDetails implements UserDetails {
    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return "password1";
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return "user1";
    }
}
