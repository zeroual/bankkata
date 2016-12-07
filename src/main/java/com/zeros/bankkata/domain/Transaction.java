package com.zeros.bankkata.domain;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final BigDecimal amount;
    private LocalDateTime date;

    public Transaction(BigDecimal amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public BigDecimal amount() {
        return this.amount;
    }

    public LocalDateTime date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
