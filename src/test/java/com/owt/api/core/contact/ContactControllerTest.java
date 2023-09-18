package com.owt.api.core.contact;

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
import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.model.dto.ContactDto;

import static com.owt.api.core.contact.ContactFixture.contact;
import static com.owt.api.core.dto.ContactDtoFixture.contactDtoWithoutId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import({ModelMapperConfig.class})
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
        ContactDto contactDto = contactDtoWithoutId();
        contactDto.email(null); // required

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(contactDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    void createContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        when(contactService.create(any(Contact.class))).thenReturn(contact);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(contactDtoWithoutId())))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(modelMapper.map(contact, ContactDto.class))));
    }

    @Test
    void createContact_whenValidRequestButContactAlreadyExists_thenConflict() throws Exception
    {
        // given
        when(contactService.create(any(Contact.class))).thenThrow(DataIntegrityViolationException.class);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(contactDtoWithoutId())))
               .andExpect(status().isConflict());
    }

    @Test
    void updateContact_whenUpdateAnyField_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        when(contactService.update(eq(contact.getKeyId()), any(Contact.class))).thenReturn(contact);

        // when / then
        String updateEndpoint = ENDPOINT + "/" + contact.getKeyId();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(modelMapper.map(contact,
                                                                                                    ContactDto.class))))
               .andExpect(status().isOk());
    }
}