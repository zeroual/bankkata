package features;

import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import com.zeros.bankkata.domain.Printer;
import com.zeros.bankkata.domain.StatementPrinter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;


public class PrintStatementFeature {

    private Clock clock = mock(Clock.class);
    private Account account = new Account(clock);
    private Printer printer = mock(Printer.class);
    private StatementPrinter statementPrinter = new StatementPrinter(printer);

    @Given("^a client deposits \"([^\"]*)\" EUR in his account on \"([^\"]*)\"$")
    public void clientDepositsMoney(double amount, String date) throws Throwable {
        when(clock.now()).thenReturn(dateOf(date));
        account.deposit(amount);
    }

    @Given("^he deposits \"([^\"]*)\" EUR into his account on \"([^\"]*)\"$")
    public void he_deposits_EUR_into_his_account_on(double amount, String date) throws Throwable {
        clientDepositsMoney(amount, date);
    }

    @Given("^he withdraws \"([^\"]*)\" EUR form his account on \"([^\"]*)\"$")
    public void clientWithdrawsMoney(double amount, String date) throws Throwable {
        when(clock.now()).thenReturn(dateOf(date));
        account.withdraw(amount);
    }

    @When("^He print his statement he got this output$")
    public void verifyPrinterOutput(String output) throws Throwable {
        output = output.replace("\r", "");
        statementPrinter.printStatementOf(account);
        verify(printer).printText(output);
    }

    private LocalDateTime dateOf(String stringDate) {
        stringDate += " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(stringDate, formatter);
    }
}
