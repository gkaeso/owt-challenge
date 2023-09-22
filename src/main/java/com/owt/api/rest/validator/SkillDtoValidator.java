package com.owt.api.rest.validator;

import com.owt.api.exception.ResourceValidationException;
import com.owt.api.rest.dto.SkillDto;

public record SkillDtoValidator() implements DtoValidator
{
    public void validate(SkillDto skillDto)
    {
        if (isAnyStringMissing(skillDto.getName()) || isAnyObjectMissing(skillDto.getLevel()))
        {
            throw new ResourceValidationException(SkillDto.class, "Request missing required arguments");
        }
    }
}
