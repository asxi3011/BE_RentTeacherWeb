package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UniversityRequest {

    Integer id;

    String code ;

    String name;

    String alias;
}
