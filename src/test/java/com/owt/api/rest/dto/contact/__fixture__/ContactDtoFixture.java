package com.owt.api.rest.dto.contact.__fixture__;

import java.util.List;

import com.owt.api.rest.dto.ContactDto;
import com.owt.api.rest.dto.LevelDto;
import com.owt.api.rest.dto.SkillDto;

import static com.owt.api.rest.dto.contact.__fixture__.AddressDtoFixture.addressDto;
import static com.owt.api.rest.dto.skill.__fixture__.SkillDtoFixture.skillDto;

public class ContactDtoFixture
{
    public static ContactDto contactDto(List<SkillDto> skills)
    {
        return new ContactDto().firstName("John")
                               .lastName("Doe")
                               .email("john.doe@mail.com")
                               .phone("0123456789")
                               .address(addressDto())
                               .skills(skills);
    }

    public static ContactDto contactDto()
    {
        return new ContactDto().firstName("John")
                               .lastName("Doe")
                               .email("john.doe@mail.com")
                               .phone("0123456789")
                               .address(addressDto())
                               .skills(List.of(skillDto("english", LevelDto.SENIOR),
                                               skillDto("german", LevelDto.SENIOR)));
    }
}
