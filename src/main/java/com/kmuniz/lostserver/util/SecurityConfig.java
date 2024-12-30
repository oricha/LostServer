package com.kmuniz.lostserver.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenValidationFilter tokenValidationFilter) throws Exception {

        http
                // Enable CSRF protection using a cookie-based token repository
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                // Add the custom token validation filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(tokenValidationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        // Define role-based access control
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN-only paths
                        .requestMatchers("/login", "/register").permitAll() // Publicly accessible paths
                        .anyRequest().authenticated() // All other requests require authentication
                )
                // Configure the login page and default success URL
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/items", true)
                        .permitAll()
                )
                // Configure the logout behavior
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // For encoding passwords
    }
}