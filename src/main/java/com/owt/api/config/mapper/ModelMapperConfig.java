package com.owt.api.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.owt.api.core.contact.Contact;
import com.owt.api.core.model.dto.*;
import com.owt.api.core.skill.Skill;

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
        modelMapper.createTypeMap(CreateContactDto.class, Contact.class)
                   .addMapping(CreateContactDto::getPhone, Contact::setPhoneNumber);

        modelMapper.createTypeMap(Contact.class, CreatedContactDto.class)
                   .addMapping(Contact::getKeyId, CreatedContactDto::setId)
                   .addMapping(Contact::getPhoneNumber, CreatedContactDto::setPhone);

        modelMapper.createTypeMap(Contact.class, ReadContactDto.class)
                   .addMapping(Contact::getKeyId, ReadContactDto::setId)
                   .addMapping(Contact::getPhoneNumber, ReadContactDto::setPhone);

        modelMapper.createTypeMap(UpdateContactDto.class, Contact.class)
                   .addMapping(UpdateContactDto::getPhone, Contact::setPhoneNumber);

        modelMapper.createTypeMap(Contact.class, UpdatedContactDto.class)
                   .addMapping(Contact::getKeyId, UpdatedContactDto::setId)
                   .addMapping(Contact::getPhoneNumber, UpdatedContactDto::setPhone);
    }

    private void configureSkill(ModelMapper modelMapper)
    {
        modelMapper.createTypeMap(Skill.class, CreatedSkillDto.class)
                   .addMapping(Skill::getKeyId, CreatedSkillDto::setId);

        modelMapper.createTypeMap(Skill.class, UpdatedSkillDto.class)
                   .addMapping(Skill::getKeyId, UpdatedSkillDto::setId);
    }
}