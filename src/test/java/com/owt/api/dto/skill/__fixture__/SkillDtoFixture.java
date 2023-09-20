package com.owt.api.dto.skill.__fixture__;

import java.util.UUID;

import com.owt.api.core.model.dto.CreateSkillDto;
import com.owt.api.core.model.dto.LevelDto;
import com.owt.api.core.model.dto.SkillDto;
import com.owt.api.core.model.dto.UpdateSkillDto;

public class SkillDtoFixture
{
    public static CreateSkillDto createSkillDto()
    {
        SkillDto skillDto = skillDto();
        return new CreateSkillDto().id(skillDto.getId())
                                   .name(skillDto.getName())
                                   .level(skillDto.getLevel());
    }

    public static UpdateSkillDto updateSkillDto()
    {
        SkillDto skillDto = skillDto();
        return new UpdateSkillDto().name(skillDto.getName())
                                   .level(skillDto.getLevel());
    }

    private static SkillDto skillDto()
    {
        return new SkillDto().id(UUID.randomUUID())
                             .name("programming")
                             .level(LevelDto.JUNIOR);
    }
}
