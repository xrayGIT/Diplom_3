package pageobjectmodel;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegistrationView{
    WebDriver driver;
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By NAME_INPUT = By.xpath(".//label[text()='Имя']/parent::div/input");
    private static final By EMAIL_INPUT = By.xpath(".//label[text()='Email']/parent::div/input");
    private static final By PASSWORD_INPUT = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private static final By INCORRECT_PASSWORD_MESSAGE = By.xpath("//p[text()='Некорректный пароль']");
    private static final By ENTER_ACCOUNT_LINK = By.xpath(".//a[text()='Войти']");


    public RegistrationView(WebDriver driver){
        this.driver = driver;
    }

    @Step("Зарегистрироваться")
    public LoginView registerNewUser(String name, String email, String pass){
        driver.findElement(NAME_INPUT).sendKeys(name);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(pass);
        driver.findElement(REGISTRATION_BUTTON).click();
        return new LoginView(driver);
    }

    @Step("Проверка. Сообщение о некорректном пароле появилось")
    public RegistrationView checkIncorrectPasswordErrorMessage(){
        boolean isErrorMessageDisplayed = driver.findElement(INCORRECT_PASSWORD_MESSAGE).isDisplayed();
        Assert.assertTrue("Страница входа в аккаунт загружена", isErrorMessageDisplayed);
        return this;
    }

    @Step("Нажать на 'Войти'")
    public LoginView clickEnterAccount(){
        driver.findElement(ENTER_ACCOUNT_LINK).click();
        return new LoginView(driver);
    }
}
