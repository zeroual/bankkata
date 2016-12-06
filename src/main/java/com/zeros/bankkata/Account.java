package com.zeros.bankkata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Account {

    private final Clock clock;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(Clock clock) {
        this.clock = clock;
    }

    public void deposit(double amount) {
        this.transactions.add(new Transaction(amount, now()));
    }

    private LocalDateTime now() {
        return clock.now();
    }

    public void withdraw(double amount) {
        this.transactions.add(new Transaction(-amount, now()));
    }

    public double runningBalance() {
        return transactions.stream()
                .mapToDouble(Transaction::amount)
                .sum();
    }

    public List<Transaction> transactions() {
        return unmodifiableList(this.transactions);
    }
}
