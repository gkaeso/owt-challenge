package com.owt.api.rest.dto.contact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.contact.Address;
import com.owt.api.rest.dto.AddressDto;

import static com.owt.api.core.contact.__fixture__.AddressFixture.address;
import static com.owt.api.rest.dto.contact.__fixture__.AddressDtoFixture.addressDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class AddressDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenModel_thenMap()
    {
        // given
        Address address = address();

        // when
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);

        // then
        assertThat(addressDto).isNotNull();
    }

    @Test
    void map_whenModel_thenMapStreet()
    {
        // given
        Address address = address();

        // when
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);

        // then
        assertThat(addressDto.getStreet()).isNotNull()
                                          .isNotEmpty()
                                          .isEqualTo(address.getStreet());
    }

    @Test
    void map_whenModel_thenMapPostCode()
    {
        // given
        Address address = address();

        // when
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);

        // then
        assertThat(addressDto.getPostCode()).isNotNull()
                                            .isNotEmpty()
                                            .isEqualTo(address.getPostCode());
    }

    @Test
    void map_whenModel_thenMapCity()
    {
        // given
        Address address = address();

        // when
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);

        // then
        assertThat(addressDto.getCity()).isNotNull()
                                        .isNotEmpty()
                                        .isEqualTo(address.getCity());
    }

    @Test
    void map_whenDto_thenMap()
    {
        // given
        AddressDto addressDto = addressDto();

        // when
        Address address = modelMapper.map(addressDto, Address.class);

        // then
        assertThat(address).isNotNull();
    }

    @Test
    void map_whenDto_thenMapStreet()
    {
        // given
        AddressDto addressDto = addressDto();

        // when
        Address address = modelMapper.map(addressDto, Address.class);

        // then
        assertThat(address.getStreet()).isNotNull()
                                       .isNotEmpty()
                                       .isEqualTo(addressDto.getStreet());
    }

    @Test
    void map_whenDto_thenMapPostCode()
    {
        // given
        AddressDto addressDto = addressDto();

        // when
        Address address = modelMapper.map(addressDto, Address.class);

        // then
        assertThat(address.getPostCode()).isNotNull()
                                         .isNotEmpty()
                                         .isEqualTo(addressDto.getPostCode());
    }

    @Test
    void map_whenDto_thenMapCity()
    {
        // given
        AddressDto addressDto = addressDto();

        // when
        Address address = modelMapper.map(addressDto, Address.class);

        // then
        assertThat(address.getCity()).isNotNull()
                                     .isNotEmpty()
                                     .isEqualTo(addressDto.getCity());
    }
}