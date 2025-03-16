package validations;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Validations {
    @Step("Проверка статус кода {1}")
    public static void checkStatus(ValidatableResponse response, int statueER){
        Assert.assertEquals("Code valid",statueER, response.extract().statusCode());
    }

    @Step("Проверка. Элемент отображается внутри окна браузера")
    public static void checkElementInViewport(WebDriver driver, WebElement element){

        boolean isElementInViewport = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(
                        driver1 -> {
                            Rectangle rectangle = element.getRect();
                            Dimension windowSize = driver1.manage().window().getSize();
                            return rectangle.getX() >0
                                    && rectangle.getY() >0
                                    && rectangle.getX() + rectangle.getWidth() <= windowSize.getWidth()
                                    && rectangle.getY() + rectangle.getHeight() <= windowSize.getWidth();
                        }
                );
        Assert.assertTrue("Елемент внутри окна браузера", isElementInViewport);
    }
}
