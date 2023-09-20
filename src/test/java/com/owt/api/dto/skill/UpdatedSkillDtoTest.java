package com.owt.api.dto.skill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.owt.api.config.mapper.ModelMapperConfig;
import com.owt.api.core.model.dto.LevelDto;
import com.owt.api.core.model.dto.UpdatedSkillDto;
import com.owt.api.core.skill.Skill;

import static com.owt.api.core.skill.__fixture__.SkillFixture.skill;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ModelMapperConfig.class, loader = AnnotationConfigContextLoader.class)
class UpdatedSkillDtoTest
{
    @Autowired
    ModelMapper modelMapper;

    @Test
    void map_whenModel_thenMap()
    {
        // given
        Skill skill = skill();

        // when
        UpdatedSkillDto updatedSkillDto = modelMapper.map(skill, UpdatedSkillDto.class);

        // then
        assertThat(updatedSkillDto).isNotNull();
    }

    @Test
    void map_whenModel_thenMapKeyId()
    {
        // given
        Skill skill = skill();

        // when
        UpdatedSkillDto updatedSkillDto = modelMapper.map(skill, UpdatedSkillDto.class);

        // then
        assertThat(updatedSkillDto.getId()).isNotNull()
                                           .isEqualTo(skill.getKeyId());
    }

    @Test
    void map_whenModel_thenMapName()
    {
        // given
        Skill skill = skill();

        // when
        UpdatedSkillDto updatedSkillDto = modelMapper.map(skill, UpdatedSkillDto.class);

        // then
        assertThat(updatedSkillDto.getName()).isNotNull()
                                             .isNotEmpty()
                                             .isEqualTo(skill.getName());
    }

    @Test
    void map_whenModel_thenMapLevel()
    {
        // given
        Skill skill = skill();

        // when
        UpdatedSkillDto updatedSkillDto = modelMapper.map(skill, UpdatedSkillDto.class);

        // then
        assertThat(updatedSkillDto.getLevel()).isNotNull()
                                              .isEqualTo(LevelDto.fromValue(skill.getLevel().name()));
    }
}