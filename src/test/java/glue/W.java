package glue;

import Pages.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class W {
    private static W instance = null;

    public static W get() {
        if (instance == null) {
            instance = new W();
        }
        return instance;
    }

    protected WebDriver driver;

    private W() {
//        String pathToDriver = System.getProperty("webdriver.chrome.driver");
//        if (pathToDriver == null || pathToDriver.isEmpty()) {
//            throw new RuntimeException("define a path to the chrome driver using system property 'webdriver.chrome.driver'");
//        }
//        System.setProperty("webdriver.chrome.driver", pathToDriver);

//        driver = new ChromeDriver();
        driver = DriverFactory.getDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        driver.manage().window().maximize();
    }

    public void search(String value) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(value);
        searchBox.submit();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result-stats")));

    }

//    public static boolean isAboutPageDisplayed() {
//        driver.findElement(By.linkText("About")).click();
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
//    }
//
//    private static void acceptCookiesIfWarned() {
//        try {
//            driver.findElement(By.cssSelector("#L2AGLb")).click();
//        } catch (NoSuchElementException ignored) {
//        }
//    }

    public static void close() {
        if (instance != null) {
            instance.driver.close();
            instance = null;
        }
    }

}
