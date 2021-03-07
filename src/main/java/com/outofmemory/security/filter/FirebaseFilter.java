package com.outofmemory.security.filter;

import com.cloudinary.utils.StringUtils;
import com.outofmemory.security.token.FirebaseTokenHolder;
import com.outofmemory.exception.auth.FirebaseTokenInvalidException;
import com.outofmemory.service.FirebaseService;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// todo add bearer
@Slf4j
@AllArgsConstructor
public class FirebaseFilter extends OncePerRequestFilter {
    private final static String HEADER_NAME = "X-Authorization-Firebase";
    private final static String TOKEN_URL = "/refresh-token";
    private final FirebaseService firebaseService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Start firebase filtering");
        String xAuth = request.getHeader(HEADER_NAME);
        if (!StringUtils.isBlank(xAuth) && !request.getRequestURL().toString().contains(TOKEN_URL)) {
            try {
                FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);
                String userName = holder.getUid();

                Authentication auth = new UsernamePasswordAuthenticationToken(userName, holder);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (FirebaseTokenInvalidException e) {
                log.error("An error occurred", e);
                throw new SecurityException(e);
            }
        }
        log.info("End firebase filtering");
        filterChain.doFilter(request, response);
    }
}
