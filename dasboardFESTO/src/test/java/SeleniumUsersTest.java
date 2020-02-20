import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumUsersTest {
/*
    @BeforeTest
    public void testSetupChrome(){
        Selenium.setupChrome();
    }
*/
    @BeforeTest
    public void testSetupFirefox(){
        Selenium.setupFireFox();
    }

// Testuojama user add
@Test(priority = 1)
public void testUserAddPositiveValues(){
    Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
    Selenium.AddNewUser("UserDeleteThis@mail.com","Dovydas","Tamulis","asd123","asd123");
}

//Testuojam user delete
@Test(priority = 2)
public void testUserDelete(){
    Selenium.OpenNewTab(1,"http://developdashboard.azurewebsites.net");
    Selenium.DeleteUser();
}

}
