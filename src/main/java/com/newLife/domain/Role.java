package com.newLife.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    Clinic, Doctor, Patient;

    @Override
    public String getAuthority() {
        return name();
    }
}
