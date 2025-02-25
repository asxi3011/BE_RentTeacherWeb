package com.example.demo.dto.response;

import com.example.demo.enums.Country;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    Country country;
    String city;
    int codeCity;
    String district;
    int codeDistrict;
}
