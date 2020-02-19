import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTest {

    @BeforeTest
    public void testSetup(){
        Selenium.setupChrome();
    }

    //Testuojama login ir logout su egzistuojanciu naudotoju
    @Test(priority = 1)
    public void testLoginLogoutExistingUser(){
        Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
        Selenium.NavigateLogout();
    }
    //Testuojama login ir logout su egzistuojanciu naudotoju ivedant bloga slaptazodi
    @Test(priority = 2)
    public void testLoginExistingUserWithWrongPassword(){
        Selenium.OpenNewTab(1,"http://developdashboard.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("login@mail.com","123456");
    }
    //Testuojama login ir logout su neegzistuojanciu naudotoju
    @Test(priority = 3)
    public void testLoginPositiveValuesNonExistUser(){
        Selenium.OpenNewTab(2,"http://developdashboard.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("as@as223.lt","123456789");
    }
    //Testuojama nurodant netinkama email formata
    @Test(priority = 4)
    public void testLoginNegativeValues(){
        Selenium.OpenNewTab(3,"http://developdashboard.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("loginmail","asdasd");

    }
    //Testuojama bandant prisijungt nenurodzius nei emailo nei slaptazodzio
    @Test(priority = 5)
    public void testLoginEmptyValue(){
        Selenium.OpenNewTab(4,"http://developdashboard.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("","");

    }


    @AfterTest
    public void closeTest(){
    // Selenium.quit();
    }

}
