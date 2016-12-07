package com.zeros.bankkata;

import java.time.LocalDateTime;

public class Clock {

    public LocalDateTime now() {
        return getNow();
    }

    protected LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
