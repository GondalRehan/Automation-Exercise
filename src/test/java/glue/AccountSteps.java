package glue;

import account.Account;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AccountSteps {

    Account account = null;
    private String statement;

    @Given("Account exists for Acc No. {string} with Name {string}")
    public void accountExistsForAccNoWithName(String accountNumber, String name) {
        account = new Account(accountNumber, name);
    }

    @And("deposits are made")
    public void depositsAreMade(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows) {
            String reference = row.get(0);
            double amount = Double.parseDouble(row.get(1));
            account.deposit(reference, amount);
        }
    }

    @And("withdrawls are made")
    public void withdrawalsAreMade(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows) {
            String reference = row.get(0);
            double amount = Double.parseDouble(row.get(1));
            account.withdraw(reference, amount);
        }
    }

    @When("statement is produced")
    public void statementIsProduced() {
        statement = account.generateStatement();
    }

    @Then("statement includes {string}")
    public void statementIncludes(String expectedContent) {
        assertTrue(statement.contains(expectedContent));
    }

}