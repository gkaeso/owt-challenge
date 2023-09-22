package com.owt.api.core.contact;

import java.util.List;
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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owt.api.config.TestConfig;
import com.owt.api.config.handler.ExceptionHandlerConfig;
import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.SkillService;
import com.owt.api.exception.ResourceNotFoundException;
import com.owt.api.rest.dto.ContactDto;
import com.owt.api.rest.dto.LevelDto;
import com.owt.api.rest.dto.SkillDto;

import static com.owt.api.core.contact.__fixture__.ContactFixture.contact;
import static com.owt.api.core.skill.__fixture__.SkillFixture.skill;
import static com.owt.api.rest.dto.contact.__fixture__.ContactDtoFixture.contactDto;
import static com.owt.api.rest.dto.skill.__fixture__.SkillDtoFixture.skillDto;
import static org.mockito.ArgumentMatchers.any;
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
    @MockBean
    SkillService skillService;
    @MockBean
    ContactUpdateService contactUpdateService;
    static final String ENDPOINT = "/contacts";

    @Test
    @WithUserDetails("user1@mail.com")
    void createContact_whenRequestBodyMissingRequiredParameters_thenBadRequest() throws Exception
    {
        // given
        ContactDto contactDto = contactDto();
        contactDto.email(null); // required

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(contactDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void createContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        when(contactService.save(any(Contact.class))).thenReturn(contact);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(contactDto())))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(contact.getKeyId())));
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void createContact_whenValidRequestButResourceAlreadyExists_thenConflict() throws Exception
    {
        // given
        when(contactService.save(any(Contact.class))).thenThrow(DataIntegrityViolationException.class);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(contactDto())))
               .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails("user1@mail.com")
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
    @WithUserDetails("user1@mail.com")
    void getContact_whenValidRequest_thenOk() throws Exception
    {
        // given
        Contact contact = contact();
        when(contactService.getByKeyId(any(UUID.class))).thenReturn(contact);
        ContactDto readContactDto = modelMapper.map(contact, ContactDto.class);

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(get(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(readContactDto)));
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void updateContact_whenRequestBodyMissingRequiredParameters_thenBadRequest() throws Exception
    {
        // given
        ContactDto updateContactDto = contactDto();
        updateContactDto.email(null); // required

        // when / then
        String updateEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(updateContactDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void updateContact_whenValidRequest_theNoContent() throws Exception
    {
        // given
        Contact contact = contact();
        SkillDto skillDto = skillDto("running", LevelDto.JUNIOR);
        ContactDto contactDto = contactDto(List.of(skillDto));
        when(contactService.getByKeyId(contact.getKeyId())).thenReturn(contact);
        when(skillService.find(skillDto.getName(), Level.valueOf(skillDto.getLevel().name())))
                .thenReturn(skill());

        // when / then
        String updateEndpoint = ENDPOINT + "/" + contact.getKeyId();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(contactDto)))
               .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void deleteContact_whenValidRequest_thenNoContent() throws Exception
    {
        // given
        doNothing().when(contactService).deleteById(any(UUID.class));

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(delete(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNoContent());
    }
}