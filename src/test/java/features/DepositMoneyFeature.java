package features;

import com.zeros.bankkata.Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositMoneyFeature {


    private Account account;

    @Given("^a client with \"([^\"]*)\" EUR in his account$")
    public void initialiseAccount(String arg1) throws Throwable {
        account = new Account();
    }

    @When("^he deposits \"([^\"]*)\" EUR into his account$")
    public void depositMoneyIntoAccount(double amount) throws Throwable {
        account.deposit(amount);
    }

    @Then("^his new balance is \"([^\"]*)\" EUR$")
    public void verifyRunningBalance(double amount) throws Throwable {
        assertThat(account.runningBalance()).isEqualTo(amount);
    }


}
