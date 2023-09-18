package com.owt.api.dto.contact;

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
import com.owt.api.core.model.dto.UpdateContactDto;

import static com.owt.api.dto.contact.__fixture__.ContactDtoFixture.updateContactDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class UpdateContactDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenDto_thenMap()
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();

        // when
        Contact contact = modelMapper.map(updateContactDto, Contact.class);

        // then
        assertThat(contact).isNotNull();
    }

    @Test
    void map_whenDto_thenMapFirstName()
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();

        // when
        Contact contact = modelMapper.map(updateContactDto, Contact.class);

        // then
        assertThat(contact.getFirstName()).isNotNull()
                                          .isNotNull()
                                          .isEqualTo(updateContactDto.getFirstName());
    }

    @Test
    void map_whenDto_thenMapLastName()
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();

        // when
        Contact contact = modelMapper.map(updateContactDto, Contact.class);

        // then
        assertThat(contact.getLastName()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(updateContactDto.getLastName());
    }

    @Test
    void map_whenDto_thenMapEmail()
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();

        // when
        Contact contact = modelMapper.map(updateContactDto, Contact.class);

        // then
        assertThat(contact.getEmail()).isNotNull()
                                      .isNotNull()
                                      .isEqualTo(updateContactDto.getEmail());
    }

    @Test
    void map_whenDto_thenMapPhone()
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();

        // when
        Contact contact = modelMapper.map(updateContactDto, Contact.class);

        // then
        assertThat(contact.getPhoneNumber()).isNotNull()
                                            .isNotNull()
                                            .isEqualTo(updateContactDto.getPhone());
    }

    @Test
    void map_whenDto_thenMapAddress()
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();

        // when
        Contact contact = modelMapper.map(updateContactDto, Contact.class);

        // then
        Address address = contact.getAddress();
        assertThat(address).isNotNull();
        assertThat(address.getStreet()).isNotNull()
                                       .isNotNull()
                                       .isEqualTo(updateContactDto.getAddress().getStreet());
        assertThat(address.getPostCode()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(updateContactDto.getAddress().getPostCode());
        assertThat(address.getCity()).isNotNull()
                                     .isNotNull()
                                     .isEqualTo(updateContactDto.getAddress().getCity());
    }
}