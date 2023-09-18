package com.owt.api.dto.validator;

import java.util.Arrays;

import org.apache.logging.log4j.util.Strings;

interface DtoValidator
{
    default boolean isAnyStringMissing(String... strings)
    {
        return Arrays.stream(strings)
                     .anyMatch(Strings::isEmpty);
    }
}
