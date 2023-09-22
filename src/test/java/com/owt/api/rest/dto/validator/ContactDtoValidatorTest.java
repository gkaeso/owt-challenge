package com.owt.api.rest.dto.validator;

import org.junit.jupiter.api.Test;

import com.owt.api.exception.ResourceValidationException;
import com.owt.api.rest.dto.ContactDto;
import com.owt.api.rest.dto.contact.__fixture__.ContactDtoFixture;
import com.owt.api.rest.validator.ContactDtoValidator;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ContactDtoValidatorTest
{
    ContactDtoValidator contactDtoValidator = new ContactDtoValidator();

    @Test
    void validate_whenMissingFirstName_thenThrows()
    {
        // given
        ContactDto ContactDto = ContactDtoFixture.contactDto();
        ContactDto.setFirstName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(ContactDto));
    }

    @Test
    void validate_whenMissingLastName_thenThrows()
    {
        // given
        ContactDto ContactDto = ContactDtoFixture.contactDto();
        ContactDto.setLastName(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(ContactDto));
    }

    @Test
    void validate_whenMissingEmail_thenThrows()
    {
        // given
        ContactDto ContactDto = ContactDtoFixture.contactDto();
        ContactDto.setEmail(null);

        // when / then
        assertThatExceptionOfType(ResourceValidationException.class)
                .isThrownBy(() -> contactDtoValidator.validate(ContactDto));
    }

    @Test
    void validate_whenMissingOptionalField_thenValid()
    {
        // given
        ContactDto ContactDto = ContactDtoFixture.contactDto();
        ContactDto.setPhone(null);

        // when / then
        assertThatNoException().isThrownBy(() -> contactDtoValidator.validate(ContactDto));
    }
}