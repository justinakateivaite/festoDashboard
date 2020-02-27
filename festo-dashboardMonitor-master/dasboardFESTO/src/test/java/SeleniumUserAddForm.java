import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUserAddForm {
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

    // Testuojama user add su tuo paciu el.pastu.
    @Test(priority = 2)
    public void testUserAddWithAlreadyRegisteredEmail(){
        String errorMsg = "Email already in use";
        Selenium.OpenNewTab(1, "http://developdashboard3.azurewebsites.net");
        Selenium.AddNewUser("login@mail.com","tester","testuotojas","aktyvus","aktyvus");
        Assert.assertEquals(Selenium.DeleteUserValidationMessage(),errorMsg);
        System.out.println(Selenium.DeleteUserValidationMessage());


    }

/*
@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 */

}
