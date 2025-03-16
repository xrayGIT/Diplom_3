package pageobjectmodel;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class LoginView{
    WebDriver driver;
    private static final By SIGN_IN_LINK = By.className("Auth_link__1fOlj");
    private static final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/parent::div/input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private static final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");

    public LoginView(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажать на 'Зарегистрироваться'")
    public RegistrationView clickRegistrationLink(){
        driver.findElement(SIGN_IN_LINK).click();
        return new RegistrationView(driver);
    }

    @Step("Логин под пользователем {0}")
    public MainPage login(String email, String pass){
        fillEmail(email);
        fillPassword(pass);
        clickEnter();
        return new MainPage(driver);
    }

    @Step("Проверить что страница для входа в аккаунт загружена")
    public LoginView checkLoginPageLoaded(){
        WebElement enterButton = driver.findElement(ENTER_BUTTON);
        Assert.assertTrue("Страница входа в аккаунт загружена", enterButton.isDisplayed());
        return this;
    }

    @Step("Заполнить email {0}")
    private void fillEmail(String email){
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    @Step("Заполнить password {0}")
    private void fillPassword(String pass){
        driver.findElement(PASSWORD_INPUT).sendKeys(pass);
    }

    @Step("Нажать войти")
    private void clickEnter(){
        driver.findElement(ENTER_BUTTON).click();
    }
}
