package com.owt.api.core.dto;

import java.util.UUID;

import com.owt.api.core.model.dto.AddressDto;
import com.owt.api.core.model.dto.ContactDto;

import static com.owt.api.core.dto.AddressDtoFixture.addressDto;

public class ContactDtoFixture
{
    public static ContactDto contactDtoWithoutId()
    {
        ContactDto contactDto = contactDto();
        contactDto.setId(null);
        return contactDto;
    }

    public static ContactDto contactDto()
    {
        return contactDto("john", "doe", "john.doe@mail.com",
                          "0123456789", addressDto());
    }

    public static ContactDto contactDto(String firstName, String lastName, String email,
                                        String phoneNumber, AddressDto addressDto)
    {
        return new ContactDto().id(UUID.randomUUID())
                               .firstname(firstName)
                               .lastname(lastName)
                               .email(email)
                               .phone(phoneNumber)
                               .address(addressDto);
    }
}
