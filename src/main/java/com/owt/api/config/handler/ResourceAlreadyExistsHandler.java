package com.owt.api.config.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
interface ResourceAlreadyExistsHandler
{
    @ExceptionHandler(DataIntegrityViolationException.class)
    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
