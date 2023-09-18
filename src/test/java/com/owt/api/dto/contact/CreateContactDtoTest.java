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
import com.owt.api.core.model.dto.CreateContactDto;

import static com.owt.api.dto.contact.__fixture__.ContactDtoFixture.createContactDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class CreateContactDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenDto_thenMap()
    {
        // given
        CreateContactDto createContactDto = createContactDto();

        // when
        Contact contact = modelMapper.map(createContactDto, Contact.class);

        // then
        assertThat(contact).isNotNull();
    }

    @Test
    void map_whenDto_thenMapFirstName()
    {
        // given
        CreateContactDto createContactDto = createContactDto();

        // when
        Contact contact = modelMapper.map(createContactDto, Contact.class);

        // then
        assertThat(contact.getFirstName()).isNotNull()
                                          .isNotNull()
                                          .isEqualTo(createContactDto.getFirstName());
    }

    @Test
    void map_whenDto_thenMapLastName()
    {
        // given
        CreateContactDto createContactDto = createContactDto();

        // when
        Contact contact = modelMapper.map(createContactDto, Contact.class);

        // then
        assertThat(contact.getLastName()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(createContactDto.getLastName());
    }

    @Test
    void map_whenDto_thenMapEmail()
    {
        // given
        CreateContactDto createContactDto = createContactDto();

        // when
        Contact contact = modelMapper.map(createContactDto, Contact.class);

        // then
        assertThat(contact.getEmail()).isNotNull()
                                      .isNotNull()
                                      .isEqualTo(createContactDto.getEmail());
    }

    @Test
    void map_whenDto_thenMapPhone()
    {
        // given
        CreateContactDto createContactDto = createContactDto();

        // when
        Contact contact = modelMapper.map(createContactDto, Contact.class);

        // then
        assertThat(contact.getPhoneNumber()).isNotNull()
                                            .isNotNull()
                                            .isEqualTo(createContactDto.getPhone());
    }

    @Test
    void map_whenDto_thenMapAddress()
    {
        // given
        CreateContactDto createContactDto = createContactDto();

        // when
        Contact contact = modelMapper.map(createContactDto, Contact.class);

        // then
        Address address = contact.getAddress();
        assertThat(address).isNotNull();
        assertThat(address.getStreet()).isNotNull()
                                       .isNotNull()
                                       .isEqualTo(createContactDto.getAddress().getStreet());
        assertThat(address.getPostCode()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(createContactDto.getAddress().getPostCode());
        assertThat(address.getCity()).isNotNull()
                                     .isNotNull()
                                     .isEqualTo(createContactDto.getAddress().getCity());
    }
}