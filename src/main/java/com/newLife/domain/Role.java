package com.newLife.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLINIC, DOCTOR, PATIENT, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
