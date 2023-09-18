package com.owt.api.dto.contact.__fixture__;

import java.util.UUID;

import com.owt.api.core.model.dto.ContactDto;
import com.owt.api.core.model.dto.CreateContactDto;
import com.owt.api.core.model.dto.UpdateContactDto;

import static com.owt.api.dto.contact.__fixture__.AddressDtoFixture.addressDto;

public class ContactDtoFixture
{
    public static CreateContactDto createContactDto()
    {
        ContactDto contactDto = contactDto();
        return new CreateContactDto().firstName(contactDto.getFirstName())
                                     .lastName(contactDto.getLastName())
                                     .email(contactDto.getEmail())
                                     .phone(contactDto.getPhone())
                                     .address(contactDto.getAddress());
    }

    public static UpdateContactDto updateContactDto()
    {
        ContactDto contactDto = contactDto();
        return new UpdateContactDto().firstName(contactDto.getFirstName())
                                     .lastName(contactDto.getLastName())
                                     .email(contactDto.getEmail())
                                     .phone(contactDto.getPhone())
                                     .address(contactDto.getAddress());
    }

    private static ContactDto contactDto()
    {
        return new ContactDto().id(UUID.randomUUID())
                               .firstName("John")
                               .lastName("Doe")
                               .email("john.doe@mail.com")
                               .phone("0123456789")
                               .address(addressDto());
    }
}
