package com.zeros.bankkata.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

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
        AtomicReference<BigDecimal> runningBalance = new AtomicReference<>(ZERO);
        return transactions
                    .stream()
                    .map(transaction -> statementLine(transaction, runningBalance))
                    .collect(Collectors.toList());
    }

    private String statementLine(Transaction transaction, AtomicReference<BigDecimal> runningBalance) {
        return "| " + formatDate(transaction.date()) + " | "
                + formatDecimal(transaction.amount()) + " | "
                + formatDecimal(runningBalance.accumulateAndGet(transaction.amount(), (a, b) -> a.add(b))) + "   |\n";
    }

    private String formatDecimal(BigDecimal amount) {
        return decimalFormat.format(amount);
    }

    private String formatDate(LocalDateTime date) {
        return date.toLocalDate().toString();
    }
}
