package com.owt.api.core.skill;

import java.util.Objects;

import javax.persistence.*;

import com.owt.api.core.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skill",
       uniqueConstraints = {
               @UniqueConstraint(name = "uq_skill_name_level", columnNames = {"name", "level"})
       })
@NoArgsConstructor
public class Skill extends BaseEntity
{
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_id_generator")
    @SequenceGenerator(name = "skill_id_generator", sequenceName = "seq_skill_id", allocationSize = 1)
    private long id; // technical id

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    public Skill(String name, Level level)
    {
        this.name = name;
        this.level = level;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Skill object)
            return super.equals(object);
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id);
    }
}
