package com.proky.booking.util.constans.enums;

public enum UserRoleEnum {
    USER("passenger"), GUEST("guest"), ADMIN("administrator");

    public final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
