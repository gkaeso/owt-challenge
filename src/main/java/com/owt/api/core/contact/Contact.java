package com.owt.api.core.contact;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.owt.api.core.BaseEntity;
import com.owt.api.core.skill.Skill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact",
       uniqueConstraints = {
               @UniqueConstraint(name = "uq_contact_email", columnNames = "email")
       })
@NoArgsConstructor
public class Contact extends BaseEntity
{
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_generator")
    @SequenceGenerator(name = "contact_id_generator", sequenceName = "seq_contact_id", allocationSize = 1)
    private long id; // technical id

    @Getter
    @Setter
    @Column(nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    @Embedded
    private Address address;

    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contact_skill_assoc",
               joinColumns = @JoinColumn(nullable = false,
                                         name = "contact_id",
                                         foreignKey = @ForeignKey(name = "fk_contact_skill_assoc_contact_id")),
               inverseJoinColumns = @JoinColumn(nullable = false,
                                                name = "skill_id",
                                                foreignKey = @ForeignKey(name = "contact_skill_assoc_skill_id")))
    private final Set<Skill> skills = new HashSet<>();

    public Contact(String firstName, String lastName, String email, String phoneNumber, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void updateSkills(Set<Skill> skills)
    {
        this.skills.clear();
        this.skills.addAll(skills);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Contact object)
            return super.equals(object);
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id);
    }
}
