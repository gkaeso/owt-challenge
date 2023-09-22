package com.owt.api.core.contact.manager;


import javax.persistence.*;

import com.owt.api.core.BaseEntity;
import com.owt.api.core.contact.Contact;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_manager",
       uniqueConstraints = @UniqueConstraint(name = "uq_contact_manager_contact_id_manager_id",
                                             columnNames = {"contact_id", "manager_id"}))
@NoArgsConstructor
public class ContactManager extends BaseEntity
{
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_manager_id_generator")
    @SequenceGenerator(name = "contact_manager_id_generator", sequenceName = "seq_contact_manager_id",
                       allocationSize = 1)
    private long id; // technical id

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_contact_manager_contact_id"))
    private Contact contact;

    @Column(name = "manager_id", nullable = false)
    private String managerId;

    public ContactManager(Contact contact, String managerId)
    {
        this.contact = contact;
        this.managerId = managerId;
    }

    public boolean isManager(String managerId)
    {
        return this.managerId.equals(managerId);
    }
}
