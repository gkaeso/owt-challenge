package com.owt.api.core.skill;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>
{
    Optional<Skill> findByKeyId(UUID keyId);

    void deleteByKeyId(UUID keyId);
}
