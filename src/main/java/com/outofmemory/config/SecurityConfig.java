package com.outofmemory.config;

import com.outofmemory.config.firebase.FirebaseAuthenticationProvider;
import com.outofmemory.config.firebase.FirebaseFilter;
import com.outofmemory.service.FirebaseService;
import com.outofmemory.service.UserService;
import com.outofmemory.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

    private UserDetailsService userService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        return new FirebaseAuthenticationProvider(userService);
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Configuration
    protected class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors()
                    .and()
                    .httpBasic().disable()
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(openEndpoints()).permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .addFilterAt(tokenAuthFilter(), UsernamePasswordAuthenticationFilter.class);
        }


        private String[] openEndpoints() {
            return new String[]{
                    "/alive",
                    "/token",
                    "/user/login",
                    "/user/register",
                    "/api-docs/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/swagger-ui.html",
                    "/v2/api-docs",
            };
        }
    }

    private FirebaseService firebaseService;

    private FirebaseFilter tokenAuthFilter() {
        return new FirebaseFilter(firebaseService);
    }

    @Autowired
    public void setFirebaseService(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Autowired
    @Qualifier(value = UserServiceImpl.NAME)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

