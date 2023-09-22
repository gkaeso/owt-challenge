package com.owt.api.core.contact;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.owt.api.core.contact.manager.ContactManagerAccessService;
import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.Skill;
import com.owt.api.core.skill.SkillService;
import com.owt.api.rest.dto.ContactDto;
import com.owt.api.rest.dto.SkillDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContactUpdateService
{
    private final ContactService contactService;
    private final SkillService skillService;
    private final ModelMapper modelMapper;
    private final ContactManagerAccessService contactManagerAccessService;

    void update(UserDetails principal, UUID keyId, ContactDto contactDto)
    {
        Contact contact = contactService.getByKeyId(keyId);
        contactManagerAccessService.validateAccess(principal.getUsername(), contact);

        Contact contactWithNewValues = modelMapper.map(contactDto, Contact.class);

        Optional.ofNullable(contactWithNewValues.getFirstName())
                .ifPresent(contact::setFirstName);
        Optional.ofNullable(contactWithNewValues.getLastName())
                .ifPresent(contact::setLastName);
        Optional.ofNullable(contactWithNewValues.getEmail())
                .ifPresent(contact::setEmail);
        Optional.ofNullable(contactWithNewValues.getPhoneNumber())
                .ifPresent(contact::setPhoneNumber);
        Optional.ofNullable(contactWithNewValues.getAddress())
                .ifPresent(contact::setAddress);
        Optional.ofNullable(contactDto.getSkills())
                .map(this::fetchRelevantSkills)
                .ifPresent(contact::updateSkills);

        contactService.update(contact);
    }

    private Set<Skill> fetchRelevantSkills(Collection<SkillDto> skillDtos)
    {
        return skillDtos.stream()
                        .map(skillDto -> skillService.find(skillDto.getName(),
                                                           Level.valueOf(skillDto.getLevel()
                                                                                 .name())))
                        .collect(Collectors.toSet());
    }
}
