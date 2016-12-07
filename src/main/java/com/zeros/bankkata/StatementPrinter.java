package com.zeros.bankkata;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class StatementPrinter {
    public static final String STATEMENT_HEADER = "| date       | amount | balance |\n";
    private Printer printer;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public StatementPrinter(Printer printer) {
        this.printer = printer;
    }

    public void printStatementOf(Account account) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(STATEMENT_HEADER);

        List<String> statementLines = renderStatementLines(account.transactions());

        for (int i = statementLines.size() - 1; i >= 0; i--) {
            stringBuilder.append(statementLines.get(i));
        }
        printer.printText(stringBuilder.toString());
    }

    private List<String> renderStatementLines(List<Transaction> transactions) {
        AtomicReference<Double> runningBalance = new AtomicReference<>(new Double(0));
        return transactions
                    .stream()
                    .map(transaction -> statementLine(transaction, runningBalance))
                    .collect(Collectors.toList());
    }

    private String statementLine(Transaction transaction, AtomicReference<Double> runningBalance) {
        return "| " + formatDate(transaction.date()) + " | "
                + formatDecimal(transaction.amount()) + " | "
                + formatDecimal(runningBalance.accumulateAndGet(transaction.amount(), (a, b) -> a + b)) + "   |\n";
    }

    private String formatDecimal(double amount) {
        return decimalFormat.format(amount);
    }

    private String formatDate(LocalDateTime date) {
        return date.toLocalDate().toString();
    }
}
