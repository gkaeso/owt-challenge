package com.owt.api.core.contact;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.owt.api.controller.ContactsApi;
import com.owt.api.core.model.dto.*;
import com.owt.api.dto.validator.ContactDtoValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ContactController implements ContactsApi
{
    private final ModelMapper modelMapper;
    private final ContactService contactService;
    private final ContactDtoValidator contactDtoValidator = new ContactDtoValidator();

    @Override
    public ResponseEntity<CreatedContactDto> createContact(CreateContactDto contactDto)
    {
        contactDtoValidator.validate(contactDto);
        Contact contact = contactService.create(modelMapper.map(contactDto, Contact.class));
        return ResponseEntity.ok(modelMapper.map(contact, CreatedContactDto.class));
    }

    @Override
    public ResponseEntity<ReadContactDto> getContact(UUID id)
    {
        Contact contact = contactService.getByKeyId(id);
        return ResponseEntity.ok(modelMapper.map(contact, ReadContactDto.class));
    }

    @Override
    public ResponseEntity<UpdatedContactDto> updateContact(UUID id, UpdateContactDto contactDto)
    {
        contactDtoValidator.validate(contactDto);
        Contact contact = contactService.update(id, modelMapper.map(contactDto, Contact.class));
        return ResponseEntity.ok(modelMapper.map(contact, UpdatedContactDto.class));
    }

    @Override
    public ResponseEntity<Void> deleteContact(UUID id)
    {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
