package com.zeros.bankkata;


public class Transaction {

    private final double amount;

    public Transaction(double amount) {
        this.amount = amount;
    }

    public double amount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return Double.compare(that.amount, amount) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }

}
