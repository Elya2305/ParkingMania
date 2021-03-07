package com.outofmemory.utils.audit;

import com.outofmemory.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = SpringSecurityAuditorAware.NAME)
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    private final static String EMPTY = "EMPTY";
    public final static String NAME = "AuditorProvider";

    public String getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getLocalId)
                .orElse(EMPTY);
    }
}
