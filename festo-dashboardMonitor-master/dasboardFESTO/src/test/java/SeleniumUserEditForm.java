import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUserEditForm {

    /*
    @BeforeTest
    public void testSetupChrome(){
        Selenium.setupChrome();
    }
*/
    @BeforeTest
    public void testSetupFirefox(){
        Selenium.setupFireFox();
        Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
    }

    @Test(priority = 1)
    public void testEditUserNameSurnameFields(){
        Selenium.OpenNewTab(1,"http://developdashboard.azurewebsites.net/users");
        Selenium.EditExistingUser("SeleniumNameTesting","SeleniumSurnameTesting","testing123","testing123");
    }
    @Test(priority = 2)
    public void testEditUserNameSurnameLithuanianLetters(){
        Selenium.OpenNewTab(2,"http://developdashboard.azurewebsites.net/users");
        Selenium.EditExistingUser("Ričardas","Mačiulis","testing123","testing123");
        Assert.assertEquals(Selenium.LatinLetters(),"Only latin letters");
        System.out.println(Selenium.LatinLetters());
    }


/*
@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 */
}
