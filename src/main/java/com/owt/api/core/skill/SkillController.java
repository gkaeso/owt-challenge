package com.owt.api.core.skill;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.owt.api.rest.controller.SkillsApi;
import com.owt.api.rest.dto.SkillDto;
import com.owt.api.rest.validator.SkillDtoValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SkillController implements SkillsApi
{
    private final ModelMapper modelMapper;
    private final SkillService skillService;
    private final SkillDtoValidator skillDtoValidator = new SkillDtoValidator();

    @Override
    public ResponseEntity<UUID> createSkill(SkillDto skillDto)
    {
        skillDtoValidator.validate(skillDto);
        Skill skill = skillService.create(modelMapper.map(skillDto, Skill.class));
        return ResponseEntity.ok(skill.getKeyId());
    }

    @Override
    public ResponseEntity<SkillDto> getSkill(UUID id)
    {
        Skill skill = skillService.getByKeyId(id);
        return ResponseEntity.ok(modelMapper.map(skill, SkillDto.class));
    }

    @Override
    public ResponseEntity<Void> updateSkill(UUID id, SkillDto skillDto)
    {
        skillDtoValidator.validate(skillDto);
        skillService.update(id, modelMapper.map(skillDto, Skill.class));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteSkill(UUID id)
    {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
