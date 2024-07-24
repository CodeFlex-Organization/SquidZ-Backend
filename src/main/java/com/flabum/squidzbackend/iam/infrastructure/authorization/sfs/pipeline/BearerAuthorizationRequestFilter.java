package com.flabum.squidzbackend.iam.infrastructure.authorization.sfs.pipeline;

import com.flabum.squidzbackend.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.BearerTokenService;
import com.flabum.squidzbackend.iam.infrastructure.token.jwts.services.TokenServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);

    private final BearerTokenService tokenService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService  userDetailsService;

    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = "";
        try {
            var userAgent = request.getHeader(HttpHeaders.USER_AGENT);
            if (userAgent != null && !userAgent.contains("Android") && !userAgent.contains("iPhone") && !userAgent.contains("iPad")) {
                 token = TokenServiceImpl.getJwtFromCookie(request);
            }
            else {token = tokenService.getBearerTokenFrom(request);}

            if (token != null && token != "" && tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails,request));
            } else {
                LOGGER.info("Token is not valid");
            }

        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

}











