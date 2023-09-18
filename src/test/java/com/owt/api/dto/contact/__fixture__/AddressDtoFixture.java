package com.owt.api.dto.contact.__fixture__;

import com.owt.api.core.model.dto.AddressDto;

public class AddressDtoFixture
{
    public static AddressDto addressDto()
    {
        return new AddressDto().street("street1")
                               .postCode("1234")
                               .city("Geneva");
    }
}
