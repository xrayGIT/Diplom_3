package pageobjectmodel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static helper.Environment.PAGE_URL;

public class MainPage {
    WebDriver driver;

    private static final By ENTER_ACCOUNT_BUTTON = By.xpath(".//button[text()='Войти в аккаунт']");

    public MainPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Открыть стартовую страницу")
    public MainPage openMainPage(){
        driver.get(PAGE_URL);
        return this;
    }

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public LoginView clickEnterAccountButton(){
        driver.findElement(ENTER_ACCOUNT_BUTTON).click();
        return new LoginView(driver);
    }


}
