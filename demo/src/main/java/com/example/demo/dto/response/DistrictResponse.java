package com.example.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DistrictResponse {
    Integer id;
    String name;
    String divisionType;
    String codename;
}
