package com.diceGame.mysql.model.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_PLAYER;

    public String getAuthority() {
        return name();
    }

}