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
import com.owt.api.core.model.dto.AddressDto;
import com.owt.api.core.model.dto.UpdatedContactDto;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class UpdatedContactDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenModel_thenMap()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        assertThat(updatedContactDto).isNotNull();
    }

    @Test
    void map_whenModel_thenMapKeyId()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        assertThat(updatedContactDto.getId()).isNotNull()
                                             .isEqualTo(contact.getKeyId());
    }

    @Test
    void map_whenModel_thenMapFirstName()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        assertThat(updatedContactDto.getFirstName()).isNotNull()
                                                    .isNotEmpty()
                                                    .isEqualTo(contact.getFirstName());
    }

    @Test
    void map_whenModel_thenMapLastName()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        assertThat(updatedContactDto.getLastName()).isNotNull()
                                                   .isNotEmpty()
                                                   .isEqualTo(contact.getLastName());
    }

    @Test
    void map_whenModel_thenMapEmail()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        assertThat(updatedContactDto.getEmail()).isNotNull()
                                                .isNotEmpty()
                                                .isEqualTo(contact.getEmail());
    }

    @Test
    void map_whenModel_thenMapPhoneNumber()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        assertThat(updatedContactDto.getPhone()).isNotNull()
                                                .isNotEmpty()
                                                .isEqualTo(contact.getPhoneNumber());
    }

    @Test
    void map_whenModel_thenMapAddress()
    {
        // given
        Contact contact = contact();

        // when
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);

        // then
        Address address = contact.getAddress();
        assertThat(updatedContactDto.getAddress()).isNotNull()
                                                  .isEqualTo(new AddressDto().street(address.getStreet())
                                                                             .postCode(address.getPostCode())
                                                                             .city(address.getCity()));
    }
}