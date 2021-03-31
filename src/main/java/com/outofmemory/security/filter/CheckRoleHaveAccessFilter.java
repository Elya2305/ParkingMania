package com.outofmemory.security.filter;

import com.outofmemory.entity.User;
import com.outofmemory.exception.UserHaveNoAccessToResourceException;
import com.outofmemory.utils.access_check.RolesHaveAccess;
import com.outofmemory.utils.security.AuthGateway;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CheckRoleHaveAccessFilter extends GenericFilterBean {
    private List<RoleAccess> config;
    private final HandlerExceptionResolver exceptionResolver;

    public CheckRoleHaveAccessFilter(HandlerExceptionResolver exceptionResolver, String... scanPackage) {
        this.exceptionResolver = exceptionResolver;
        init(scanPackage);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        try {
            this.doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, chain);
        } catch (Exception exc) {
            exceptionResolver.resolveException((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, null, exc);
        }
    }

    private void doFilter(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        String requestUrl = servletRequest.getRequestURI();
        String requestMethod = servletRequest.getMethod();

        if (requestUrl.equals("/error")) {
            chain.doFilter(servletRequest, servletResponse);
        }

        doCheckAccess(requestUrl, requestMethod);
        chain.doFilter(servletRequest, servletResponse);
    }

    private void doCheckAccess(String requestUrl, String requestMethod) {
        checkUserNotBlocked();
        checkUserHaveAccess(requestUrl, requestMethod);
    }

    private void init(String... scanPackages) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(scanPackages)
                .addScanners(new TypeAnnotationsScanner(), new MethodAnnotationsScanner()));

        config = reflections.getMethodsAnnotatedWith(RolesHaveAccess.class)
                .stream().map(this::toRoleAccess).collect(Collectors.toList());

    }

    private void checkUserHaveAccess(String requestUrl, String requestMethod) {
        config.stream().filter(o -> o.getRequestUrl().equals(requestUrl)
                && o.getRequestMethod().equals(requestMethod))
                .findAny().ifPresent(roleAccess -> {
            User.Role current = AuthGateway.currentRole();
            if (!roleAccess.getRolesHaveAccess().contains(current)) {
                throw new UserHaveNoAccessToResourceException("User with role " + current + " doesn't have access to resource");
            }
        });
    }

    private void checkUserNotBlocked() {
        User.Status current = AuthGateway.currentStatus();
        if (User.Status.BLOCKED == current) {
            throw new UserHaveNoAccessToResourceException("User " + AuthGateway.currentUserId() + " is blocked");
        }
    }

    private RoleAccess toRoleAccess(Method method) {
        RoleAccess roleAccess = new RoleAccess();
        roleAccess.setRequestMethod(requestMethod(method));
        roleAccess.setRequestUrl(requestUrl(method));
        roleAccess.setRolesHaveAccess(roles(method));
        return roleAccess;
    }

    private List<User.Role> roles(Method method) {
        return Arrays.asList(method.getDeclaredAnnotation(RolesHaveAccess.class).restrict());
    }

    private String requestMethod(Method method) {
        Annotation mappingAnnotation = findMappingAnnotation(method);
        RequestMethod requestMethod = getMappingAnnotationMethod(mappingAnnotation);
        return requestMethod.name();
    }

    private String requestUrl(Method method) {
        String classUrl = method.getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
        String methodUrl = getMethodUrl(findMappingAnnotation(method));
        return classUrl + methodUrl;
    }

    private RequestMethod getMappingAnnotationMethod(Annotation annotation) {
        return Arrays.stream(annotation.annotationType().getDeclaredAnnotation(RequestMapping.class).method())
                .findFirst().orElseThrow(() -> new RuntimeException("Can't find request method"));
    }

    private Annotation findMappingAnnotation(Method method) {
        Annotation annotation = null;
        for (HttpMethod httpMethod : HttpMethod.values()) {
            Annotation temp = method.getAnnotation(httpMethod.getAnnotationClass());
            if (Objects.nonNull(temp)) {
                annotation = temp;
                break;
            }
        }
        return annotation;
    }

    private String getMethodUrl(Annotation annotation) {
        if (annotation.annotationType().equals(GetMapping.class)) {
            return oneValue(((GetMapping) annotation).value());
        }
        if (annotation.annotationType().equals(PostMapping.class)) {
            return oneValue(((PostMapping) annotation).value());
        }
        if (annotation.annotationType().equals(PutMapping.class)) {
            return oneValue(((PutMapping) annotation).value());
        }
        if (annotation.annotationType().equals(DeleteMapping.class)) {
            return oneValue(((DeleteMapping) annotation).value());
        }
        if (annotation.annotationType().equals(PatchMapping.class)) {
            return oneValue(((PatchMapping) annotation).value());
        }
        return "";
    }

    private String oneValue(String[] values) {
        return values.length == 0 ? "" : values[0];
    }

    @Data
    @EqualsAndHashCode(exclude = "rolesHaveAccess")
    private static class RoleAccess {
        private List<User.Role> rolesHaveAccess;
        private String requestMethod;
        private String requestUrl;
    }

    private enum HttpMethod {
        GET(GetMapping.class),
        POST(PostMapping.class),
        PUT(PutMapping.class),
        DELETE(DeleteMapping.class),
        PATCH(PatchMapping.class),
        HEAD(null),
        OPTIONS(null);

        private final Class<? extends Annotation> annotationClass;

        HttpMethod(Class<? extends Annotation> annotationClass) {
            this.annotationClass = annotationClass;
        }

        public Class<? extends Annotation> getAnnotationClass() {
            return annotationClass;
        }
    }
}
