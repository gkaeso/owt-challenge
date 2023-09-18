package com.owt.api.core.dto;

import com.owt.api.core.model.dto.AddressDto;

class AddressDtoFixture
{
    public static AddressDto addressDto()
    {
        return new AddressDto().street("street1")
                               .postCode("1234")
                               .city("Geneva");
    }
}
