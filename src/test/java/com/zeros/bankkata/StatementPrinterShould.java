package com.zeros.bankkata;

import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import com.zeros.bankkata.domain.Printer;
import com.zeros.bankkata.domain.StatementPrinter;
import org.junit.Before;
import org.junit.Test;

import static java.time.LocalDateTime.parse;
import static org.mockito.Mockito.*;

public class StatementPrinterShould {

    private Printer printer = mock(Printer.class);
    private Clock clock = mock(Clock.class);
    private StatementPrinter statementPrinter = new StatementPrinter(printer);
    private Account account = new Account(clock);

    @Before
    public void initialise() {
        when(clock.now()).thenReturn(parse("2016-12-06T00:00:00"));
    }

    @Test
    public void printAlwaysStatementHeader() {
        statementPrinter.printStatementOf(account);
        verify(printer).printText("| date       | amount | balance |\n");
    }

    @Test
    public void printAllTransactions() {
        account.deposit(12);
        account.deposit(20);
        account.withdraw(15);
        String output =
                "| date       | amount | balance |\n" +
                "| 2016-12-06 | -15,00 | 17,00   |\n" +
                "| 2016-12-06 | 20,00 | 32,00   |\n" +
                "| 2016-12-06 | 12,00 | 12,00   |\n";
        statementPrinter.printStatementOf(account);
        verify(printer).printText(output);
    }
}