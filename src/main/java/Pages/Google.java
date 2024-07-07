package Pages;

import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Google {

private WebDriver driver;

    public Google(WebDriver driver) {
        this.driver = driver;
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public void goTo(String url){
        driver.get(url);
        acceptCookiesIfWarned();
    }

    public void goToAbout(){
        driver.findElement(By.xpath("//a[text()='About']")).click();
    }

    public boolean search(String value){
        WebElement search = driver.findElement(By.name("q"));
        search.sendKeys(value);
        search.sendKeys(Keys.ENTER);
/*
        WebElement myDynamicElement = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
        driver.navigate().refresh();


        System.out.println("About displaying: "+myDynamicElement.isDisplayed());
//                driver.findElement((By.xpath("//*[@id='result-stats']/text()"))));
        boolean resultStats = driver.findElement((By.id("result-stats"))).isDisplayed();
        System.out.println("Rehan displaying: "+resultStats);

        //================
        // Find the element that displays the number of search results on the first page
        WebElement resultStats = driver.findElement(By.id("result-stats"));

        // Get the text of the resultStats element
        String resultStatsText = resultStats.getText();
        System.out.println(resultStatsText);

        // Extract the number of search results from the resultStats text
        String[] resultStatsParts = resultStatsText.split(" ");
        System.out.println(resultStatsParts);
        String numResultsString = resultStatsParts[1].replace(",", "");
        int numResults = Integer.parseInt(numResultsString);

        // Print the number of search results
        System.out.println("Number of search results on the first page: " + numResults);

        //===========
//        return resultStats.isDisplayed();
        if(numResults>0)
            return true;
        else return false;
    */
        return true;
    }


    public boolean isResultsContain(String expectedText) {
//        By resultStatsLocator = By.xpath("//*[@id='result-stats']");
//        WebElement results = waitForElementWithRetry(resultStatsLocator);

//        WebElement results = driver.findElement(By.xpath("//*[text() ='"+expectedText+"']"));


        (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text() ='"+expectedText+"']")));
        WebElement results = driver.findElement(By.xpath("//h3[normalize-space()='"+expectedText+"']"));
        return results.getText().contains(expectedText);
    }


    public boolean isStatsDisplayed() {

//        driver.navigate().refresh();
//        WebElement myDynamicElement = (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
//        System.out.println(driver.findElement(By.id("result-stats")).getText());
//        System.out.println("Is result-stats displaying: "+myDynamicElement.isDisplayed());
//        WebElement resultStats = driver.findElement(By.id("result-stats"));
        boolean status;
        try {
            WebElement resultStats = (new WebDriverWait(driver, Duration.ofSeconds(20))).until(ExpectedConditions.presenceOfElementLocated((By.id("result-stats"))));
//            WebElement resultStats = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("result-stats")));
            Assert.assertTrue("Result stats not displayed!", resultStats.isDisplayed());
            status=resultStats.isDisplayed();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Page Source: \n" + driver.getPageSource());
            status=false;
            throw e;
        }
//        return resultStats.isDisplayed();
        return status;
    }


    public boolean numberIsMoreThan(String type, int number) {
        WebElement resultStats = driver.findElement(By.id("result-stats"));
        String resultStatsText = resultStats.getText();

        if (type.equals("results")) {
            String resultsText = resultStatsText.split(" ")[1].replace(",", "");
            int resultsCount = Integer.parseInt(resultsText);
            return (resultsCount > number);
        } else if (type.equals("seconds")) {
            String secondsText = resultStatsText.split(" ")[3].replace("(", "").replace(")", "").replace("s", "");
            double secondsCount = Double.parseDouble(secondsText);
            return (secondsCount > number);
        }
        return false;
    }

    public void acceptCookiesIfWarned() {
        try {
            driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    public WebElement waitForElementWithRetry(By locator) {
        int retries = 3; // Number of retry attempts
        for (int i = 0; i < retries; i++) {
            try {
//                (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='result-stats']")));
                return (new WebDriverWait(driver, Duration.ofSeconds(15))).until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (StaleElementReferenceException e) {
                // If the element is stale, wait and then retry
                try {
                    Thread.sleep(1000); // Wait for 1 second before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted while waiting to retry", ie);
                }
            }
        }
        // After retrying, if still failing, throw the exception
        throw new StaleElementReferenceException("Element not found after retries: " + locator);
    }

//    public WebElement waitForElement(final By findBy, final int waitTime) {
//        Wait<WebDriver> wait = new FluentWait<>((WebDriver) driver)
//                .withTimeout(waitTime, TimeUnit.SECONDS)
//                .pollingEvery(POLL_TIME, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class,StaleElementReferenceException.class);
//
//        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver driver) {
//                System.out.println("Trying to find element " + findBy.toString());
//                WebElement element = driver.findElement(findBy);
//                return element;
//            }
//        });
//        return webElement;
//    }

}
