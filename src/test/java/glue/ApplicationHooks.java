package glue;

import java.util.Properties;

import Pages.DriverFactory;
import org.openqa.selenium.WebDriver;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
//    private ConfigReader configReader;
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
//        ToDo: implement
//        configReader = new ConfigReader();
//        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = "chrome"; //prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(browserName);

    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // ToDo: take screenshot:
        }
    }

}
