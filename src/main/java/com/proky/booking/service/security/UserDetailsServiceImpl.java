package com.proky.booking.service.security;

import com.proky.booking.dto.SecureUserDto;
import com.proky.booking.persistence.entities.User;
import com.proky.booking.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Log4j2
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        final Set<GrantedAuthority> grantedAuthorities = buildUserAuthority(user);
        return buildUserForAuthentication(user, grantedAuthorities);
    }

    private Set<GrantedAuthority> buildUserAuthority(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return grantedAuthorities;
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, Set<GrantedAuthority> authorities) {
        final String userName = user.getFirstName().concat(" ").concat(user.getLastName());
        String password = user.getPassword();
        final SecureUserDto secureUserDto = new SecureUserDto(userName, password, true, true, true, true, authorities);
        secureUserDto.setUserId(user.getId());
        return secureUserDto;
    }
}
