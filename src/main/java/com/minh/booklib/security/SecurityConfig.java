package com.minh.booklib.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**", "/register", "/user/**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/**").hasRole("USER")
                .and()
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/success")
//                        .permitAll())
//                .httpBasic(Customizer.withDefaults())
//                .addFilterBefore(new JWTValidatorFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(new JWTGeneratorFilter(), UsernamePasswordAuthenticationFilter.class)

        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
