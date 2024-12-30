package com.kmuniz.lostserver.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Extract token from Authorization header
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix

            // Validate the token (you should replace this with your actual token validation logic)
            if (isValidToken(token)) {
                // Authenticate the user
                PreAuthenticatedAuthenticationToken authentication =
                        new PreAuthenticatedAuthenticationToken("user", null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidToken(String token) {
        // Replace with actual token validation logic
        return "valid-token".equals(token);
    }
}
