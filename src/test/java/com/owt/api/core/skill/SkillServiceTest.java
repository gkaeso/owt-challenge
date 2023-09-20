package com.owt.api.core.skill;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.owt.api.exception.ResourceNotFoundException;

import static com.owt.api.core.skill.__fixture__.SkillFixture.skill;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest
{
    @Mock
    SkillRepository skillRepository;

    @InjectMocks
    SkillService skillService;

    @Test
    void getByKeyId_whenValidKeyId_thenReturnsSkill()
    {
        // given
        Skill skill = skill();
        when(skillRepository.findByKeyId(skill.getKeyId())).thenReturn(Optional.of(skill));

        // when
        Skill returnedSkill = skillService.getByKeyId(skill.getKeyId());

        // then
        assertThat(returnedSkill).isNotNull()
                                 .isEqualTo(skill);
    }

    @Test
    void getByKeyId_whenInvalidKeyId_thenThrows()
    {
        // given
        UUID keyId = UUID.randomUUID();
        when(skillRepository.findByKeyId(keyId)).thenReturn(Optional.empty());

        // when / then
        assertThatExceptionOfType(ResourceNotFoundException.class).isThrownBy(() -> skillService.getByKeyId(keyId));
    }

    @Test
    void update_whenValid_thenReturnsUpdatedContact()
    {
        // given
        Skill skill = skill("programming", Level.JUNIOR);
        Skill skillWithNewValues = skill("programming", Level.SENIOR);
        when(skillRepository.findByKeyId(skill.getKeyId())).thenReturn(Optional.of(skill));
        when(skillRepository.save(any(Skill.class))).then(returnsFirstArg());

        // when
        Skill updatedSkill = skillService.update(skill.getKeyId(), skillWithNewValues);

        // then
        assertThat(updatedSkill).isNotNull()
                                .isEqualTo(skill);
        assertThat(updatedSkill.getKeyId()).isNotNull()
                                           .isEqualTo(skill.getKeyId()); // has not been modified
        assertThat(updatedSkill.getName()).isNotNull()
                                          .isNotEmpty()
                                          .isEqualTo(skillWithNewValues.getName());
    }
}