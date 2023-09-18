package com.owt.api.core.contact;

import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.owt.api.controller.ContactsApi;
import com.owt.api.core.model.dto.ContactDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ContactController implements ContactsApi
{
    private final ModelMapper modelMapper;
    private final ContactService contactService;

    @Override
    public ResponseEntity<ContactDto> createContact(ContactDto contactDto)
    {
        if (isMissingRequiredArguments(contactDto))
        {
            return ResponseEntity.badRequest().build();
        }

        try
        {
            Contact contact = contactService.create(modelMapper.map(contactDto, Contact.class));
            return ResponseEntity.ok(modelMapper.map(contact, ContactDto.class));
        }
        catch (DataIntegrityViolationException ex)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public ResponseEntity<ContactDto> updateContact(UUID id, ContactDto contactDto)
    {
        Contact updatedContact = contactService.update(id, modelMapper.map(contactDto, Contact.class));
        return ResponseEntity.ok(modelMapper.map(updatedContact, ContactDto.class));
    }

    @Override
    public ResponseEntity<Void> deleteContact(UUID id)
    {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isMissingRequiredArguments(ContactDto contactDto)
    {
        return Strings.isEmpty(contactDto.getFirstname()) ||
                Strings.isEmpty(contactDto.getLastname()) ||
                Strings.isEmpty(contactDto.getEmail());
    }
}
