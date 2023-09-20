package com.owt.api.core.skill;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.owt.api.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class SkillService
{
    private final SkillRepository skillRepository;

    Skill create(Skill skill)
    {
        return skillRepository.save(skill);
    }

    Skill getByKeyId(UUID keyId)
    {
        return skillRepository.findByKeyId(keyId)
                              .orElseThrow(() -> new ResourceNotFoundException(Skill.class, keyId));
    }

    Skill update(UUID keyId, Skill newSkill)
    {
        Skill skill = getByKeyId(keyId);

        Optional.ofNullable(newSkill.getName())
                .ifPresent(skill::setName);
        Optional.ofNullable(newSkill.getLevel())
                .ifPresent(skill::setLevel);

        return skillRepository.save(skill);
    }

    void deleteById(UUID keyId)
    {
        skillRepository.deleteByKeyId(keyId);
    }
}
