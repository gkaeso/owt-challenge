package com.owt.api.core.skill;

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
import com.owt.api.exception.ResourceNotFoundException;
import com.owt.api.rest.dto.SkillDto;

import static com.owt.api.core.skill.__fixture__.SkillFixture.skill;
import static com.owt.api.rest.dto.skill.__fixture__.SkillDtoFixture.skillDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import({ModelMapperConfig.class, ExceptionHandlerConfig.class, TestConfig.class})
@WebMvcTest(SkillController.class)
class SkillControllerTest
{

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ModelMapper modelMapper;
    @MockBean
    SkillService skillService;
    static final String ENDPOINT = "/skills";

    @Test
    @WithUserDetails("user1@mail.com")
    void createSkill_whenRequestBodyMissingRequiredParameters_thenBadRequest() throws Exception
    {
        // given
        SkillDto createSkillDto = skillDto();
        createSkillDto.name(null); // required

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(createSkillDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void createSkill_whenValidRequest_thenOk() throws Exception
    {
        // given
        Skill skill = skill();
        when(skillService.create(any(Skill.class))).thenReturn(skill);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(skillDto())))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(skill.getKeyId())));
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void createSkill_whenValidRequestButResourceAlreadyExists_thenConflict() throws Exception
    {
        // given
        when(skillService.create(any(Skill.class))).thenThrow(DataIntegrityViolationException.class);

        // when / then
        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(skillDto())))
               .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void getSkill_whenInvalidRequest_thenNotFound() throws Exception
    {
        // given
        when(skillService.getByKeyId(any(UUID.class))).thenThrow(ResourceNotFoundException.class);

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(get(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void getSkill_whenValidRequest_thenOk() throws Exception
    {
        // given
        Skill skill = skill();
        when(skillService.getByKeyId(any(UUID.class))).thenReturn(skill);
        SkillDto readSkillDto = modelMapper.map(skill, SkillDto.class);

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(get(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(readSkillDto)));
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void updateSkill_whenRequestBodyMissingRequiredParameters_thenBadRequest() throws Exception
    {
        // given
        SkillDto updateSkillDto = skillDto();
        updateSkillDto.name(null); // required

        // when / then
        String updateEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(updateSkillDto)))
               .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void updateSkill_whenValidRequest_thenNoContent() throws Exception
    {
        // given
        Skill skill = skill();
        SkillDto updateSkillDto = skillDto();
        when(skillService.update(eq(skill.getKeyId()), any(Skill.class))).thenReturn(skill);

        // when / then
        String updateEndpoint = ENDPOINT + "/" + skill.getKeyId();
        mockMvc.perform(put(updateEndpoint).contentType(MediaType.APPLICATION_JSON)
                                           .content(objectMapper.writeValueAsString(updateSkillDto)))
               .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("user1@mail.com")
    void deleteSkill_whenValidRequest_thenNoContent() throws Exception
    {
        // given
        doNothing().when(skillService).deleteById(any(UUID.class));

        // when / then
        String readEndpoint = ENDPOINT + "/" + UUID.randomUUID();
        mockMvc.perform(delete(readEndpoint).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isNoContent());
    }
}