package com.owt.api.rest.dto.skill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.Skill;
import com.owt.api.rest.dto.LevelDto;
import com.owt.api.rest.dto.SkillDto;

import static com.owt.api.core.skill.__fixture__.SkillFixture.skill;
import static com.owt.api.rest.dto.skill.__fixture__.SkillDtoFixture.skillDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class SkillDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenModel_thenMap()
    {
        // given
        Skill skill = skill();

        // when
        SkillDto skillDto = modelMapper.map(skill, SkillDto.class);

        // then
        assertThat(skillDto).isNotNull();
    }

    @Test
    void map_whenModel_thenMapName()
    {
        // given
        Skill skill = skill();

        // when
        SkillDto skillDto = modelMapper.map(skill, SkillDto.class);

        // then
        assertThat(skillDto.getName()).isNotNull()
                                      .isNotEmpty()
                                      .isEqualTo(skill.getName());
    }

    @Test
    void map_whenModel_thenMapLevel()
    {
        // given
        Skill skill = skill();

        // when
        SkillDto skillDto = modelMapper.map(skill, SkillDto.class);

        // then
        assertThat(skillDto.getLevel()).isNotNull()
                                       .isEqualTo(LevelDto.fromValue(skill.getLevel().name()));
    }

    @Test
    void map_whenDto_thenMap()
    {
        // given
        SkillDto skillDto = skillDto();

        // when
        Skill skill = modelMapper.map(skillDto, Skill.class);

        // then
        assertThat(skill).isNotNull();
    }

    @Test
    void map_whenDto_thenMapName()
    {
        // given
        SkillDto skillDto = skillDto();

        // when
        Skill skill = modelMapper.map(skillDto, Skill.class);

        // then
        assertThat(skill.getName()).isNotNull()
                                   .isNotEmpty()
                                   .isEqualTo(skillDto.getName());
    }

    @Test
    void map_whenDto_thenMapLevel()
    {
        // given
        SkillDto skillDto = skillDto();

        // when
        Skill skill = modelMapper.map(skillDto, Skill.class);

        // then
        assertThat(skill.getLevel()).isNotNull()
                                    .isEqualTo(Level.valueOf(skillDto.getLevel().name()));
    }
}