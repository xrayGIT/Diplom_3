package pageobjectmodel;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

import static helper.Environment.PAGE_URL;

public class ProfileView {
    WebDriver driver;
    private static final By PROFILE_BUTTON = By.xpath(".//a[text()='Профиль']");
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text()='Конструктор']");
    private static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");
    private static final By LOGO = By.className("AppHeader_header__logo__2D0X2");

    public ProfileView(WebDriver driver){
        this.driver = driver;
    }

    @Step("Проверить что страница профиля загружена")
    public ProfileView checkProfilePageLoaded(){
        WebElement profileButton = driver.findElement(PROFILE_BUTTON);
        Assert.assertTrue("Страница входа в аккаунт загружена", profileButton.isDisplayed());
        boolean isURLcorrect = Objects.equals(driver.getCurrentUrl(), PAGE_URL + "account/profile");
        Assert.assertTrue("URL страницы профиля /account/profile", isURLcorrect);
        return this;
    }

    @Step("Клик по Logo")
    public MainPage clickOnLogo(){
        driver.findElement(LOGO).click();
        return new MainPage(driver);
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickOnConstructorButton(){
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        return new MainPage(driver);
    }

    @Step("Клик по кнопке 'Выход'")
    public LoginView clickOnExitButton(){
        driver.findElement(EXIT_BUTTON).click();
        return new LoginView(driver);
    }
}
