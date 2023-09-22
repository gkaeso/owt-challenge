package com.owt.api.config.handler;

import java.time.Clock;
import java.time.ZoneOffset;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owt.api.rest.dto.ErrorDto;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ExceptionHandlerConfig
{
    private final Clock clock;
    private final ObjectMapper objectMapper;

    @Bean
    ResourceNotFoundHandler resourceNotFoundHandler()
    {
        return (request, response, exception) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(errorDto(exception.getClass(),
                                                                    exception.getMessage())));
        };
    }

    @Bean
    ResourceAlreadyExistsHandler resourceAlreadyExistsHandler()
    {
        return (request, response) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(errorDto(DataIntegrityViolationException.class,
                                                                    "Resource already exists")));
        };
    }

    @Bean
    ResourceValidationHandler resourceValidationHandler()
    {
        return (request, response, exception) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(errorDto(exception.getClass(),
                                                                    exception.getMessage())));
        };
    }

    @Bean
    ForbiddenResourceAccessExceptionHandler forbiddenResourceAccessException()
    {
        return (request, response, exception) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter()
                    .write(objectMapper.writeValueAsString(errorDto(exception.getClass(),
                                                                    exception.getMessage())));
        };
    }

    private ErrorDto errorDto(Class<?> clazz, String message)
    {
        return new ErrorDto().timestamp((clock.instant().atOffset(ZoneOffset.UTC)))
                             .type(clazz.getSimpleName())
                             .message(message);
    }
}
