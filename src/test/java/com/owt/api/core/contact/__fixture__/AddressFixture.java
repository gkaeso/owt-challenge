package com.owt.api.core.contact.__fixture__;

import com.owt.api.core.contact.Address;

public class AddressFixture
{
    public static Address address()
    {
        return address("street1", "1234", "Geneva");
    }

    public static Address address(String street, String postCode, String city)
    {
        return new Address(street, postCode, city);
    }
}
