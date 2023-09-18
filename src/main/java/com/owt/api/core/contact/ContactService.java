package com.owt.api.core.contact;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owt.api.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ContactService
{
    private final ContactRepository contactRepository;

    Contact create(Contact contact)
    {
        return contactRepository.save(contact);
    }

    Contact getByKeyId(UUID keyId)
    {
        return contactRepository.findByKeyId(keyId)
                                .orElseThrow(() -> new ResourceNotFoundException(Contact.class, keyId));
    }

    Contact update(UUID keyId, Contact newContact)
    {
        Contact contact = getByKeyId(keyId);

        Optional.ofNullable(newContact.getFirstName())
                .ifPresent(contact::setFirstName);
        Optional.ofNullable(newContact.getLastName())
                .ifPresent(contact::setLastName);
        Optional.ofNullable(newContact.getEmail())
                .ifPresent(contact::setEmail);
        Optional.ofNullable(newContact.getPhoneNumber())
                .ifPresent(contact::setPhoneNumber);
        Optional.ofNullable(newContact.getAddress())
                .ifPresent(contact::setAddress);

        return contactRepository.save(contact);
    }

    @Transactional
    void deleteById(UUID keyId)
    {
        contactRepository.deleteByKeyId(keyId);
    }
}
