package com.example.demo.dto.response;

import com.example.demo.entity.District;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityResponse {
    Integer id;
    String name;
    String divisionType;
    String codename;
    Integer phoneCode;
    List<DistrictResponse> districts;
}
