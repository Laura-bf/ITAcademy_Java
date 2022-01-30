package com.diceGame.mysql.model.DTO;

import java.util.List;

import com.diceGame.mysql.model.domain.Role;

public class SignUpRequest {
    private String userName;
    private String password;
    private List<Role> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
