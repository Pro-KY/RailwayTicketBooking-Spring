package com.proky.booking.service.security;

public interface SecurityService {
    String findLoggedInUserName();
    void autoLogin(String email, String password);
}
