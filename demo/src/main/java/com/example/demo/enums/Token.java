package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.temporal.ChronoUnit;

@Getter
@AllArgsConstructor
public enum Token {
    ACCESS_TOKEN(40,ChronoUnit.SECONDS),
    REFRESH_TOKEN(3,ChronoUnit.DAYS);

    private final int duration;
    private final ChronoUnit unit;

}
