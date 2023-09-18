package com.owt.api.dto.validator;

import com.owt.api.core.model.dto.CreateContactDto;
import com.owt.api.core.model.dto.UpdateContactDto;
import com.owt.api.core.model.dto.UpdatedContactDto;
import com.owt.api.exception.ResourceValidationException;

public record ContactDtoValidator() implements DtoValidator
{
    public void validate(CreateContactDto contactDto)
    {
        if (isAnyStringMissing(contactDto.getFirstName(),
                               contactDto.getLastName(),
                               contactDto.getEmail()))
        {
            throw new ResourceValidationException(CreateContactDto.class, "Request missing required arguments");
        }
    }

    public void validate(UpdateContactDto contactDto)
    {
        if (isAnyStringMissing(contactDto.getFirstName(),
                               contactDto.getLastName(),
                               contactDto.getEmail()))
        {
            throw new ResourceValidationException(UpdatedContactDto.class, "Request missing required arguments");
        }
    }
}
