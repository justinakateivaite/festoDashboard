import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumLoginTest {
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

    //Testuojama login ir logout su egzistuojanciu naudotoju
    @Test(priority = 1)
    public void testLoginLogoutExistingUser(){
        Selenium.MonitoringDashboardLogin("login@mail.com","asd123");
        Selenium.NavigateLogout();
    }
    //Testuojama login su egzistuojanciu naudotoju ivedant bloga slaptazodi
    @Test(priority = 2)
    public void testLoginExistingUserWithWrongPassword(){
        Selenium.OpenNewTab(1,"http://developdashboard.azurewebsites.net/login");
       // Selenium.OpenNewTab(1,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("login@mail.com","123456");
        Assert.assertEquals(Selenium.userInvalidEmailOrPassword(),"Invalid email or password","Missing Validation message");
        System.out.println(Selenium.userInvalidEmailOrPassword());
    }
    //Testuojama login su neegzistuojanciu naudotoju
    @Test(priority = 3)
    public void testLoginPositiveValuesNonExistUser(){
       Selenium.OpenNewTab(2,"http://developdashboard.azurewebsites.net/login");
       // Selenium.OpenNewTab(2,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("as@as223.lt","123456789");
        Assert.assertEquals(Selenium.userWrongEmailOrPassword(),"Invalid email or password","Missing Validation message");
        System.out.println(Selenium.userWrongEmailOrPassword());
    }
    //Testuojama nurodant netinkama email formata
    @Test(priority = 4)
    public void testLoginNegativeValues(){
        Selenium.OpenNewTab(3,"http://developdashboard.azurewebsites.net/login");
       // Selenium.OpenNewTab(3,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("loginmail","asdasd");
        Assert.assertEquals(Selenium.userEmailInvalidMessage(),"Invalid email address","Missing Validation message");
        System.out.println(Selenium.userEmailInvalidMessage());

    }
    //Testuojama nurodant netinkama email formata
    @Test(priority = 5)
    public void testLoginNegativeValues2(){
        Selenium.OpenNewTab(4,"http://developdashboard.azurewebsites.net/login");
       // Selenium.OpenNewTab(4,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("test@.com","asdasd");
        Assert.assertEquals(Selenium.userEmailInvalidMessage(),"Invalid email address","Missing Validation message");
        System.out.println(Selenium.userEmailInvalidMessage());

    }

    //Testuojama nurodant netinkama email formata
    @Test(priority = 6)
    public void testLoginNegativeValues3(){
        Selenium.OpenNewTab(5,"http://developdashboard.azurewebsites.net/login");
       // Selenium.OpenNewTab(5,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("test@","asdasd");
        Assert.assertEquals(Selenium.userEmailInvalidMessage(),"Invalid email address","Missing Validation message");
        System.out.println(Selenium.userEmailInvalidMessage());

    }

    //Testuojama bandant prisijungt nenurodzius nei emailo nei slaptazodzio
    @Test(priority = 7)
    public void testLoginEmptyValue(){
        Selenium.OpenNewTab(6,"http://developdashboard.azurewebsites.net/login");
       // Selenium.OpenNewTab(6,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("","");

    }

    @Test(priority = 8)
    public void testLoginUserIsDisabled(){
       Selenium.OpenNewTab(7,"http://developdashboard.azurewebsites.net/login");
        //Selenium.OpenNewTab(7,"http://dashboardfront.azurewebsites.net/login");
        Selenium.MonitoringDashboardLogin("login2@mail.com","asd123");
        Assert.assertEquals(Selenium.UserDisabledErrorMessage(),"user is disabled","Missing Validation message");
        System.out.println(Selenium.DeleteUserValidationMessage());
    }


    @AfterTest
    public void closeTest(){
    // Selenium.quit();
    }

}
