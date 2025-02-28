package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class IntrospectResponse {
    boolean valid;
    String accessToken;
    Boolean isNewToken;
}
