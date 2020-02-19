import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.util.Asserts;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Selenium {
    static WebDriver chromeBrowser;



    public static void main(String[] args){

        System.out.println("Selenium");
    }

    static public void setupChrome(){
        System.setProperty("webdriver.chrome.driver","drivers\\chromedriver.exe");
        chromeBrowser = new ChromeDriver();
        //chromeBrowser.manage().window().maximize();  //Uncomment to Fullscreen browser
        //chromeBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeBrowser.get("http://developdashboard.azurewebsites.net/login");
    }

    static public void setupFireFox(){

    }




    static public void MonitoringDashboardLogin(String email, String password) {

        //Login formos uzpildymas

        //Enter email
        waitForElementById("email");

        WebElement emailInput = chromeBrowser.findElement(By.id("email"));
        emailInput.sendKeys(email);



        //Enter password
        waitForElementById("password");
        WebElement passwordInput = chromeBrowser.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        //Press submit button
        waitForElementByXpath("//button");
        WebElement submitButton = chromeBrowser.findElement(By.xpath("//button"));
        submitButton.click();

    }


    //Atidaro nauja taba narsykleje
    static public void OpenNewTab(int switchNumber, String url){
        ((JavascriptExecutor)chromeBrowser).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(chromeBrowser.getWindowHandles());
        chromeBrowser.switchTo().window(tabs.get(switchNumber));
        chromeBrowser.get(url);
    }

    //Logoutinimas
    static public void NavigateLogout(){
        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = chromeBrowser.findElement(By.className("menuIcon"));
        burgerMenu.click();
        //Click logout
        waitForElementByXpath("//a[@href='/logout']//button[@class=\"buttonDesign\"]");
        WebElement logoutButton = chromeBrowser.findElement(By.xpath("//a[@href='/logout']//button[@class=\"buttonDesign\"]"));
        logoutButton.click();
    }


    //ElementsToBeClicked
    static private void waitForElementByName(String name){
        WebDriverWait waitas = new WebDriverWait(chromeBrowser, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.name(name)));
    }
    static private void waitForElementByClassName(String name){
        WebDriverWait waitas = new WebDriverWait(chromeBrowser, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.className(name)));
    }
    static private void waitForElementById(String name){
        WebDriverWait waitas = new WebDriverWait(chromeBrowser, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.id(name)));
    }
    static private void waitForElementByXpath(String name){
        WebDriverWait waitas = new WebDriverWait(chromeBrowser,2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.xpath(name)));
    }

    static private void waitForEelementByXpathVisibility(String name){
        WebDriverWait wait = new WebDriverWait(chromeBrowser,2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(name)));
    }

    static private void waitForElementPresenceByClass(String name ){
        WebDriverWait wait = new WebDriverWait(chromeBrowser,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(name)));
    }

    public static void close(){
        chromeBrowser.close();
    }

    public static void quit(){
        chromeBrowser.quit();
    }
}
