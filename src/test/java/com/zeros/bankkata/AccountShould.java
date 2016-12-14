package com.zeros.bankkata;

import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import com.zeros.bankkata.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountShould {


    public final BigDecimal FOUR = valueOf(4);
    public final BigDecimal SIXTEEN = valueOf(16);
    public final BigDecimal SIX = valueOf(6);
    private final BigDecimal TWELVE = valueOf(12);
    private final BigDecimal TWENTY = valueOf(20);
    private Account account;
    @Mock
    private Clock clock = mock(Clock.class);
    private LocalDateTime now = dateOf("2016-12-06");

    @Before
    public void initialise() {
        account = new Account(clock);
        when(clock.now()).thenReturn(now);
    }

    @Test
    public void saveDepositTransactionInAccount() {
        BigDecimal amountToDeposit = TWELVE;
        account.deposit(amountToDeposit);
        assertThat(account.transactions()).hasSize(1);
        assertThat(account.transactions()).containsExactly(depositTransaction(amountToDeposit, now));
    }

    @Test
    public void saveWithdrawalTransaction() {
        account.deposit(TWENTY);
        account.withdraw(TWELVE);
        assertThat(account.transactions()).hasSize(2);
        assertThat(account.transactions()).containsExactly(depositTransaction(TWENTY, now), withdrawTransaction(TWELVE, now));
    }


    @Test
    public void computeTheRunningBalance() {
        account.deposit(TWELVE);
        assertThat(account.runningBalance()).isEqualTo(TWELVE);
        account.deposit(FOUR);
        assertThat(account.runningBalance()).isEqualTo(SIXTEEN);
        account.withdraw(TEN);
        assertThat(account.runningBalance()).isEqualTo(SIX);

    }


    private Transaction depositTransaction(BigDecimal amount, LocalDateTime date) {
        return new Transaction(amount, date);
    }

    private Transaction withdrawTransaction(BigDecimal amount, LocalDateTime date) {
        return new Transaction(amount.negate(), date);
    }

    private LocalDateTime dateOf(String stringDate) {
        stringDate += " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(stringDate, formatter);
    }
}
