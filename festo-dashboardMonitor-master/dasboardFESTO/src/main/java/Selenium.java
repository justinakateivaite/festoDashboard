
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.util.Asserts;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Selenium {
    static WebDriver BrowserDriver;


    public static void main(String[] args){

        System.out.println("Selenium");

    }
/*
    static public void setupChrome(){
        WebDriverManager.chromedriver().setup();
        BrowserDriver = new ChromeDriver();
        BrowserDriver.get("http://developdashboard.azurewebsites.net/login");
    }
    */



    static public void setupFireFox(){
            WebDriverManager.firefoxdriver().setup();
            BrowserDriver = new FirefoxDriver();
            BrowserDriver.get("http://developdashboard3.azurewebsites.net/login");

    }



    //Login formos uzpildymas
    static public void MonitoringDashboardLogin(String email, String password) {

        //Enter email
        waitForElementById("email");

        WebElement emailInput = BrowserDriver.findElement(By.id("email"));
        emailInput.sendKeys(email);
        String validationMessage = emailInput.getAttribute("validationMessage");

        boolean valid = (Boolean)((JavascriptExecutor)BrowserDriver).executeScript("return arguments[0].validity.valid;", emailInput);
        if(!valid ){
            System.out.println(validationMessage);
        }

        //Enter password
        waitForElementById("password");
        WebElement passwordInput = BrowserDriver.findElement(By.id("password"));
        passwordInput.sendKeys(password);

        //Press submit button
        waitForElementByXpath("//button");
        WebElement submitButton = BrowserDriver.findElement(By.xpath("//button"));
        submitButton.click();

    }


    //Atidaro nauja taba narsykleje
    static public void OpenNewTab(int switchNumber, String url){
        ((JavascriptExecutor)BrowserDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(BrowserDriver.getWindowHandles());
        BrowserDriver.switchTo().window(tabs.get(switchNumber));
        BrowserDriver.get(url);
    }

    //Logoutinimas
    static public void NavigateLogout(){
        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = BrowserDriver.findElement(By.className("menuIcon"));
        burgerMenu.click();
        //Click logout
        waitForElementByXpath("//a[@href='/logout']//button[@class=\"buttonDesign\"]");
        WebElement logoutButton = BrowserDriver.findElement(By.xpath("//a[@href='/logout']//button[@class=\"buttonDesign\"]"));
        logoutButton.click();
    }


    static public void AddNewUser(String email, String name, String surname, String password, String repeatPassword){

        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = BrowserDriver.findElement(By.className("menuIcon"));
        burgerMenu.click();

        //Click users
        waitForElementByXpath("//a[@href='/users']//button[@class=\"buttonDesign\"]");
        WebElement users = BrowserDriver.findElement(By.xpath("//a[@href='/users']//button[@class=\"buttonDesign\"]"));
        users.click();
        BrowserDriver.navigate().refresh();
        //Click add new user
        waitForElementByXpath("//div[@class='addButton d-flex justify-content-between']/button[text() = 'Add new user']");
        WebElement addUserButton = BrowserDriver.findElement((By.xpath("//div[@class='addButton d-flex justify-content-between']/button[text() = 'Add new user']")));
        addUserButton.click();

        //Enter email
        waitForElementByName("email");
        WebElement writeEmail = BrowserDriver.findElement(By.name("email"));
        writeEmail.sendKeys(email);

        //Enter name
        waitForElementByName("name");
        WebElement writeName = BrowserDriver.findElement(By.name("name"));
        writeName.sendKeys(name);

        //Enter Surname
        waitForElementByName("surname");
        WebElement writeSurname = BrowserDriver.findElement(By.name("surname"));
        writeSurname.sendKeys(surname);

        //Enter Password
        waitForElementByName("password0");
        WebElement writePassword0 = BrowserDriver.findElement(By.name("password0"));
        writePassword0.sendKeys(password);

        //Enter repeatPassword
        waitForElementByName("password");
        WebElement writePassword = BrowserDriver.findElement(By.name("password"));
        writePassword.sendKeys(repeatPassword);

        //Mark checkbox
        //Enter repeatPassword
        waitForElementByName("isactive");
        WebElement isActiveCheckbox = BrowserDriver.findElement(By.name("isactive"));
        isActiveCheckbox.click();

        //Enter Submit form
        waitForElementByXpath("//button[@type='submit']");
        WebElement submitAddForm = BrowserDriver.findElement((By.xpath("//button[@type='submit']")));
        submitAddForm.click();
    }

    static public void EditRecentlyCreatedUser(String name,String surname, String password, String repeatPassword){
        //Click Edit recently created user
    	JavascriptExecutor js = (JavascriptExecutor) BrowserDriver;
        waitForElementByXpath("//tr[td[div[text() = \"test@aktyvus.lt\"]]]/td/button[text() = 'edit']");
        WebElement editUser = BrowserDriver.findElement(By.xpath("//tr[td[div[text() = 'test@aktyvus.lt']]]/td/button[text() = 'edit']"));
      //This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", editUser);
        editUser.click();


        //Enter name
        waitForElementByName("name");
        WebElement enterName = BrowserDriver.findElement(By.name("name"));
        enterName.clear();
        enterName.sendKeys(name);

        //Enter surname
        waitForElementByName("surname");
        WebElement enterSurname = BrowserDriver.findElement(By.name("surname"));
        enterSurname.clear();
        enterSurname.sendKeys(surname);


        //Entering password
        //Enter Password
        waitForElementByName("password0");
        WebElement writePassword0 = BrowserDriver.findElement(By.name("password0"));
        writePassword0.sendKeys(password);

        //Enter repeatPassword
        waitForElementByName("password");
        WebElement writePassword = BrowserDriver.findElement(By.name("password"));
        writePassword.sendKeys(repeatPassword);

        //Enter Submit form
        waitForElementByXpath("//button[@type='submit']");
        WebElement submitAddForm = BrowserDriver.findElement((By.xpath("//button[@type='submit']")));
        submitAddForm.click();

    }

    static public void EditExistingUser(String name,String surname, String password, String repeatPassword){
        //Click Edit recently created user
        waitForElementByXpath("//tr[td[div[text() = \"login@mail.com\"]]]/td/button[text() = 'edit']");
        WebElement deleteUser = BrowserDriver.findElement(By.xpath("//tr[td[div[text() = 'login@mail.com']]]/td/button[text() = 'edit']"));
        deleteUser.click();


        //Enter name
        waitForElementByName("name");
        WebElement enterName = BrowserDriver.findElement(By.name("name"));
        enterName.clear();
        enterName.sendKeys(name);

        //Enter surname
        waitForElementByName("surname");
        WebElement enterSurname = BrowserDriver.findElement(By.name("surname"));
        enterSurname.clear();
        enterSurname.sendKeys(surname);


        //Entering password
        //Enter Password
        waitForElementByName("password0");
        WebElement writePassword0 = BrowserDriver.findElement(By.name("password0"));
        writePassword0.sendKeys(password);

        //Enter repeatPassword
        waitForElementByName("password");
        WebElement writePassword = BrowserDriver.findElement(By.name("password"));
        writePassword.sendKeys(repeatPassword);

        //Enter Submit form
        waitForElementByXpath("//button[@type='submit']");
        WebElement submitAddForm = BrowserDriver.findElement((By.xpath("//button[@type='submit']")));
        submitAddForm.click();

    }

    static public void DeleteUser(){
        //Click burger
        waitForElementByClassName("menuIcon");
        WebElement burgerMenu = BrowserDriver.findElement(By.className("menuIcon"));
        burgerMenu.click();
        //Click users
        waitForElementByXpath("//a[@href='/users']//button[@class=\"buttonDesign\"]");
        WebElement users = BrowserDriver.findElement(By.xpath("//a[@href='/users']//button[@class=\"buttonDesign\"]"));
      
        users.click();
        //Click delete
        JavascriptExecutor js = (JavascriptExecutor) BrowserDriver;
        waitForElementByXpath("//tr[td[div[text() = 'test@aktyvus.lt']]]/td/button[text() = 'delete']");
        WebElement deleteUser = BrowserDriver.findElement(By.xpath("//tr[td[div[text() = 'test@aktyvus.lt']]]/td/button[text() = 'delete']"));
        js.executeScript("arguments[0].scrollIntoView();", deleteUser);
        deleteUser.click();
        //Click Are u sure to delete?
        waitForElementByXpath("//div[@class='ReactModal__Content ReactModal__Content--after-open']//button[@id='delete-user']");
        WebElement confirmDelete = BrowserDriver.findElement(By.xpath("//div[@class='ReactModal__Content ReactModal__Content--after-open']//button[@id='delete-user']"));
        confirmDelete.click();
    }

    static public String javascriptError(){
        waitForEelementByXpathVisibility("//div[@style='font-size: 2em; font-family: sans-serif; color: rgb(206, 17, 38); white-space: pre-wrap; margin: 0px 2rem 0.75rem 0px; flex: 0 0 auto; max-height: 50%; overflow: auto;']");
    String errorMsg = BrowserDriver.findElement(By.xpath("//div[@style='font-size: 2em; font-family: sans-serif; color: rgb(206, 17, 38); white-space: pre-wrap; margin: 0px 2rem 0.75rem 0px; flex: 0 0 auto; max-height: 50%; overflow: auto;']")).getText();
    return errorMsg;
    }

    static public String DeleteUserValidationMessage(){
        waitForEelementByXpathVisibility("//p[@class='text-danger warning-text'][text()='Email already in use']");
        String  errorMsg = BrowserDriver.findElement(By.xpath("//p[@class='text-danger warning-text'][text()='Email already in use']")).getText();
        return  errorMsg;
    }

    static public String UserDisabledErrorMessage(){
        waitForEelementByXpathVisibility("//p[span[text()='User disabled']]");
        String errorMsg = BrowserDriver.findElement(By.xpath("//p[span[text()='User disabled']]")).getText();
        return errorMsg;
    }

    static public String userEmailIncorrectFormat(){
        waitForEelementByXpathVisibility("//p[@id='er']");
        String errorMsg = BrowserDriver.findElement(By.xpath("//p[@id='er']")).getText();
        return errorMsg;
    }

    static public String userIncorrectEmailOrPassword(){
        waitForEelementByXpathVisibility("//p[span[text()='Incorrect email or password']]");
        String errorMsg = BrowserDriver.findElement(By.xpath("//p[span[text()='Incorrect email or password']]")).getText();
        return errorMsg;
    }

    static public String userWrongEmailOrPassword(){
        waitForEelementByXpathVisibility("//p[text()='Incorrect email or password']");
        String errorMsg = BrowserDriver.findElement(By.xpath("//p[text()='Incorrect email or password']")).getText();
        return errorMsg;
    }
    static public String LatinLetters(){
        waitForEelementByXpathVisibility("//p[text()='Only latin letters and numbers']");
        String errorMsg = BrowserDriver.findElement(By.xpath("//p[text()='Only latin letters and numbers']")).getText();
        return errorMsg;
    }


    //UserEdit
    //"//tr[td[text() = 'UserDeleteThis@mail.com']]/td/button"


    //ElementsToBeClicked
    static private void waitForElementByName(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.name(name)));
    }
    static private void waitForElementByClassName(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.className(name)));
    }
    static private void waitForElementById(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver, 2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.id(name)));
    }
    static private void waitForElementByXpath(String name){
        WebDriverWait waitas = new WebDriverWait(BrowserDriver,2);
        waitas.until(ExpectedConditions.elementToBeClickable(By.xpath(name)));
    }

    static private void waitForEelementByXpathVisibility(String name){
        WebDriverWait wait = new WebDriverWait(BrowserDriver,2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(name)));
    }

    static private void waitForElementPresenceByClass(String name ){
        WebDriverWait wait = new WebDriverWait(BrowserDriver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(name)));
    }

    public static void close(){
        BrowserDriver.close();
    }

    public static void quit(){
        BrowserDriver.quit();
    }
}
