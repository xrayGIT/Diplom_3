package pageobjectmodel;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static helper.Environment.PAGE_URL;

public class MainPage{
    WebDriver driver;

    private static final By ENTER_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");
    private static final By CREATE_ORDER_BUTTON = By.xpath("//button[text()='Оформить заказ']");
    private static final By ENTER_PERSONAL_ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");
    private static final By PREPARE_BURGER_HEADER = By.xpath(".//h1[text()='Соберите бургер']");


    public MainPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Открыть стартовую страницу")
    public MainPage openMainPage(){
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Открыть страницу регистрации")
    public RegistrationView openRegisterPage(){
        driver.get(PAGE_URL + "register");
        return new RegistrationView(driver);
    }

    @Step("Открыть страницу восстановления забытого пароля")
    public ForgotPasswordView openForgotPasswordPage(){
        driver.get(PAGE_URL + "forgot-password");
        return new ForgotPasswordView(driver);
    }

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public LoginView clickEnterAccountButton(){
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
        return new LoginView(driver);
    }

    @Step("Нажать на кнопку 'Войти в личный кабинет'")
    public ProfileView clickEnterPersonalAccountButton(){
        driver.findElement(ENTER_PERSONAL_ACCOUNT_BUTTON).click();
        return new ProfileView(driver);
    }

    @Step("Проверка. Кнопка 'Оформить заказ' появилась")
    public MainPage checkOrderButtonIsDisplayed(){
        boolean isButtonDisplayed = driver.findElement(CREATE_ORDER_BUTTON).isDisplayed();
        Assert.assertTrue("Кнопка 'Оформить заказ' отображается", isButtonDisplayed);
        return this;
    }

    @Step("Проверка. Главная страница с конструктором открыта")
    public MainPage checkMainPageOpened(){
        boolean isHeaderShown = driver.findElement(PREPARE_BURGER_HEADER).isDisplayed();
        Assert.assertTrue("Заголовок 'Соберите бургер' отображается", isHeaderShown);
        boolean isURLcorrect = Objects.equals(driver.getCurrentUrl(), PAGE_URL);
        Assert.assertTrue("URL страницы профиля /account/profile", isURLcorrect);
        return this;
    }

}
