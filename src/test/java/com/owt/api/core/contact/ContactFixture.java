package com.owt.api.core.contact;

import static com.owt.api.core.BaseEntityFixture.fillBase;
import static com.owt.api.core.contact.AddressFixture.address;

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
