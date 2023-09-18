package com.owt.api.config;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

class ClockFixture
{
    static Instant now()
    {
        return OffsetDateTime.of(2023, 9, 19,
                                 0, 0, 0, 0,
                                 ZoneOffset.UTC)
                             .toInstant();
    }
}