package com.zeros.bankkata.demo;


import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import com.zeros.bankkata.domain.Printer;
import com.zeros.bankkata.domain.StatementPrinter;

public class BankKataApp {


    public static void main(String[] args) {

        Clock clock = new Clock();

        Account account = new Account(clock);
        account.deposit(12);
        account.deposit(30);
        account.withdraw(20);

        StatementPrinter statementPrinter = new StatementPrinter(new Console());
        statementPrinter.printStatementOf(account);

    }

    private static class Console implements Printer {
        @Override
        public void printText(String output) {
            System.out.println(output);
        }
    }
}
