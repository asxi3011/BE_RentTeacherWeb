package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DistrictRequest {
    Integer id;
    String name;
    String divisionType;
    String codename;

}
