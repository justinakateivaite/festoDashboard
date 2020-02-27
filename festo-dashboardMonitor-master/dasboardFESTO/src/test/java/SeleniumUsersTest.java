import org.testng.Assert;
import org.testng.annotations.AfterTest;
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
    Selenium.AddNewUser("test@aktyvus.lt","Jonas","Jonaitis","aktyvus","aktyvus");
}


@Test(priority = 2)
public void testEditRecentlyCreatedUser(){
        Selenium.OpenNewTab(1,"http://developdashboard3.azurewebsites.net/users");
        Selenium.EditRecentlyCreatedUser("NameTest","SurnameTest","testing123","testing123");
}
//Testuojam user delete

@Test(priority = 3)
public void testUserDelete(){
    Selenium.OpenNewTab(2,"http://developdashboard3.azurewebsites.net");
    Selenium.DeleteUser();
    Selenium.NavigateLogout();

}

/*
@AfterTest
    public void closeTest(){
        Selenium.quit();
}
 */

}
