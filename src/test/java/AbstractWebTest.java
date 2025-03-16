import browser.WebDriverFactory;
import client.StellarburgersClient;
import com.github.javafaker.Faker;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static helper.Environment.BASE_URL;

public abstract class AbstractWebTest {
    WebDriver driver;
    User user;
    StellarburgersClient stellarburgersClient;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver();
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String name = faker.name().firstName();
        user = new User(email, "password", name);
        stellarburgersClient = new StellarburgersClient(BASE_URL);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
