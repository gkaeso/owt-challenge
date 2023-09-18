package com.owt.api.config.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.owt.api.exception.ResourceValidationException;

@RestControllerAdvice
interface ResourceValidationHandler
{
    @ExceptionHandler(ResourceValidationException.class)
    void handle(HttpServletRequest request, HttpServletResponse response, ResourceValidationException exception)
            throws IOException;
}
