package com.owt.api.core.skill.__fixture__;


import com.owt.api.core.skill.Level;
import com.owt.api.core.skill.Skill;

import static com.owt.api.core.BaseEntityFixture.fillBase;
import static com.owt.api.core.skill.Level.JUNIOR;

public class SkillFixture
{
    public static Skill skill()
    {
        return skill("programming", JUNIOR);
    }

    public static Skill skill(String name, Level level)
    {
        return fillBase(new Skill(name, level));
    }
}
