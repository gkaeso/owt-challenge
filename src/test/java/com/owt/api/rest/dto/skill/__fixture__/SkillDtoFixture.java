package com.owt.api.rest.dto.skill.__fixture__;

import com.owt.api.rest.dto.LevelDto;
import com.owt.api.rest.dto.SkillDto;

public class SkillDtoFixture
{
    public static SkillDto skillDto()
    {
        return new SkillDto().name("cooking")
                             .level(LevelDto.EXPERT);
    }

    public static SkillDto skillDto(String name, LevelDto level)
    {
        return new SkillDto().name(name)
                             .level(level);
    }
}
