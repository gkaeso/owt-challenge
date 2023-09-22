package com.owt.api.rest.validator;

import com.owt.api.exception.ResourceValidationException;
import com.owt.api.rest.dto.ContactDto;

public record ContactDtoValidator() implements DtoValidator
{
    public void validate(ContactDto contactDto)
    {
        if (isAnyStringMissing(contactDto.getFirstName(),
                               contactDto.getLastName(),
                               contactDto.getEmail()))
        {
            throw new ResourceValidationException(ContactDto.class, "Request missing required arguments");
        }
    }
}
