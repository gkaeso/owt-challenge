package com.owt.api.core.contact;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>
{
    Optional<Contact> findByKeyId(UUID keyId);

    void deleteByKeyId(UUID keyId);
}
