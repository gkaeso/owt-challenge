package com.owt.api.core.contact.manager;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.owt.api.core.contact.Contact;
import com.owt.api.exception.ForbiddenResourceAccessException;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactManagerAccessServiceTest
{
    @Mock
    ContactManagerRepository contactManagerRepository;
    @InjectMocks
    ContactManagerAccessService contactManagerAccessService;

    @Test
    void validateAccess_whenNotFound_thenThrows()
    {
        // given
        Contact contact = contact();
        when(contactManagerRepository.findByContactAndManagerId(any(), any())).thenReturn(Optional.empty());

        // when
        assertThatExceptionOfType(ForbiddenResourceAccessException.class)
                .isThrownBy(() -> contactManagerAccessService.validateAccess("id", contact));
    }

    @Test
    void validateAccess_whenFound_thenOk()
    {
        // given
        Contact contact = contact();
        when(contactManagerRepository.findByContactAndManagerId(any(), any()))
                .thenReturn(Optional.of(new ContactManager()));

        // when
        assertThatNoException().isThrownBy(() -> contactManagerAccessService.validateAccess("id", contact));
    }
}