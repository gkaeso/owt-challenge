package com.owt.api.dto.validator;

import org.junit.jupiter.api.Test;

import com.owt.api.core.model.dto.CreateContactDto;
import com.owt.api.core.model.dto.UpdateContactDto;
import com.owt.api.exception.ResourceValidationException;

import static com.owt.api.dto.contact.__fixture__.ContactDtoFixture.createContactDto;
import static com.owt.api.dto.contact.__fixture__.ContactDtoFixture.updateContactDto;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ContactDtoValidatorTest
{
    ContactDtoValidator contactDtoValidator = new ContactDtoValidator();

    @Test
    void validate_whenCreate_andMissingFirstName_thenThrows()
    {
        // given
        CreateContactDto createContactDto = createContactDto();
        createContactDto.setFirstName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(createContactDto));
    }

    @Test
    void validate_whenCreate_andMissingLastName_thenThrows()
    {
        // given
        CreateContactDto createContactDto = createContactDto();
        createContactDto.setLastName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(createContactDto));
    }

    @Test
    void validate_whenCreate_andMissingEmail_thenThrows()
    {
        // given
        CreateContactDto createContactDto = createContactDto();
        createContactDto.setEmail(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(createContactDto));
    }

    @Test
    void validate_whenCreate_andMissingOptional_thenValid()
    {
        // given
        CreateContactDto createContactDto = createContactDto();
        createContactDto.setPhone(null);

        // when / then
        assertThatNoException().isThrownBy(() -> contactDtoValidator.validate(createContactDto));
    }

    @Test
    void validate_whenUpdate_andMissingFirstName_thenThrows()
    {
        // given
        UpdateContactDto updatedContactDto = updateContactDto();
        updatedContactDto.setFirstName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(updatedContactDto));
    }

    @Test
    void validate_whenUpdate_andMissingLastName_thenThrows()
    {
        // given
        UpdateContactDto updatedContactDto = updateContactDto();
        updatedContactDto.setLastName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(updatedContactDto));
    }

    @Test
    void validate_whenUpdate_andMissingEmail_thenThrows()
    {
        // given
        UpdateContactDto updatedContactDto = updateContactDto();
        updatedContactDto.setEmail(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(updatedContactDto));
    }

    @Test
    void validate_whenUpdate_andMissingOptional_thenValid()
    {
        // given
        UpdateContactDto updatedContactDto = updateContactDto();
        updatedContactDto.setPhone(null);

        // when / then
        assertThatNoException().isThrownBy(() -> contactDtoValidator.validate(updatedContactDto));
    }
}