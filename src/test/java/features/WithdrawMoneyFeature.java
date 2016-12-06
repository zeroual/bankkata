package features;

import com.zeros.bankkata.Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;


public class WithdrawMoneyFeature {

    private Account account = new Account();

    @Given("^an existing client with \"([^\"]*)\" EUR in his account$")
    public void initAccountWith(double amount) throws Throwable {
        account.deposit(amount);
    }

    @When("^he withdraws \"([^\"]*)\" EUR form his account$")
    public void withdrawMoney(double amount) throws Throwable {
        account.withdraw(amount);
    }

    @Then("^his new balance after withdraws is \"([^\"]*)\" EUR$")
    public void verifyRunningBalance(double amount) throws Throwable {
        assertThat(account.runningBalance()).isEqualTo(amount);
    }
}
