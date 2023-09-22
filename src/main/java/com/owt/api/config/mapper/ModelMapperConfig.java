package com.owt.api.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.owt.api.core.contact.Contact;
import com.owt.api.rest.dto.ContactDto;

@Configuration
public class ModelMapperConfig
{
    @Bean
    public ModelMapper modelMapper()
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.registerModule(new Jsr310Module());
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STANDARD);

        configureContact(modelMapper);
        configureSkill(modelMapper);

        return modelMapper;
    }

    private void configureContact(ModelMapper modelMapper)
    {
        modelMapper.createTypeMap(ContactDto.class, Contact.class)
                   .addMapping(ContactDto::getPhone, Contact::setPhoneNumber);

        modelMapper.createTypeMap(Contact.class, ContactDto.class)
                   .addMapping(Contact::getPhoneNumber, ContactDto::setPhone);
    }

    private void configureSkill(ModelMapper modelMapper)
    {
        // none
    }
}