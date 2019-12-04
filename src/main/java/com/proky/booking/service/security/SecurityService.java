package com.proky.booking.service.security;

import com.proky.booking.dto.SecureUserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SecurityService {

    public boolean isNotAnonymousUser() {
        return !(getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public String getUserRole() {
        String role;

        try {
            UserDetails userDetails = (UserDetails) getAuthentication().getPrincipal();
            final GrantedAuthority next = userDetails.getAuthorities().iterator().next();
            role = next.getAuthority();
        } catch (ClassCastException e) {
            role = (String) getAuthentication().getPrincipal();
        }

        return role;
    }

    public SecureUserDto getCurrentUser() {
        return (SecureUserDto) getAuthentication().getPrincipal();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
