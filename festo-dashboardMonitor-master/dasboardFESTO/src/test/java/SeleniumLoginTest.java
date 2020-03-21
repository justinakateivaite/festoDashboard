import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class SeleniumLoginTest {

    @BeforeTest
    public void testSetupChrome(){
        Selenium.setupChromeAndNavigateToLogin();
    }

    //Testuojama login ir logout su egzistuojanciu naudotoju
    @Test(priority = 1)
    public void testLoginLogoutExistingUser(){
        Selenium.loginWithCredentials("jk@jk.lt","hjklhjkl");
        Selenium.NavigateLogout();
    }
    //Testuojama login su egzistuojanciu naudotoju ivedant bloga slaptazodi
    @Test(priority = 2)
    public void testLoginExistingUserWithWrongPassword(){
        Selenium.OpenNewTab(1,"http://developdashboard3.azurewebsites.net/login");
       // Selenium.OpenNewTab(1,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("login@mail.com","123456");
        Assert.assertEquals(Selenium.userIncorrectEmailOrPassword(),"Incorrect email or password","Missing Validation message");
        System.out.println(Selenium.userIncorrectEmailOrPassword());
    }
    //Testuojama login su neegzistuojanciu naudotoju
    @Test(priority = 3)
    public void testLoginPositiveValuesNonExistUser(){
       Selenium.OpenNewTab(2,"http://developdashboard3.azurewebsites.net/login");
       // Selenium.OpenNewTab(2,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("as@as223.lt","123456789");
        Assert.assertEquals(Selenium.userIncorrectEmailOrPassword(),"Incorrect email or password","Missing Validation message");
        System.out.println(Selenium.userIncorrectEmailOrPassword());
    }
    //Testuojama nurodant netinkama email formata
    @Test(priority = 4)
    public void testLoginNegativeValues(){
        Selenium.OpenNewTab(3,"http://developdashboard3.azurewebsites.net/login");
       // Selenium.OpenNewTab(3,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("loginmail","asdasd");
        Assert.assertEquals(Selenium.userEmailIncorrectFormat(),"Invalid email address format","Missing Validation message");
        System.out.println(Selenium.userEmailIncorrectFormat());

    }
    //Testuojama nurodant netinkama email formata
    @Test(priority = 5)
    public void testLoginNegativeValues2(){
        Selenium.OpenNewTab(4,"http://developdashboard3.azurewebsites.net/login");
       // Selenium.OpenNewTab(4,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("test@.com","asdasd");
        Assert.assertEquals(Selenium.userEmailIncorrectFormat(),"Invalid email address format","Missing Validation message");
        System.out.println(Selenium.userEmailIncorrectFormat());

    }

    //Testuojama nurodant netinkama email formata
    @Test(priority = 6)
    public void testLoginNegativeValues3(){
        Selenium.OpenNewTab(5,"http://developdashboard3.azurewebsites.net/login");
       // Selenium.OpenNewTab(5,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("test@","asdasd");
        Assert.assertEquals(Selenium.userEmailIncorrectFormat(),"Invalid email address format","Missing Validation message");
        System.out.println(Selenium.userEmailIncorrectFormat());

    }

    //Testuojama bandant prisijungt nenurodzius nei emailo nei slaptazodzio
    @Test(priority = 7)
    public void testLoginEmptyValue(){
        Selenium.OpenNewTab(6,"http://developdashboard3.azurewebsites.net/login");
       // Selenium.OpenNewTab(6,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("","");

    }

    @Test(priority = 8)
    public void testLoginUserIsDisabled(){
       Selenium.OpenNewTab(7,"http://developdashboard3.azurewebsites.net/login");
        //Selenium.OpenNewTab(7,"http://dashboardfront.azurewebsites.net/login");
        Selenium.loginWithCredentials("login2@mail.com","asd123");
        Assert.assertEquals(Selenium.UserDisabledErrorMessage(),"User disabled","Missing Validation message");
        System.out.println(Selenium.UserDisabledErrorMessage());
    }


    @AfterTest
    public void closeTest(){
    // Selenium.quit();
    }

}
