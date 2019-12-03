package com.proky.booking.service.security;

import com.proky.booking.dto.SecureUserDto;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean isAnonymousUser() {
        return getAuthentication() instanceof AnonymousAuthenticationToken;
    }

    public String getUserRole() {

        UserDetails userDetails = (UserDetails) getAuthentication().getPrincipal();
        final GrantedAuthority next = userDetails.getAuthorities().iterator().next();
        return next.getAuthority();
    }

    public SecureUserDto getCurrentUser() {
        return (SecureUserDto) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
