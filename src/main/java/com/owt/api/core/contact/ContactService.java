package com.owt.api.core.contact;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owt.api.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ContactService
{
    private final ContactRepository contactRepository;

    Contact create(UserDetails principal, Contact contact)
    {
        contact.addManager(principal.getUsername());
        return contactRepository.save(contact);
    }

    Contact getByKeyId(UUID keyId)
    {
        return contactRepository.findByKeyId(keyId)
                                .orElseThrow(() -> new ResourceNotFoundException(Contact.class, keyId));
    }

    Contact update(Contact contact)
    {
        return contactRepository.save(contact);
    }

    @Transactional
    public void deleteById(UUID keyId)
    {
        contactRepository.deleteByKeyId(keyId);
    }
}
