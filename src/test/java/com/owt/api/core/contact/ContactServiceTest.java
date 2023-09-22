package com.owt.api.core.contact;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.owt.api.exception.ResourceNotFoundException;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest
{
    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactService contactService;

    @Test
    void getByKeyId_whenValidKeyId_thenReturnsContact()
    {
        // given
        Contact contact = contact();
        when(contactRepository.findByKeyId(contact.getKeyId())).thenReturn(Optional.of(contact));

        // when
        Contact returnedContact = contactService.getByKeyId(contact.getKeyId());

        // then
        assertThat(returnedContact).isNotNull()
                                   .isEqualTo(contact);
    }

    @Test
    void getByKeyId_whenInvalidKeyId_thenThrows()
    {
        // given
        UUID keyId = UUID.randomUUID();
        when(contactRepository.findByKeyId(keyId)).thenReturn(Optional.empty());

        // when / then
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> contactService.getByKeyId(keyId));
    }
}