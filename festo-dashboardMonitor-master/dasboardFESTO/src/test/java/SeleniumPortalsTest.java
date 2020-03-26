import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Random;

public class SeleniumPortalsTest {
    static String email = "jk@jk.lt";
    static String password = "HjklHjkl2@";
    static Random random = new Random();
    static String createName = "TestPortalCreate" + random.nextInt(99999);
    static String editName = "TestPortalEdit" + random.nextInt(99999);

    @BeforeTest
    public void setup() {
        Selenium.setupChrome();
        Selenium.navigateToLogin();
        Selenium.loginWithCredentials(email, password);
        Selenium.waitForElementByXpath("/html/body/div/div/header/div/div/div/div[4]/button");
    }

    // Testuojamas portalo sukūrimas
    @Test(priority = 1)
    public void testPortalCreate() {
        // Atidaromas portalų sąrašas
        Selenium.navigateToMaintainingList();
        // Atidaroma portalo sukūrimo forma
        Selenium.clickButtonByXpath("/html/body/div[1]/div/main/div/div[1]/button");
        Selenium.waitForElementPresenceByClass("add-edit-form");
        // Užpildomi portalo sukūrimo formos laukai
        Selenium.fillTextInputByName("name", createName);
        Selenium.fillTextInputByName("url", "https://gitana.lt/" + createName);
        Selenium.selectOptionByName("type", 0);
        Selenium.fillTextInputByName("email", createName + "jk@jk.lt");
        Selenium.fillTextInputByName("checkInterval", "10");
        Selenium.fillTextInputByName("responseTimeThreshold", "1000");
        Selenium.markCheckboxByName("isActive", true);
        // Nu'scroll'inama iki OK mygtuko
        Selenium.scrollToElementByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        // Paspaudžiamas OK mygtukas
        Selenium.clickButtonByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        // Laukiama kol lentelėje atsiras įrašas su nauju portalu
        Selenium.waitForElementPresenceByXpath("//tr//td[text() = '" + createName + "']");
        // Tikrinama ar yra įrašas su tikėtinu pavadinimu
        Assert.assertEquals(Selenium.existElementByXpath("//tr//td[text() = '" + createName + "']"), true);
    }

    // Testuojamas portalo redagavimas
    @Test(priority = 2)
    public void testPortalEdit() {
        // Atidaromas portalų sąrašas
        Selenium.navigateToMaintainingList();
        // Palaukiama kol bus užkrautas anksčiau sukurtas elementas
        Selenium.waitForElementByXpath("//tr[td[text() = '" + createName + "']]/td/button[text() = 'Edit']");
        // Langas paslenkamas iki anksčiau sukurto elemento EDIT mygtuko
        Selenium.scrollToElementByXpath("//tr[td[text() = '" + createName + "']]/td/button[text() = 'Edit']");
        // Paspaudžiamas mygtukas EDIT
        Selenium.clickButtonByXpath("//tr[td[text() = '" + createName + "']]/td/button[text() = 'Edit']");

        // Užpildoma nauja portalo informacija
        Selenium.waitForElementPresenceByClass("add-edit-form");
        Selenium.fillTextInputByName("name", editName);
        Selenium.fillTextInputByName("url", "https://gitana.lt/" + editName);
        Selenium.selectOptionByName("type", 0);
        Selenium.fillTextInputByName("email", editName + "jk@jk.lt");
        Selenium.fillTextInputByName("checkInterval", "20");
        Selenium.fillTextInputByName("responseTimeThreshold", "2000");
        Selenium.markCheckboxByName("isActive", false);
        // Nu'scroll'inama iki OK mygtuko
        Selenium.scrollToElementByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        // Paspaudžiamas OK mygtukas
        Selenium.clickButtonByXpath("//form[@class='add-edit-form']//button[@type='submit']");
        // Sulaukiama pakeisto portalo atsiradimo lentelėje
        Selenium.waitForElementPresenceByXpath("//tr//td[text() = '" + editName + "']");

        // Validuojama, kad portalas buvo pakeistas. Patikrinama ar sąraše atsirado naujas elementas ir dingo senas
        Assert.assertEquals(Selenium.existElementByXpath("//tr//td[text() = '" + editName + "']"), true);
        Assert.assertEquals(Selenium.existElementByXpath("//tr//td[text() = '" + createName + "']"), false);
    }

    // Testuojamas portalo ištrynimas
    @Test(priority = 3)
    public void testPortalDelete() {
        // Atidaromas portalų sąrašas
        Selenium.navigateToMaintainingList();
        // Palaukiama kol bus užkrautas elementas, kuris buvo pakeistas.
        Selenium.waitForElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Nu'scroll'inama iki mygtuko DELETE
        Selenium.scrollToElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Paspaudžiamas mygtukas DELETE
        Selenium.clickButtonByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Palaukiama kol trynimo pop-up'e bus užkrautas mygtukas DELETE
        Selenium.waitForElementPresenceByXpath("//div[@class='delete-modal']//button[text() = 'Delete']");
        // Paspaudžiamas mygtukas DELETE
        Selenium.clickButtonByXpath("//div[@class='delete-modal']//button[text() = 'Delete']");
        // Palaukiama 500ms
        Selenium.pause(500);
        // Palaukiama kol užsikraus puslapis su pakeitimais
        Selenium.waitForPageToLoad();

        // Validuojama, kad portalas buvo ištrintas. Patikrinama ar nebėra seno įrašo
        Assert.assertEquals(Selenium.existElementByXpath("//tr//td[text() = '" + editName + "']"), false);
    }

    // Testuojamas portalo atkūrimas iš RecycleBin
    @Test(priority = 4)
    public void testPortalRestoreFromRecycleBin() {
        // Atidaroma portalų sąraše esanti šiukšliadėžė
        Selenium.navigateToMaintainingListRecycleBin();
        // Sulaukiama kol bus užkrautas mygtukas RESTORE
        Selenium.waitForElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Restore']");
        // Nu'scroll'inama iki mygtuko RESTORE
        Selenium.scrollToElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Restore']");
        // Paspaudžiamas mygtukas RESTORE
        Selenium.clickButtonByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Restore']");
        // Palaukiama 500ms
        Selenium.pause(500);
        // Palaukiama kol u=sikraus puslapis su pakeitimais
        Selenium.waitForPageToLoad();
        // Atidaromas portalų sąrašas
        Selenium.navigateToMaintainingList();
        // Sulaukiama grąžinto iš šiukšliadėžės elemento
        Selenium.waitForElementPresenceByXpath("//tr//td[text() = '" + editName + "']");

        // Validuojama, kad portalas buvo atkurtas. Patikrinama ar sąraše atsirado grąžintas portalas
        Assert.assertEquals(Selenium.existElementByXpath("//tr//td[text() = '" + editName + "']"), true);
    }

    // Testuojamas portalo ištrynimas iš RecycleBin
    @Test(priority = 5)
    public void testPortalDeleteFromRecycleBin() {
        // Portalo ištrynimas (perkėlimas į šiukšliadėžę)
        // Atidaromas portalų sąrašas
        Selenium.navigateToMaintainingList();
        // Laukiama, kol bus užkrautas elementas
        Selenium.waitForElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Nu'scroll'inama iki pasirinkto elemento DELETE mygtuko
        Selenium.scrollToElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Paspaudžiamas mygtukas DELETE
        Selenium.clickButtonByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Palaukiama kol bus užkrautas DELETE mygtukas pop-up'e
        Selenium.waitForElementPresenceByXpath("//div[@class='delete-modal']//button[text() = 'Delete']");
        // Paspaudžiamas DELETE mygtukas
        Selenium.clickButtonByXpath("//div[@class='delete-modal']//button[text() = 'Delete']");
        // Palaukiama 500ms
        Selenium.pause(500);
        // Palaukiama, kol bus užkrautas puslapis
        Selenium.waitForPageToLoad();

        // Portalo ištrynimas (iš šiukšliadėžės)
        // Atidaromas šiukšliadėžėje esantis ištrintų portalų sąrašas
        Selenium.navigateToMaintainingListRecycleBin();
        // Sulaukiama kol bus užkrautas elementas
        Selenium.waitForElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Nu'scroll'inama iki DELETE mygtuko
        Selenium.scrollToElementByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Paspaudžiamas DELETE mygtukas
        Selenium.clickButtonByXpath("//tr[td[text() = '" + editName + "']]/td/button[text() = 'Delete']");
        // Sulaukiama negrįžtamo ištrynimo patvirtinimo pop-up'o
        Selenium.waitForElementPresenceByXpath("//div[@class='delete-modal']//button[text() = 'Permanently Delete']");
        // Paspaudžiamas mygtukas PERMANENTLY DELETE
        Selenium.clickButtonByXpath("//div[@class='delete-modal']//button[text() = 'Permanently Delete']");
        // Palaukiama 500ms
        Selenium.pause(500);
        // Palaukiama, kol bus užkrautas puslapis
        Selenium.waitForPageToLoad();

        // Validuojama, kad portalas buvo ištrintas. Patikrinama ar sąraše nėra portalo
        Assert.assertEquals(Selenium.existElementByXpath("//tr//td[text() = '" + editName + "']"), false);
    }

    // Testuojamas portalo sukūrimo response
    @Test(priority = 6)
    public void testPortalTestResponse() {
        // Atidaromas portalų sąrašas
        Selenium.navigateToMaintainingList();
        // Paspaudžiamas portalo sukūrimo formos mygtukas
        Selenium.clickButtonByXpath("/html/body/div[1]/div/main/div/div[1]/button");
        // Sulaukiama kol bus atidaryta portalo sukūrimo forma
        Selenium.waitForElementPresenceByClass("add-edit-form");
        // Užpildomi portalo sukūrimo formos laukai
        Selenium.fillTextInputByName("name", createName);
        Selenium.fillTextInputByName("url", "https://google.com/");
        Selenium.selectOptionByName("type", 0);
        Selenium.fillTextInputByName("email", createName + "jk@jk.lt");
        Selenium.fillTextInputByName("checkInterval", "10");
        Selenium.fillTextInputByName("responseTimeThreshold", "1000");
        Selenium.markCheckboxByName("isActive", true);
        // Nu'scroll'inama iki Test mygtuko
        Selenium.scrollToElementByXpath("//form[@class='add-edit-form']//button[text()='Test']");
        // Paspaudžiamas Test mygtukas
        Selenium.clickButtonByXpath("//form[@class='add-edit-form']//button[text() = 'Test']");
        // Palaukiama kol atsiras TEST info pop-up'as su elementu kurio tekste yra STATUS CODE
        Selenium.waitForElementPresenceByXpath("//div[@class='test-modal']//p[contains(text(),'Status code')]");
        // Nuskaitomas tekstas elemento, kuriame yra tekstas STATUS CODE
        String text = Selenium.getElementTextByXpath("//div[@class='test-modal']//p[contains(text(),'Status code')]");
        // Tikrinama ar status code 200
        Assert.assertEquals(text.contains("200"), true);
    }

    @AfterClass
    public void quit() {
        Selenium.logout();
        Selenium.quit();
    }
}
