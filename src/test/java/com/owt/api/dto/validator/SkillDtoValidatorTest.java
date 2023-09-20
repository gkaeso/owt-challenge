package com.owt.api.dto.validator;

import org.junit.jupiter.api.Test;

import com.owt.api.core.model.dto.CreateSkillDto;
import com.owt.api.core.model.dto.UpdateSkillDto;
import com.owt.api.exception.ResourceValidationException;

import static com.owt.api.dto.skill.__fixture__.SkillDtoFixture.createSkillDto;
import static com.owt.api.dto.skill.__fixture__.SkillDtoFixture.updateSkillDto;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SkillDtoValidatorTest
{
    SkillDtoValidator skillDtoValidator = new SkillDtoValidator();

    @Test
    void validate_whenCreate_andMissingName_thenThrows()
    {
        // given
        CreateSkillDto createSkillDto = createSkillDto();
        createSkillDto.setName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> skillDtoValidator.validate(createSkillDto));
    }

    @Test
    void validate_whenCreate_andMissingLevel_thenThrows()
    {
        // given
        CreateSkillDto createSkillDto = createSkillDto();
        createSkillDto.setLevel(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> skillDtoValidator.validate(createSkillDto));
    }

    @Test
    void validate_whenUpdate_andMissingName_thenThrows()
    {
        // given
        UpdateSkillDto updateSkillDto = updateSkillDto();
        updateSkillDto.setName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> skillDtoValidator.validate(updateSkillDto));
    }

    @Test
    void validate_whenUpdate_andMissingLevel_thenThrows()
    {
        // given
        UpdateSkillDto updateSkillDto = updateSkillDto();
        updateSkillDto.setLevel(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> skillDtoValidator.validate(updateSkillDto));
    }
}