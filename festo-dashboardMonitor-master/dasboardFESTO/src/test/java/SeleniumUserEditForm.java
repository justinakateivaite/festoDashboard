import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUserEditForm {
/*
    @BeforeTest
    public void testSetupChrome(){
        Selenium.setupChrome();
         Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
    }
*/

    @BeforeTest
    public void testSetupFirefox(){
        Selenium.setupFireFox();
        Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
    }


    @Test(priority = 1)
    public void testEditUserNameSurnameLithuanianLetters() {
        String errorMsg = "Only latin letters and numbers";
        Selenium.OpenNewTab(1, "http://developdashboard3.azurewebsites.net/users");
        Selenium.EditExistingUser("Ričardas", "Mačiulis", "asd123", "asd123");
        Assert.assertEquals(Selenium.LatinLetters(), "Only latin letters and numbers");
        System.out.println(Selenium.LatinLetters());

        if(Selenium.LatinLetters().equals(errorMsg)){
            Assert.fail();
        }


    }

    @Test(priority = 2)
    public void testEditUserNameSurnameFields(){
        Selenium.OpenNewTab(2,"http://developdashboard3.azurewebsites.net/users");
        Selenium.EditExistingUser("SeleniumNameTesting","SeleniumSurnameTesting","asd123","asd123");
    }


/*
@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 */
}
