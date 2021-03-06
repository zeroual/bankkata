package features;

import com.zeros.bankkata.domain.Account;
import com.zeros.bankkata.domain.Clock;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


public class WithdrawMoneyFeature {

    private final Clock clock = new Clock();
    private Account account = new Account(clock);

    @Given("^an existing client with \"([^\"]*)\" EUR in his account$")
    public void initAccountWith(BigDecimal amount) throws Throwable {
        account.deposit(amount);
    }

    @When("^he withdraws \"([^\"]*)\" EUR form his account$")
    public void withdrawMoney(BigDecimal amount) throws Throwable {
        account.withdraw(amount);
    }

    @Then("^his new balance after withdraws is \"([^\"]*)\" EUR$")
    public void verifyRunningBalance(BigDecimal amount) throws Throwable {
        assertThat(account.runningBalance()).isEqualTo(amount);
    }
}
