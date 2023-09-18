package com.owt.api.exception;

public class ResourceValidationException extends RuntimeException
{
    public ResourceValidationException(Class<?> clazz, String message)
    {
        super(String.format("Illegal %s: %s", clazz.getName(), message));
    }
}
