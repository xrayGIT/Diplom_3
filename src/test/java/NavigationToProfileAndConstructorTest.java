import client.StellarburgersClient;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobjectmodel.MainPage;

public class NavigationToProfileAndConstructorTest extends AbstractWebTest {

    @Before
    @Override
    @Step("Пререквизиты")
    public void setUp(){
        super.setUp();
        stellarburgersClient.createUser(user, 200);
    }

    @Test
    @DisplayName("Открытие страницы личного кабинета и конструктора")
    public void openProfileTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .clickEnterAccountButton()
                .login(user.getEmail(), user.getPassword())
                .clickEnterPersonalAccountButton()
                .checkProfilePageLoaded()
                .clickOnLogo().checkMainPageOpened()
                .clickEnterPersonalAccountButton()
                .checkProfilePageLoaded()
                .clickOnConstructorButton().checkMainPageOpened();
    }

    @Override
    @After
    @Step("Восстановление исходного состояния")
    public void tearDown() {
        super.tearDown();
        stellarburgersClient.deleteUser(user, 202);
    }
}
