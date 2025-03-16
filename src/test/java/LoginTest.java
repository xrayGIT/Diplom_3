
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjectmodel.LoginView;
import pageobjectmodel.MainPage;


@RunWith(Parameterized.class)
public class LoginTest extends AbstractWebTest {

    String startPage;
    String buttonToLogin;
    public LoginTest(String startPage, String buttonToLogin) {
        this.startPage = startPage;
        this.buttonToLogin = buttonToLogin;
    }

    /*
        * Стартовая точка: главная страница, /register, /forgot-password
        * Кнопка для нажатия: ("Личный кабнет", "Войти в аккаунт"), ("Войти"), ("Войти")
        * .//*[text()='Войти']
     */

    @Parameterized.Parameters(name = "Со страницы {0} через кнопку {1}")
    public static Object[][] testData(){
        return new Object[][]{
                {"MainPage", "Личный кабинет"},
                {"MainPage", "Войти в аккаунт"},
                {"ForgotPasswordView", "Войти"},
                {"RegistrationView", "Войти"}

        };
    }
    @Before
    @Override
    @Step("Пререквизиты")
    public void setUp(){
        super.setUp();
        stellarburgersClient.createUser(user, 200);
    }

    @Test
    public void loginUserTest(){
        MainPage mainPage = new MainPage(driver);

        switch (startPage){ // сознательно не стал переносить логику с разными кнопками в MainPage по аналогии с 4 спринтом,
                            // потому что кнопка для личного кабинета может приводить к разным действиям.
                            // При дальнейшем расширении кейсов не хорошо иметь неоднозначное поведение из-за возвращаемого типа страницы
                            // в целом я не вижу целесообразности в параметризованом тесте конкретно в этом задании ради 4-х кейсов. Сложность реализации и читабельность снижаются, оверинжениринг.
                            // добавил параметризацию потому что говорили об этом на вебинаре.
            case "MainPage":
                mainPage = mainPage.openMainPage();
                if(buttonToLogin.equals("Личный кабинет")){ mainPage.clickEnterAccountButton(); }
                else if (buttonToLogin.equals("Войти в аккаунт")) { mainPage.clickEnterPersonalAccountButton(); }
                break;
            case "ForgotPasswordView":
                mainPage.openForgotPasswordPage().clickEnterAccount();
                break;
            case "RegistrationView":
                mainPage.openRegisterPage().clickEnterAccount();
        }
        LoginView loginView = new LoginView(driver);
        loginView
                .login(user.getEmail(), user.getPassword())
                .checkOrderButtonIsDisplayed();
    }

    @Override
    @After
    @Step("Восстановление исходного состояния")
    public void tearDown() {
        super.tearDown();
        stellarburgersClient.deleteUser(user, 202);
    }
}
