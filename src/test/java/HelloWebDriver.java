import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {

        // Adding chrome options
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--start-fullscreen");


//        WebDriver driver = new ChromeDriver(co);
//        driver.get("http://seleniumhq.org");
//        driver.findElement(By.className("DocSearch-Button-Placeholder")).click();
//        WebElement searchInput = driver.findElement(By.id("docsearch-input"));
//        searchInput.sendKeys("Selenium java");

        WebDriver driver = new ChromeDriver(co);
        driver.get("http://seleniumhq.org");
        driver.findElement(By.className("DocSearch-Button-Placeholder")).click();
        // findElements method returns a list so we need to fix declaration of variable using List<WebElement> before
        // actual declare
        List<WebElement> searchInput = driver.findElements(By.id("docsearch-input"));
        // .get(0) to select exact list value
        searchInput.get(0).sendKeys("Selenium java");

        Thread.sleep(2000);
        driver.quit();

        driver = new EdgeDriver();
        driver.get("http://seleniumhq.org");
        Thread.sleep(2000);
        driver.quit();

        driver = new FirefoxDriver();
        driver.get("http://seleniumhq.org");
        Thread.sleep(2000);
        driver.quit();

    // General timeouts - all timeouts are set before declaring webdriver, usually waits defined only if we need them
    // Page load using pageLoadTimeout()
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    // Script using setScriptTimeout()
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    // Implicitly wait using implicitlyWait()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    // Explicit wait using expected condition
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("someID")));

// Create unified method to call wait functions just passing parameters
        waitElementLocatedBy(driver, By.id("SomeID"));
    }

    private static void waitElementLocatedBy(WebDriver driver, By by) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
// Example of Custom Condition waiter
//    public class CustomCondition {
//        public static ExpectedConditions<Boolean> jQueryAJAXsCompleted() {
//            return new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver driver) {
//                    return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active == =0; ");
//                }
//            };
//        }
//}
// WHere to use custom condition?
// Then use it as parameter in expected condition as described here, making custom condition to check is there any dynamic content/page fully load
// ????????????????????????????????????????????????
//        new WebDriverWait(driver, 10).until(CustomCondition.jQueryAJAXCompleted());
//
//    Fluent wait
//    with default options
//  Wait<WebDriverWait> wait = new FluentWait<WebDriver>(driver)
//        .withTimeout(Duration.ofSeconds(30))
//        .pollingEvery(5)
//        .ignoring(NoSuchElementException.class);
//   with custom functions
//WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//    public WebElement apply(WebDriver driver) {
//        return driver.findElement(By.id('foo');
//    }
//});


