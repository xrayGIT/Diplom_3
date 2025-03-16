
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjectmodel.MainPage;


public class ExitTest extends AbstractWebTest {

    @Before
    @Override
    @Step("Пререквизиты")
    public void setUp(){
        super.setUp();
        stellarburgersClient.createUser(user, 200);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void openProfileTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .clickEnterAccountButton()
                .login(user.getEmail(), user.getPassword())
                .clickEnterPersonalAccountButton()
                .checkProfilePageLoaded()
                .clickOnExitButton()
                .checkLoginPageLoaded();
    }

    @Override
    @After
    @Step("Восстановление исходного состояния")
    public void tearDown() {
        super.tearDown();
        stellarburgersClient.deleteUser(user, 202);
    }
}
