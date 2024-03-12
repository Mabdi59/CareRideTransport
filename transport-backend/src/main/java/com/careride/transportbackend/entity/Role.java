package com.careride.transportbackend.entity;

public enum Role {
    USER,
    ADMIN;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }
    public String getName() {
        return this.name();
    }
}
