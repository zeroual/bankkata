package com.zeros.bankkata;

import com.zeros.bankkata.domain.Clock;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;


public class ClockShould {

    private LocalDateTime now = parse("2016-12-06T10:15:30");

    @Test
    public void returnNowTime() {
        Clock clock = new controllableClock();
        Assertions.assertThat(clock.now()).isEqualTo(now);
    }

    private class controllableClock extends Clock {
        @Override
        protected LocalDateTime getNow() {
            return now;
        }
    }
}