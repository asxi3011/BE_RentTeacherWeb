package com.example.demo.dto.response;

import com.example.demo.enums.TypeTimeWork;
import com.example.demo.enums.WagePayments;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseWithUser {
    String title;
    LocalDate datePost;
    LocalDate dateEnd;
    WagePayments wagePayment;
    TypeTimeWork typeTimeWork;
    int experience;
    BigDecimal wage;
    UserCreationResponse user ;
}
