import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUserAddForm {
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

    // Testuojama user add su tuo paciu el.pastu.
    @Test(priority = 2)
    public void testUserAddWithAlreadyRegisteredEmail(){
        Selenium.OpenNewTab(1, "http://developdashboard.azurewebsites.net");
        Selenium.AddNewUser("login@mail.com","tester","testuotojas","aktyvus","aktyvus");
        Assert.assertEquals(Selenium.DeleteUserValidationMessage(),"Email already in use");
        System.out.println(Selenium.DeleteUserValidationMessage());
    }

/*
@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 */

}
