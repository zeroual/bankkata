package features;

import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositMoneyFeature {


    private Account account;
    private Clock clock;

    @Given("^a client with \"([^\"]*)\" EUR in his account$")
    public void initialiseAccount(String arg1) throws Throwable {
        clock = new Clock();
        account = new Account(clock);
    }

    @When("^he deposits \"([^\"]*)\" EUR into his account$")
    public void depositMoneyIntoAccount(BigDecimal amount) throws Throwable {
        account.deposit(amount);
    }

    @Then("^his new balance is \"([^\"]*)\" EUR$")
    public void verifyRunningBalance(BigDecimal amount) throws Throwable {
        assertThat(account.runningBalance()).isEqualTo(amount);
    }


}
