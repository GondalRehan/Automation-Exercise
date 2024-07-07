package glue;

import Pages.About;
import Pages.DriverFactory;
import Pages.Google;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class GoogleSteps {

    Google google = new Google(DriverFactory.getDriver());
    About about = new About(DriverFactory.getDriver());

    @Given("url {string} is launched")
    public void url(String url) {
        google.goTo(url);
    }

    @When("About page is shown")
    public void aboutPageIsShown() {
        Assert.assertTrue(about.isAboutPageDisplayed());
    }

    @Then("page displays {string}")
    public void pageDisplays(String text) {
        Assert.assertTrue(about.hasMissionStatement(text));
    }

    @When("searching for {string}")
    public void searchingFor(String value) {
        //Change to GooglePage
//        Assert.assertTrue(home.search(value));
        google.search(value);
    }

    @Then("results contain {string}")
    public void resultsContain(String value) {
        Assert.assertTrue(google.isResultsContain(value));
    }

    @Then("result stats are displayed")
    public void resultStatsAreDisplayed() {
        Assert.assertTrue(google.isStatsDisplayed());
    }

    @Then("number of {string} is more than {int}")
    public void numberMoreThan(String type, int number) {
        Assert.assertTrue(google.numberIsMoreThan(type, number));
    }
}
