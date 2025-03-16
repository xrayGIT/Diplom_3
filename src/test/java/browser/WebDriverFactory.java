package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*
    * класс-фабрика создает WebDriver для работы с нужным браузером в зависимости от значения system property.
    * для выбора браузера в конфигурации запуска указать -Dbrowser=yandex или -Dbrowser=chrome. По умолчанию исползуется chrome
    *
    * для обеспечения работы яндекс браузера нужно
    *   - указать путь к драйверу в константе YANDEX_CHROME_DRIVER_PATH
    *   - указать путь к браузеру Yandex в константе YANDEX_BROWSER_PATH. (необходимо для поддержки ARM процессоров где используется не нативный драйвер)
 */

public class WebDriverFactory {
    private static final String WEBDRIVER_CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String BROWSER_PROPERTY = "browser";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String YANDEX_BROWSER_PATH = "";
    private static final String YANDEX_CHROME_DRIVER_PATH = "";

    public static WebDriver getWebDriver(){
        BrowserName browserName = getActiveBrowser();
        switch (browserName) {
            case CHROME: // для хром можно не указывать путь до драйвера
                return new ChromeDriver();
            case YANDEX:
                System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, YANDEX_CHROME_DRIVER_PATH);
                ChromeOptions options = new ChromeOptions();
                options.setBinary(YANDEX_BROWSER_PATH);
                return new ChromeDriver(options);
            default: throw new IllegalArgumentException("unsupported browser: " + browserName);
        }
    }

    private static BrowserName getActiveBrowser(){
        String browserName = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER);
        return BrowserName.valueOf(browserName.toUpperCase());
    }
}
