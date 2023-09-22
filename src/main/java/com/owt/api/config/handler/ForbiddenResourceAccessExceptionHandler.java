package com.owt.api.config.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.owt.api.exception.ForbiddenResourceAccessException;

@RestControllerAdvice
interface ForbiddenResourceAccessExceptionHandler
{
    @ExceptionHandler(ForbiddenResourceAccessException.class)
    void handle(HttpServletRequest request, HttpServletResponse response, ForbiddenResourceAccessException exception)
            throws IOException;
}
