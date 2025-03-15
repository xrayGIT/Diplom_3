import browser.WebDriverFactory;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class AbstractWebTest {
    WebDriver driver;
    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
