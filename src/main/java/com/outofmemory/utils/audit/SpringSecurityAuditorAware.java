package com.outofmemory.utils.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// todo check isAuthenticated?
@Slf4j
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    private final static String EMPTY = "EMPTY";

    public String getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(String.class::cast)
                .orElse(EMPTY);
    }
}
