package com.owt.api.config;

import java.time.Clock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
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
}
