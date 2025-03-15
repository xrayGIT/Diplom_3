import browser.WebDriverFactory;
import client.StellarburgersClient;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.restassured.response.ValidatableResponse;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjectmodel.MainPage;
import pageobjectmodel.RegistrationView;

import static helper.Environment.BASE_URL;
import static org.hamcrest.CoreMatchers.equalTo;


public class RegistrationTest extends AbstractWebTest {
    User user;
    StellarburgersClient stellarburgersClient;
    Boolean skipUserDeletion;

    @Before
    @Override
    @Step("Пререквизиты")
    public void setUp(){
        super.setUp();
        skipUserDeletion = false;
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String name = faker.name().firstName();
        user = new User(email, "password", name);
        stellarburgersClient = new StellarburgersClient(BASE_URL);
    }

    @Test
    public void registerNewUserTest(){

        MainPage mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .clickEnterAccountButton()
                .clickRegistrationLink()
                .registerNewUser(user.getName(), user.getEmail(), user.getPassword())
                .checkLoginPageLoaded();
    }

    @Test
    public void registerNewUser_InvalidPassTest(){
        user.setPassword("12345");
        MainPage mainPage = new MainPage(driver);
        RegistrationView registrationView = mainPage
                .openMainPage()
                .clickEnterAccountButton()
                .clickRegistrationLink();
        registrationView
                .registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        registrationView.checkIncorrectPasswordErrorMessage();
        skipUserDeletion = true; // не создали пользователя, нечего удалять
    }

    @Override
    @After
    @Step("Восстановление исходного состояния")
    public void tearDown(){
        super.tearDown();
        if(!skipUserDeletion){
            ValidatableResponse response = stellarburgersClient.loginUser(user, 200);
            String token = response.extract().body().jsonPath().get("accessToken");
            if(token != null){
                stellarburgersClient.deleteUser(token, 202);
            } else {
                Allure.step("Удаление пользователя не возможно. Токен отсутствует, проверьте был ли он сгенерирован на предыдущих шагах", Status.BROKEN);
            }
        }
    }

}
