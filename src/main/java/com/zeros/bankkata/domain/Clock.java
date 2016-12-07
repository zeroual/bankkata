package com.zeros.bankkata.domain;

import java.time.LocalDateTime;

public class Clock {

    public LocalDateTime now() {
        return getNow();
    }

    protected LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
