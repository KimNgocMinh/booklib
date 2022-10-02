package com.minh.booklib.security;

import com.minh.booklib.appconfig.SpringApplicationContext;
import com.minh.booklib.model.User;
import com.minh.booklib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTValidatorFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authCode = request.getHeader(AUTHORIZATION);
        if (null != authCode && authCode.startsWith(BEARER)) {
            String token = authCode.substring(BEARER.length());
            UserRepository userRepository = SpringApplicationContext.getBean(UserRepository.class);
            String author = JWTUtils.verifyJWT(token);
            if (null == author) {
                throw new BadCredentialsException("Bad credentials");
            }
            User user = userRepository.findById(author).orElseThrow(() -> new UsernameNotFoundException("Not found"));
            UserDetails userDetails = new BookLibUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(author, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }
}
