package com.outofmemory.security.filter;

import com.cloudinary.utils.StringUtils;
import com.outofmemory.dto.user.UserDto;
import com.outofmemory.security.token.FirebaseTokenHolder;
import com.outofmemory.service.FirebaseService;
import com.outofmemory.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// todo add bearer
// todo remove set context (in provider?)
@Slf4j
@AllArgsConstructor
public class FirebaseFilter extends OncePerRequestFilter {
    private final static String HEADER_NAME = "X-Authorization-Firebase";
    private final static String TOKEN_URL = "/refresh-token";
    private final FirebaseService firebaseService;
    private final UserService userService;
    private final HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            doFilter(request, response, filterChain);
        } catch (Exception exc) {
            exceptionResolver.resolveException(request, response, null, exc);
        }
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("Start firebase filtering");
        String xAuth = request.getHeader(HEADER_NAME);
        if (!StringUtils.isBlank(xAuth) && !request.getRequestURL().toString().contains(TOKEN_URL)) {
            FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);
            String userName = holder.getUid();

            UserDto user = userService.getByLocaleId(userName);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, holder);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        log.info("End firebase filtering");
        filterChain.doFilter(request, response);
    }
}
