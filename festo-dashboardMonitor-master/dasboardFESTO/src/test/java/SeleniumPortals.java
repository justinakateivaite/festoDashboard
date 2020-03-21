import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

public class SeleniumPortals {
    static String email = "jk@jk.lt";
    static String password = "HjklHjkl2@";

    @BeforeTest
    public void setup() {
        Selenium.setupChrome();
    }

    // Testuojamas portalo sukūrimas
    @Test
    public void testPortalCreate() {
        Selenium.navigateToLogin();
        Selenium.loginWithCredentials(email, password);
        Selenium.waitForElementByXpath("/html/body/div/div/header/div/div/div/div[4]/button");
        openAddNewPortal();
        String name = "Test001Testing";
        Selenium.fillTextInputByName("name", name);
        Selenium.fillTextInputByName("url", "https://gitana.lt");
        Selenium.selectOptionByName("type", 0);
//        Selenium.selectOptionByName("method", 0);
//        Selenium.markCheckboxByName("basicAuth", false);
//        Selenium.fillTextInputByName("callParameters", "");
        Selenium.fillTextInputByName("email", "jk@jk.lt");
        Selenium.fillTextInputByName("checkInterval", "10");
        Selenium.fillTextInputByName("responseTimeThreshold", "1000");
        Selenium.markCheckboxByName("isActive", true);
        Selenium.scrollToElementByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        Selenium.clickButtonByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        Selenium.waitForElementByXpath("/html/body/div[1]/div/main//table//tr//td//div[contains(text(), '" + name + "')]");
        Assert.assertEquals(Selenium.BrowserDriver.getPageSource().contains(name), true);
    }

    private void openAddNewPortal() {
        Selenium.navigateToMaintainingList();
        Selenium.clickButtonByXpath("/html/body/div[1]/div/main/div/div[1]/button");
        Selenium.waitForElementPresenceByClass("add-edit-form");
    }

    @Test
    public void testPortalEdit() {
        Selenium.navigateToLogin();
        Selenium.loginWithCredentials(email, password);
        Selenium.waitForElementByXpath("/html/body/div/div/header/div/div/div/div[4]/button");
        openAddNewPortal();
        String name = "Test002Testing";
        Selenium.fillTextInputByName("name", name);
        Selenium.fillTextInputByName("url", "https://gitana.lt");
        Selenium.selectOptionByName("type", 0);
        Selenium.fillTextInputByName("email", "jk@jk.lt");
        Selenium.fillTextInputByName("checkInterval", "10");
        Selenium.fillTextInputByName("responseTimeThreshold", "1000");
        Selenium.markCheckboxByName("isActive", true);
        Selenium.scrollToElementByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        Selenium.clickButtonByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        Selenium.scrollToElementByXpath("/html/body/div[1]/div/main//table//tr//td//div[contains(text(), '" + name + "')]/ancestor::tr/button[@id='edit-portal']");
        Selenium.clickButtonByXpath("/html/body/div[1]/div/main//table//tr//td//div[contains(text(), '" + name + "')]/ancestor::tr/button[@id='edit-portal']");
        Selenium.fillTextInputByName("name", "NameThatName");
    }

    @AfterTest
    public void close() {
        Selenium.logout();
    }

    @AfterClass
    public void quit() {
        Selenium.quit();
    }
}
