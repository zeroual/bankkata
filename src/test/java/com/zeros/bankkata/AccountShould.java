package com.zeros.bankkata;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountShould {

    private Account account;

    @Before
    public void initialise() {
        account = new Account();
    }

    @Test
    public void saveDepositTransactionInAccount() {
        double amountToDeposit = 12;
        account.deposit(amountToDeposit);
        assertThat(account.transactions()).hasSize(1);
        assertThat(account.transactions()).containsExactly(depositTransaction(amountToDeposit));
    }

    @Test
    public void computeTheRunningBalance() {
        account.deposit(12);
        assertThat(account.runningBalance()).isEqualTo(12);
        account.deposit(4);
        assertThat(account.runningBalance()).isEqualTo(16);
    }


    private Transaction depositTransaction(double amountToDeposit) {
        return new Transaction(amountToDeposit);
    }
}
