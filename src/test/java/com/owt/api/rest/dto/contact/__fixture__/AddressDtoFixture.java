package com.owt.api.rest.dto.contact.__fixture__;


import com.owt.api.rest.dto.AddressDto;

public class AddressDtoFixture
{
    public static AddressDto addressDto()
    {
        return new AddressDto().street("street1")
                               .postCode("1234")
                               .city("Geneva");
    }
}
