
import client.StellarburgersClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjectmodel.MainPage;
import pageobjectmodel.RegistrationView;

import static helper.Environment.BASE_URL;


public class RegistrationTest extends AbstractWebTest {
    Boolean skipUserDeletion;

    @Before
    @Override
    @Step("Пререквизиты")
    public void setUp(){
        super.setUp();
        skipUserDeletion = false;
        stellarburgersClient = new StellarburgersClient(BASE_URL);
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
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
    @DisplayName("Регистрация нового пользователя с коротким паролем не возможна")
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
            stellarburgersClient.deleteUser(user, 202);
        }
    }

}
