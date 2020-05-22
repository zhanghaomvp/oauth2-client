package com.example.cetcxl.client.common.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Set;

public class ClientUser implements OAuth2User {
    private String username;
    private boolean enabled;
    private Set<SimpleGrantedAuthority> authorities;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Set<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return username;
    }
}
