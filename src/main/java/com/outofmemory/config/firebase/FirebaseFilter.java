package com.outofmemory.config.firebase;

import com.cloudinary.utils.StringUtils;
import com.outofmemory.exception.auth.FirebaseTokenInvalidException;
import com.outofmemory.service.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class FirebaseFilter extends OncePerRequestFilter {
    private final static String HEADER_NAME = "X-Authorization-Firebase";
    private final FirebaseService firebaseService;

    // todo fix recursion
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Start firebase filtering");
        String xAuth = request.getHeader(HEADER_NAME);
        if (!StringUtils.isBlank(xAuth)) {
            try {
                FirebaseTokenHolder holder = firebaseService.parseToken(xAuth);
                String userName = holder.getUid();

                Authentication auth = new FirebaseAuthenticationToken(userName, holder);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (FirebaseTokenInvalidException e) {
                log.error("Ann error occurred", e);
                throw new SecurityException(e);
            }
            filterChain.doFilter(request, response);
            log.info("End firebase filtering");
        }
    }
}
