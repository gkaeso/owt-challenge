package com.owt.api.dto.validator;

import com.owt.api.core.model.dto.CreateContactDto;
import com.owt.api.core.model.dto.CreateSkillDto;
import com.owt.api.core.model.dto.UpdateSkillDto;
import com.owt.api.core.model.dto.UpdatedContactDto;
import com.owt.api.exception.ResourceValidationException;

public record SkillDtoValidator() implements DtoValidator
{
    public void validate(CreateSkillDto skillDto)
    {
        if (isAnyStringMissing(skillDto.getName()) || isAnyObjectMissing(skillDto.getLevel()))
        {
            throw new ResourceValidationException(CreateContactDto.class, "Request missing required arguments");
        }
    }

    public void validate(UpdateSkillDto skillDto)
    {
        if (isAnyStringMissing(skillDto.getName()) || isAnyObjectMissing(skillDto.getLevel()))
        {
            throw new ResourceValidationException(UpdatedContactDto.class, "Request missing required arguments");
        }
    }
}
