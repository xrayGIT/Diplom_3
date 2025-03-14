package pageobjectmodel;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class MainPage {
    WebDriver driver;
    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public MainPage openMainPage(){
        driver.get(PAGE_URL);
        return this;
    }
}
