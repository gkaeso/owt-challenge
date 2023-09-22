package com.owt.api.rest.dto.contact;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.contact.Address;
import com.owt.api.core.contact.Contact;
import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.Skill;
import com.owt.api.rest.dto.AddressDto;
import com.owt.api.rest.dto.ContactDto;
import com.owt.api.rest.dto.SkillDto;
import com.owt.api.rest.dto.contact.__fixture__.ContactDtoFixture;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class ContactDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenDto_thenMap()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        assertThat(contact).isNotNull();
    }

    @Test
    void map_whenDto_thenMapFirstName()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        assertThat(contact.getFirstName()).isNotNull()
                                          .isNotNull()
                                          .isEqualTo(contactDto.getFirstName());
    }

    @Test
    void map_whenDto_thenMapLastName()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        assertThat(contact.getLastName()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(contactDto.getLastName());
    }

    @Test
    void map_whenDto_thenMapEmail()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        assertThat(contact.getEmail()).isNotNull()
                                      .isNotNull()
                                      .isEqualTo(contactDto.getEmail());
    }

    @Test
    void map_whenDto_thenMapPhone()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        assertThat(contact.getPhoneNumber()).isNotNull()
                                            .isNotNull()
                                            .isEqualTo(contactDto.getPhone());
    }

    @Test
    void map_whenDto_thenMapAddress()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        Address address = contact.getAddress();
        assertThat(address).isNotNull();
        assertThat(address.getStreet()).isNotNull()
                                       .isNotNull()
                                       .isEqualTo(contactDto.getAddress().getStreet());
        assertThat(address.getPostCode()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(contactDto.getAddress().getPostCode());
        assertThat(address.getCity()).isNotNull()
                                     .isNotNull()
                                     .isEqualTo(contactDto.getAddress().getCity());
    }

    @Test
    void map_whenDto_thenDoesNotMapSkills()
    {
        // given
        ContactDto contactDto = ContactDtoFixture.contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        Set<Skill> skills = contact.getSkills();
        assertThat(skills).isNotNull()
                          .isEmpty(); // skills are set with service, not by mapping
    }

    @Test
    void map_whenModel_thenMap()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto).isNotNull();
    }

    @Test
    void map_whenModel_thenMapFirstName()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getFirstName()).isNotNull()
                                             .isNotNull()
                                             .isEqualTo(contact.getFirstName());
    }

    @Test
    void map_whenModel_thenMapLastName()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getLastName()).isNotNull()
                                            .isNotNull()
                                            .isEqualTo(contact.getLastName());
    }

    @Test
    void map_whenModel_thenMapEmail()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getEmail()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(contact.getEmail());
    }

    @Test
    void map_whenModel_thenMapPhone()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getPhone()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(contact.getPhoneNumber());
    }

    @Test
    void map_whenModel_thenMapAddress()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        Address address = contact.getAddress();
        AddressDto addressDto = contactDto.getAddress();
        assertThat(addressDto).isNotNull();
        assertThat(addressDto.getStreet()).isNotNull()
                                          .isNotNull()
                                          .isEqualTo(address.getStreet());
        assertThat(addressDto.getPostCode()).isNotNull()
                                            .isNotNull()
                                            .isEqualTo(address.getPostCode());
        assertThat(addressDto.getCity()).isNotNull()
                                        .isNotNull()
                                        .isEqualTo(address.getCity());
    }

    @Test
    void map_whenModel_thenMapSkills()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        List<SkillDto> skillDtos = contactDto.getSkills();
        assertThat(skillDtos).isNotNull()
                             .isNotEmpty()
                             .hasSameSizeAs(contactDto.getSkills());
        contact.getSkills()
               .forEach(skill -> assertThat(skillDtos).anySatisfy(skillDto -> skillEquals(skill, skillDto)));
    }

    private void skillEquals(Skill skill, SkillDto skillDto)
    {
        assertThat(skill.getName()).isNotNull()
                                   .isNotEmpty()
                                   .isEqualTo(skillDto.getName());
        assertThat(skill.getLevel()).isNotNull()
                                    .isEqualTo(Level.valueOf(skillDto.getLevel().name()));
    }
}