package com.minh.booklib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BookLibAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;
    private final UserDetailsService userService;

    @Autowired
    public BookLibAuthenticationProvider(PasswordEncoder encoder, UserDetailsService userService) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails u = userService.loadUserByUsername(username);
        encoder.matches(password,u.getPassword());

        return new UsernamePasswordAuthenticationToken(username, null, u.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
