package com.annieryannel.userssecure.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ROLE_USER,
    ROLE_ACTIVE;

    @Override
    public String getAuthority() {
        return name();
    }
}
