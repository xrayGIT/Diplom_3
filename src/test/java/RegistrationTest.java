import browser.BrowserName;
import browser.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjectmodel.MainPage;


public class RegistrationTest {
    WebDriver driver;
    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver();
    }

    @Test
    public void registerNewUserTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
