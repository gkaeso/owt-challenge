package com.owt.api.core.contact.manager;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.owt.api.core.contact.Contact;

@Repository
public interface ContactManagerRepository extends JpaRepository<ContactManager, Long>
{
    Optional<ContactManager> findByContactAndManagerId(Contact contact, String managerId);
}
