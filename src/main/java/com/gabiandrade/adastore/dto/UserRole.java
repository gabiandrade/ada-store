package com.gabiandrade.adastore.dto;

public enum UserRole {

    ADMIN("ADMIN_ROLE"),
    USER("USER_ROLE");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
