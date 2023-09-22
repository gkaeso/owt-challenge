package com.owt.api.core.contact.manager;

import org.springframework.stereotype.Component;

import com.owt.api.core.contact.Contact;
import com.owt.api.exception.ForbiddenResourceAccessException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public// TODO
class ContactManagerAccessService
{
    private final ContactManagerRepository contactManagerRepository;

    public void validateAccess(String managerId, Contact contact)
    {
        contactManagerRepository.findByContactAndManagerId(contact, managerId)
                                .orElseThrow(ForbiddenResourceAccessException::new);
    }
}
