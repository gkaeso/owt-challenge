package com.owt.api.core.contact;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.owt.api.rest.controller.ContactsApi;
import com.owt.api.rest.dto.ContactDto;
import com.owt.api.rest.validator.ContactDtoValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class ContactController implements ContactsApi
{
    private final ModelMapper modelMapper;
    private final ContactService contactService;
    private final ContactUpdateService contactUpdateService;
    private final ContactDtoValidator contactDtoValidator = new ContactDtoValidator();

    @Override
    public ResponseEntity<UUID> createContact(ContactDto contactDto)
    {
        contactDtoValidator.validate(contactDto);
        Contact c = contactService.save(modelMapper.map(contactDto, Contact.class));
        return ResponseEntity.ok(c.getKeyId());
    }

    @Override
    public ResponseEntity<ContactDto> getContact(UUID id)
    {
        Contact contact = contactService.getByKeyId(id);
        return ResponseEntity.ok(modelMapper.map(contact, ContactDto.class));
    }

    @Override
    public ResponseEntity<Void> updateContact(UUID id, ContactDto contactDto)
    {
        contactDtoValidator.validate(contactDto);
        contactUpdateService.update(id, contactDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteContact(UUID id)
    {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
