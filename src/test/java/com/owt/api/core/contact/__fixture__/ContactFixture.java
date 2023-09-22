package com.owt.api.core.contact.__fixture__;

import java.util.Set;

import com.owt.api.core.contact.Address;
import com.owt.api.core.contact.Contact;
import com.owt.api.core.skill.Skill;

import static com.owt.api.core.BaseEntityFixture.fillBase;
import static com.owt.api.core.contact.__fixture__.AddressFixture.address;
import static com.owt.api.core.skill.__fixture__.SkillFixture.skill;

public class ContactFixture
{
    public static Contact contact()
    {
        return contact("john", "doe", "john.doe@mail.com",
                       "0123456789", address(), Set.of(skill(), skill()));
    }

    public static Contact contact(String firstName, String lastName, String email,
                                  String phoneNumber, Address address, Set<Skill> skills)
    {
        Contact contact = fillBase(new Contact(firstName, lastName, email, phoneNumber, address));
        contact.updateSkills(skills);
        return contact;
    }
}
