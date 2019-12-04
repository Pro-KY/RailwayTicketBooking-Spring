package com.proky.booking.util.constans;

public enum RoleEnum {
    USER("user"), ADMIN("administrator"), ANONYMOUS("ROLE_ANONYMOUS");

    public final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
