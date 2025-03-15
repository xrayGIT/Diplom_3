package pageobjectmodel;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPasswordView {
    WebDriver driver;
    private static final By ENTER_ACCOUNT_LINK = By.xpath(".//a[text()='Войти']");
    public ForgotPasswordView(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажать на 'Войти'")
    public LoginView clickEnterAccount(){
        driver.findElement(ENTER_ACCOUNT_LINK).click();
        return new LoginView(driver);
    }

}
