package com.owt.api.config.security;

import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.owt.api.config.security.handler.ForbiddenErrorHandler;
import com.owt.api.config.security.handler.UnauthorizedErrorHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnWebApplication
@RequiredArgsConstructor
public class WebSecurityConfig
{
    private final UnauthorizedErrorHandler authenticationErrorHandler;
    private final ForbiddenErrorHandler forbiddenErrorHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        // Configure security settings ===========================================================================

        return httpSecurity
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(forbiddenErrorHandler)
                .and()

                .headers()
                .frameOptions()
                .sameOrigin()
                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .anyRequest().authenticated()
                .and()

                .httpBasic()
                .and()

                .build();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsManager()
    {
        UserDetails user1 = User.withUsername("user1@mail.com")
                                .password(passwordEncoder().encode("password"))
                                .roles("ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(Set.of(user1));
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(8);
    }
}