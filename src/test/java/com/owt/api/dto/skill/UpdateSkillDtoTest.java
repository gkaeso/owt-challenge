package com.owt.api.dto.skill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.model.dto.UpdateSkillDto;
import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.Skill;

import static com.owt.api.dto.skill.__fixture__.SkillDtoFixture.updateSkillDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class UpdateSkillDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenDto_thenMap()
    {
        // given
        UpdateSkillDto updateSkillDto = updateSkillDto();

        // when
        Skill skill = modelMapper.map(updateSkillDto, Skill.class);

        // then
        assertThat(skill).isNotNull();
    }

    @Test
    void map_whenDto_thenMapName()
    {
        // given
        UpdateSkillDto updateSkillDto = updateSkillDto();

        // when
        Skill skill = modelMapper.map(updateSkillDto, Skill.class);

        // then
        assertThat(skill.getName()).isNotNull()
                                   .isNotNull()
                                   .isEqualTo(updateSkillDto.getName());
    }

    @Test
    void map_whenDto_thenMapLevel()
    {
        // given
        UpdateSkillDto updateSkillDto = updateSkillDto();

        // when
        Skill skill = modelMapper.map(updateSkillDto, Skill.class);

        // then
        assertThat(skill.getLevel()).isNotNull()
                                    .isEqualTo(Level.valueOf(updateSkillDto.getLevel().name()));
    }
}