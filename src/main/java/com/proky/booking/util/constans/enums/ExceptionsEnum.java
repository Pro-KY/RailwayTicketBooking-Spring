package com.proky.booking.util.constans.enums;

public enum ExceptionsEnum {
    SERVICE_EXCEPTION("ServiceException");

    public final String name;

    ExceptionsEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
