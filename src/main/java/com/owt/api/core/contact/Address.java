package com.owt.api.core.contact;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address
{
    @Getter
    @Setter
    @Column(name = "address_street")
    private String street;

    @Getter
    @Setter
    @Column(name = "address_post_code")
    private String postCode;

    @Getter
    @Setter
    @Column(name = "address_city")
    private String city;
}
