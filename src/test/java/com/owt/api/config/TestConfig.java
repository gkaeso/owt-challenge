package com.owt.api.config;

import java.time.Clock;
import java.util.Set;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.owt.api.config.security.handler.ForbiddenErrorHandler;
import com.owt.api.config.security.handler.UnauthorizedErrorHandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
@EnableWebSecurity
public class TestConfig
{
    @Bean
    @Primary
    public Clock clock()
    {
        Clock clock = mock(Clock.class);
        when(clock.instant()).thenReturn(ClockFixture.now());
        return clock;
    }

    @Bean
    @Primary
    public UnauthorizedErrorHandler unauthorizedErrorHandler()
    {
        return new UnauthorizedErrorHandler();
    }

    @Bean
    @Primary
    public ForbiddenErrorHandler forbiddenErrorHandler()
    {
        return new ForbiddenErrorHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        // Configure security settings ===========================================================================

        return httpSecurity
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedErrorHandler())
                .accessDeniedHandler(new ForbiddenErrorHandler())
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
    @Primary
    public UserDetailsService inMemoryUserDetailsManager()
    {
        UserDetails user1 = User.withUsername("user1@mail.com")
                                .password(dummyPasswordEncoder().encode("password"))
                                .roles("ADMIN")
                                .build();
        UserDetails user2 = User.withUsername("user2@mail.com")
                                .password(dummyPasswordEncoder().encode("password"))
                                .roles("ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(Set.of(user1, user2));
    }

    @Bean
    @Primary
    public PasswordEncoder dummyPasswordEncoder()
    {
        return new PasswordEncoder()
        {
            @Override
            public String encode(CharSequence rawPassword)
            {
                return String.valueOf(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword)
            {
                return encodedPassword.equals(encode(rawPassword));
            }
        };
    }
}
