package com.owt.api.core.contact.__fixture__;

import com.owt.api.core.contact.Address;
import com.owt.api.core.contact.Contact;

import static com.owt.api.core.BaseEntityFixture.fillBase;
import static com.owt.api.core.contact.__fixture__.AddressFixture.address;

public class ContactFixture
{
    public static Contact contact()
    {
        return contact("john", "doe", "john.doe@mail.com",
                       "0123456789", address());
    }

    public static Contact contact(String firstName, String lastName, String email,
                                  String phoneNumber, Address address)
    {
        return fillBase(new Contact(firstName, lastName, email, phoneNumber, address));
    }
}
