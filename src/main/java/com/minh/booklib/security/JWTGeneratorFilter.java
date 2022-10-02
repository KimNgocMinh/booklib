package com.minh.booklib.security;

import com.minh.booklib.model.User;
import com.minh.booklib.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTGeneratorFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (null != authentication) {
            String username = authentication.getPrincipal().toString();

            String token = JWTUtils.generateToken(username);

            if (null == token) {
                response.getWriter().write("Can't create token. Please try again later");
                return;
            }
            response.setHeader("Bearer", token);
        }

        filterChain.doFilter(request, response);
    }
}
