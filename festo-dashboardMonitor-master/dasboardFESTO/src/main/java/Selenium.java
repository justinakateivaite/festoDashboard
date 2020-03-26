
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Selenium {
    static WebDriver browserDriver;
    static int timeOutInSeconds = 2;

    public static void main(String[] args){
        System.out.println("Selenium");
    }

    static public void setupChrome(){
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
        browserDriver = new ChromeDriver();
    }

    static public void navigateToLogin() {
        browserDriver.get("https://developdashboard3.azurewebsites.net/login");
    }

    static public void navigateToMaintainingList() {
        browserDriver.get("https://developdashboard3.azurewebsites.net/maintaining_list");
    }

    static public void navigateToMaintainingListRecycleBin() {
        browserDriver.get("https://developdashboard3.azurewebsites.net/deleted_portals");
    }

    static public void logout() {
        browserDriver.get("https://developdashboard3.azurewebsites.net/logout");
    }

    //Login formos uzpildymas
    static public void loginWithCredentials(String email, String password) {

        //Enter email
        waitForElementById("email");

        WebElement emailInput = browserDriver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        String validationMessage = emailInput.getAttribute("validationMessage");

        boolean valid = (Boolean)((JavascriptExecutor) browserDriver).executeScript("return arguments[0].validity.valid;", emailInput);
        if(!valid ){
            System.out.println(validationMessage);
        }

        //Enter password
        waitForElementById("password");
        WebElement passwordInput = browserDriver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        //Press submit button
        waitForElementByXpath("//button");
        WebElement submitButton = browserDriver.findElement(By.xpath("//button"));
        submitButton.click();
    }

    //ElementsToBeClicked
    static void waitForElementByName(String name) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
    }

    static void waitForElementByClassName(String name) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(name)));
    }

    static void waitForElementById(String name) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(name)));
    }

    static void waitForElementByXpath(String name) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(name)));
    }

    static void waitForElementByXpathVisibility(String name) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(name)));
    }

    static void waitForElementPresenceByClass(String name) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(name)));
    }

    static void waitForElementPresenceByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    static void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(browserDriver, timeOutInSeconds);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    static void pause(Integer milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void fillTextInputByXpath(String elementXpath, String text) {
        waitForElementByXpath(elementXpath);
        WebElement input = browserDriver.findElement(By.xpath(elementXpath));
        input.clear();
        input.sendKeys(text);
    }

    static void fillTextInputByName(String elementName, String text) {
        waitForElementByName(elementName);
        WebElement input = browserDriver.findElement(By.name(elementName));
        input.clear();
        input.sendKeys(text);
    }

    static void selectOptionByXpath(String elementXpath, int index) {
        waitForElementByXpath(elementXpath);
        WebElement element = browserDriver.findElement(By.xpath(elementXpath));
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    static void selectOptionByName(String elementName, int index) {
        waitForElementByName(elementName);
        WebElement element = browserDriver.findElement(By.name(elementName));
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    static void markCheckboxByXpath(String elementXpath, boolean isSelected) {
        waitForElementByXpath(elementXpath);
        WebElement element = browserDriver.findElement(By.xpath(elementXpath));
        if (element.isSelected() != isSelected) {
            element.click();
        }
    }

    static void markCheckboxByName(String elementName, boolean isSelected) {
        waitForElementByName(elementName);
        WebElement element = browserDriver.findElement(By.name(elementName));
        if (element.isSelected() != isSelected) {
            element.click();
        }
    }

    static void clickButtonByXpath(String elementXpath) {
        waitForElementPresenceByXpath(elementXpath);
        WebElement element = browserDriver.findElement(By.xpath(elementXpath));
        element.click();
    }

    static void clickButtonByName(String elementName) {
        waitForElementByName(elementName);
        WebElement element = browserDriver.findElement(By.name(elementName));
        element.click();
    }

    static void scrollToElementByXpath(String elementXpath) {
        waitForElementByXpath(elementXpath);
        WebElement element = browserDriver.findElement(By.xpath(elementXpath));
        JavascriptExecutor js = (JavascriptExecutor) browserDriver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    static String getElementTextByXpath(String elementXpath) {
        WebElement element = browserDriver.findElement(By.xpath(elementXpath));
        return element.getText();
    }

    static boolean existElementByXpath(String elementXpath) {
        try {
            WebElement element = browserDriver.findElement(By.xpath(elementXpath));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void close() {
        browserDriver.close();
    }

    public static void quit() {
        browserDriver.quit();
    }
}
