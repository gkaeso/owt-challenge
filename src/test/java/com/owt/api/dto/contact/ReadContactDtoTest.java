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
import com.owt.api.core.model.dto.ReadContactDto;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class ReadContactDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenModel_thenMap()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        assertThat(readContactDto).isNotNull();
    }

    @Test
    void map_whenModel_thenMapKeyId()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        assertThat(readContactDto.getId()).isNotNull()
                                          .isEqualTo(contact.getKeyId());
    }

    @Test
    void map_whenModel_thenMapFirstName()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        assertThat(readContactDto.getFirstName()).isNotNull()
                                                 .isNotEmpty()
                                                 .isEqualTo(contact.getFirstName());
    }

    @Test
    void map_whenModel_thenMapLastName()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        assertThat(readContactDto.getLastName()).isNotNull()
                                                .isNotEmpty()
                                                .isEqualTo(contact.getLastName());
    }

    @Test
    void map_whenModel_thenMapEmail()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        assertThat(readContactDto.getEmail()).isNotNull()
                                             .isNotEmpty()
                                             .isEqualTo(contact.getEmail());
    }

    @Test
    void map_whenModel_thenMapPhoneNumber()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        assertThat(readContactDto.getPhone()).isNotNull()
                                             .isNotEmpty()
                                             .isEqualTo(contact.getPhoneNumber());
    }

    @Test
    void map_whenModel_thenMapAddress()
    {
        // given
        Contact contact = contact();

        // when
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // then
        Address address = contact.getAddress();
        assertThat(readContactDto.getAddress()).isNotNull()
                                               .isEqualTo(new AddressDto().street(address.getStreet())
                                                                          .postCode(address.getPostCode())
                                                                          .city(address.getCity()));
    }
}