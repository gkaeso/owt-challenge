package com.owt.api.core.contact;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.owt.api.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import static java.util.function.Predicate.not;

@Service
@RequiredArgsConstructor
public class ContactService
{
    private final ContactRepository contactRepository;

    public Contact getByKeyId(UUID keyId)
    {
        return contactRepository.findByKeyId(keyId)
                                .orElseThrow(() -> new ResourceNotFoundException(Contact.class, keyId));
    }

    public Contact create(Contact contact)
    {
        return contactRepository.save(contact);
    }

    public Contact update(UUID keyId, Contact newContact)
    {
        Contact contact = getByKeyId(keyId);

        Optional.ofNullable(newContact.getFirstName())
                .filter(not(Strings::isEmpty))
                .ifPresent(contact::setFirstName);
        Optional.ofNullable(newContact.getLastName())
                .filter(not(Strings::isEmpty))
                .ifPresent(contact::setLastName);
        Optional.ofNullable(newContact.getEmail())
                .filter(not(Strings::isEmpty))
                .ifPresent(contact::setEmail);
        Optional.ofNullable(newContact.getPhoneNumber())
                .or(Optional::empty)
                .ifPresent(contact::setPhoneNumber);
        Optional.ofNullable(newContact.getAddress())
                .ifPresent(contact::setAddress);

        return contactRepository.save(contact);
    }

    @Transactional
    public void deleteById(UUID keyId)
    {
        contactRepository.deleteByKeyId(keyId);
    }
}
