package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class About {

    By missionStatement = By.xpath("//*[@id='page-content']/section[1]");
    By pageTitle = By.xpath("Google - About Google, our culture and company news");


    private WebDriver driver;

    public About(WebDriver driver) {
        this.driver = driver;
    }

    public String getAboutPageTitle() {
        return driver.getTitle();
    }

    public boolean isPageDisplayed() {
        return getAboutPageTitle().contains("About Google");
    }

    public boolean hasMissionStatement(String value) {
        WebElement body = driver.findElement(By.tagName("body"));
        return body.getText().contains(value);
    }

    public boolean isAboutPageDisplayed() {
        driver.findElement(By.linkText("About")).click();
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement bodyElement = w.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            return bodyElement != null;
        } catch (Exception e) {
            return false;
        }
    }
}
