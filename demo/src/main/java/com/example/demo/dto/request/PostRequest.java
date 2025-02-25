package com.example.demo.dto.request;

import com.example.demo.enums.TypeTimeWork;
import com.example.demo.enums.WagePayments;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder

public class PostRequest {
    String title;
    LocalDate datePost;
    LocalDate dateEnd;
    WagePayments wagePayment;
    TypeTimeWork typeTimeWork;
    int experience;
    BigDecimal wage;
    String idAddress;
}
