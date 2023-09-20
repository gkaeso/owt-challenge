package com.owt.api.dto.skill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.model.dto.CreateSkillDto;
import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.Skill;
import com.owt.api.dto.skill.__fixture__.SkillDtoFixture;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class CreateSkillDtoTest
{

    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenDto_thenMap()
    {
        // given
        CreateSkillDto createSkillDto = SkillDtoFixture.createSkillDto();

        // when
        Skill skill = modelMapper.map(createSkillDto, Skill.class);

        // then
        assertThat(skill).isNotNull();
    }

    @Test
    void map_whenDto_thenMapName()
    {
        // given
        CreateSkillDto createSkillDto = SkillDtoFixture.createSkillDto();

        // when
        Skill skill = modelMapper.map(createSkillDto, Skill.class);

        // then
        assertThat(skill.getName()).isNotNull()
                                   .isEqualTo(createSkillDto.getName());
    }

    @Test
    void map_whenDto_thenMapLevel()
    {
        // given
        CreateSkillDto createSkillDto = SkillDtoFixture.createSkillDto();

        // when
        Skill skill = modelMapper.map(createSkillDto, Skill.class);

        // then
        assertThat(skill.getLevel()).isNotNull()
                                    .isEqualTo(Level.valueOf(createSkillDto.getLevel().name()));
    }
}
