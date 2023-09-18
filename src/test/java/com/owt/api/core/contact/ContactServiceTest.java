package com.owt.api.core.contact;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.owt.api.exception.ResourceNotFoundException;

import static com.owt.api.core.contact.AddressFixture.address;
import static com.owt.api.core.contact.ContactFixture.contact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
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
    void getByKeyId_whenInvalidKeyId_thenReturnsContact()
    {
        // given
        UUID keyId = UUID.randomUUID();
        when(contactRepository.findByKeyId(keyId)).thenReturn(Optional.empty());

        // when / then
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> contactService.getByKeyId(keyId));
    }

    @Test
    void update_whenValid_thenReturnsUpdatedContact()
    {
        // given
        Contact contact = contact("John", "Doe", "john.doe@mail.com",
                                  "0123456789", address("street", "1234", "city"));
        Contact contactWithNewValues = contact("Jessie", "James", "jesse.james@mail.com",
                                               "9876543210", address("way", "4321", "town"));
        when(contactRepository.findByKeyId(contact.getKeyId())).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).then(returnsFirstArg());

        // when
        Contact updatedContact = contactService.update(contact.getKeyId(), contactWithNewValues);

        // then
        assertThat(updatedContact).isNotNull()
                                  .isEqualTo(contact);
        assertThat(updatedContact.getFirstName()).isNotNull()
                                                 .isNotEmpty()
                                                 .isEqualTo(contactWithNewValues.getFirstName());
        assertThat(updatedContact.getLastName()).isNotNull()
                                                .isNotEmpty()
                                                .isEqualTo(contactWithNewValues.getLastName());
        assertThat(updatedContact.getEmail()).isNotNull()
                                             .isNotEmpty()
                                             .isEqualTo(contactWithNewValues.getEmail());
        assertThat(updatedContact.getAddress()).isNotNull()
                                               .isEqualTo(contactWithNewValues.getAddress());
    }
}