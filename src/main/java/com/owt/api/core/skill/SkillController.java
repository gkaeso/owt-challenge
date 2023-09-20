package com.owt.api.core.skill;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.owt.api.controller.SkillsApi;
import com.owt.api.core.model.dto.*;
import com.owt.api.dto.validator.SkillDtoValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SkillController implements SkillsApi
{
    private final ModelMapper modelMapper;
    private final SkillService skillService;
    private final SkillDtoValidator skillDtoValidator = new SkillDtoValidator();

    @Override
    public ResponseEntity<CreatedSkillDto> createSkill(CreateSkillDto skillDto)
    {
        skillDtoValidator.validate(skillDto);
        Skill skill = skillService.create(modelMapper.map(skillDto, Skill.class));
        return ResponseEntity.ok(modelMapper.map(skill, CreatedSkillDto.class));
    }

    @Override
    public ResponseEntity<ReadSkillDto> getSkill(UUID id)
    {
        Skill skill = skillService.getByKeyId(id);
        return ResponseEntity.ok(modelMapper.map(skill, ReadSkillDto.class));
    }

    @Override
    public ResponseEntity<UpdatedSkillDto> updateSkill(UUID id, UpdateSkillDto skillDto)
    {
        skillDtoValidator.validate(skillDto);
        Skill skill = skillService.update(id, modelMapper.map(skillDto, Skill.class));
        return ResponseEntity.ok(modelMapper.map(skill, UpdatedSkillDto.class));
    }

    @Override
    public ResponseEntity<Void> deleteSkill(UUID id)
    {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
