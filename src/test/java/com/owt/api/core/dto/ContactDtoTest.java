package com.owt.api.core.dto;

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
import com.owt.api.core.model.dto.AddressDto;
import com.owt.api.core.model.dto.ContactDto;

import static com.owt.api.core.contact.ContactFixture.contact;
import static com.owt.api.core.dto.ContactDtoFixture.contactDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class ContactDtoTest
{
    @Autowired
    ModelMapper modelMapper;

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
    void map_whenModel_thenMapKeyId()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getId()).isNotNull()
                                      .isEqualTo(contact.getKeyId());
    }

    @Test
    void map_whenModel_thenMapFirstName()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getFirstname()).isNotNull()
                                             .isNotEmpty()
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
        assertThat(contactDto.getLastname()).isNotNull()
                                            .isNotEmpty()
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
                                         .isNotEmpty()
                                         .isEqualTo(contact.getEmail());
    }

    @Test
    void map_whenModel_thenMapPhoneNumber()
    {
        // given
        Contact contact = contact();

        // when
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);

        // then
        assertThat(contactDto.getPhone()).isNotNull()
                                         .isNotEmpty()
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
        assertThat(contactDto.getAddress()).isNotNull()
                                           .isEqualTo(new AddressDto().street(address.getStreet())
                                                                      .postCode(address.getPostCode())
                                                                      .city(address.getCity()));
    }

    @Test
    void map_whenDto_thenMap()
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto, Contact.class);

        // then
        assertThat(contact).isNotNull();
    }

    @Test
    void map_whenDto_thenMapId() // TODO - fix without adding explicit setter to entity
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto(), Contact.class);

        // then
        assertThat(contact.getKeyId()).isNotNull()
                                      .isEqualTo(contactDto.getId());
    }

    @Test
    void map_whenDto_thenMapFirstName()
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto(), Contact.class);

        // then
        assertThat(contact.getFirstName()).isNotNull()
                                          .isNotNull()
                                          .isEqualTo(contactDto.getFirstname());
    }

    @Test
    void map_whenDto_thenMapLastName()
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto(), Contact.class);

        // then
        assertThat(contact.getLastName()).isNotNull()
                                         .isNotNull()
                                         .isEqualTo(contactDto.getLastname());
    }

    @Test
    void map_whenDto_thenMapEmail()
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto(), Contact.class);

        // then
        assertThat(contact.getEmail()).isNotNull()
                                      .isNotNull()
                                      .isEqualTo(contactDto.getEmail());
    }

    @Test
    void map_whenDto_thenMapPhone()
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto(), Contact.class);

        // then
        assertThat(contact.getPhoneNumber()).isNotNull()
                                            .isNotNull()
                                            .isEqualTo(contactDto.getPhone());
    }

    @Test
    void map_whenDto_thenMapAddress()
    {
        // given
        ContactDto contactDto = contactDto();

        // when
        Contact contact = modelMapper.map(contactDto(), Contact.class);

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
}