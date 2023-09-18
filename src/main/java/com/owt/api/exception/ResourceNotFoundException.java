package com.owt.api.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(Class<?> type, UUID keyId)
    {
        super(String.format("Resource %s %s not found", type.getSimpleName(), keyId));
    }
}
