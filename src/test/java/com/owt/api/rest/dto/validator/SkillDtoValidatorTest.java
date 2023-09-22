package com.owt.api.rest.dto.validator;

import org.junit.jupiter.api.Test;

import com.owt.api.exception.ResourceValidationException;
import com.owt.api.rest.dto.SkillDto;
import com.owt.api.rest.validator.SkillDtoValidator;

import static com.owt.api.rest.dto.skill.__fixture__.SkillDtoFixture.skillDto;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SkillDtoValidatorTest
{
    SkillDtoValidator skillDtoValidator = new SkillDtoValidator();

    @Test
    void validate_whenMissingName_thenThrows()
    {
        // given
        SkillDto SkillDto = skillDto();
        SkillDto.setName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> skillDtoValidator.validate(SkillDto));
    }

    @Test
    void validate_whenMissingLevel_thenThrows()
    {
        // given
        SkillDto SkillDto = skillDto();
        SkillDto.setLevel(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> skillDtoValidator.validate(SkillDto));
    }
}