package com.owt.api.core.contact;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owt.api.config.TestConfig;
import com.owt.api.config.handler.ExceptionHandlerConfig;
import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.model.dto.*;
import com.owt.api.exception.ResourceNotFoundException;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static com.owt.api.dto.contact.__fixture__.ContactDtoFixture.createContactDto;
import static com.owt.api.dto.contact.__fixture__.ContactDtoFixture.updateContactDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import({ModelMapperConfig.class, ExceptionHandlerConfig.class, TestConfig.class})
@WebMvcTest(ContactController.class)
class ContactControllerTest
{
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ModelMapper modelMapper;
    @MockBean
    ContactService contactService;
    static final String ENDPOINT = "/contacts";

    @Test
    void createContact_whenRequestBodyMissingRequiredParameters_thenBadRequest() throws Exception
    {
        // given
        CreateContactDto createContactDto = createContactDto();
        createContactDto.email(null); // required

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(createContactDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    void createContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        when(contactService.create(any(Contact.class))).thenReturn(contact);
        CreatedContactDto createdContactDto = modelMapper.map(contact, CreatedContactDto.class);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(createContactDto())))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(createdContactDto)));
    }

    @Test
    void createContact_whenValidRequestButResourceAlreadyExists_thenConflict() throws Exception
    {
        // given
        when(contactService.create(any(Contact.class))).thenThrow(DataIntegrityViolationException.class);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(createContactDto())))
               .andExpect(status().isConflict());
    }

    @Test
    void getContact_whenInvalidRequest_thenNotFound() throws Exception
    {
        // given
        when(contactService.getByKeyId(any(UUID.class))).thenThrow(ResourceNotFoundException.class);

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(get(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    void getContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        when(contactService.getByKeyId(any(UUID.class))).thenReturn(contact);
        ReadContactDto readContactDto = modelMapper.map(contact, ReadContactDto.class);

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(get(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(readContactDto)));
    }

    @Test
    void updateContact_whenRequestBodyMissingRequiredParameters_thenBadRequest() throws Exception
    {
        // given
        UpdateContactDto updateContactDto = updateContactDto();
        updateContactDto.email(null); // required

        // when / then
        String updateEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(updateContactDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    void updateContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        UpdateContactDto updateContactDto = updateContactDto();
        UpdatedContactDto updatedContactDto = modelMapper.map(contact, UpdatedContactDto.class);
        when(contactService.update(eq(contact.getKeyId()), any(Contact.class))).thenReturn(contact);

        // when / then
        String updateEndpoint = ENDPOINT + "/" + contact.getKeyId();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(updateContactDto)))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(updatedContactDto)));
    }

    @Test
    void deleteContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        doNothing().when(contactService).deleteById(any(UUID.class));

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(delete(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNoContent());
    }
}