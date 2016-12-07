package com.zeros.bankkata.domain;


import java.time.LocalDateTime;

public class Transaction {

    private final double amount;
    private LocalDateTime date;

    public Transaction(double amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public double amount() {
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

        if (Double.compare(that.amount, amount) != 0) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(amount);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
