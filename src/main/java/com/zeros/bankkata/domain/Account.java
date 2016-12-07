package com.zeros.bankkata.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Collections.unmodifiableList;

public class Account {

    private final Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(Clock clock) {
        this.clock = clock;
    }

    public void deposit(BigDecimal amount) {
        this.transactions.add(new Transaction(amount, now()));
    }

    public void withdraw(BigDecimal amount) {
        this.transactions.add(new Transaction(amount.negate(), now()));
    }

    public BigDecimal runningBalance() {
        return transactions.stream()
                .map(Transaction::amount)
                .reduce(ZERO, BigDecimal::add);
    }

    public List<Transaction> transactions() {
        return unmodifiableList(this.transactions);
    }

    private LocalDateTime now() {
        return clock.now();
    }
}
