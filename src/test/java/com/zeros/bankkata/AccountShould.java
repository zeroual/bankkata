package com.zeros.bankkata;

import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import com.zeros.bankkata.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountShould {

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
        double amountToDeposit = 12;
        account.deposit(amountToDeposit);
        assertThat(account.transactions()).hasSize(1);
        assertThat(account.transactions()).containsExactly(depositTransaction(amountToDeposit, now));
    }

    @Test
    public void saveWithdrawalTransaction() {
        account.deposit(20);
        account.withdraw(12);
        assertThat(account.transactions()).hasSize(2);
        assertThat(account.transactions()).containsExactly(depositTransaction(20, now), withdrawTransaction(12, now));
    }


    @Test
    public void computeTheRunningBalance() {
        account.deposit(12);
        assertThat(account.runningBalance()).isEqualTo(12);
        account.deposit(4);
        assertThat(account.runningBalance()).isEqualTo(16);
        account.withdraw(10);
        assertThat(account.runningBalance()).isEqualTo(6);

    }


    private Transaction depositTransaction(double amount, LocalDateTime date) {
        return new Transaction(amount, date);
    }

    private Transaction withdrawTransaction(double amount, LocalDateTime date) {
        return new Transaction(-amount, date);
    }

    private LocalDateTime dateOf(String stringDate) {
        stringDate += " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(stringDate, formatter);
    }
}
