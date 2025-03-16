import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjectmodel.MainPage;

@RunWith(Parameterized.class)
public class ConstructorTest extends AbstractWebTest {
    String tabToTest;
    String ingredientToBeValidated;
    public ConstructorTest(String tabToTest, String ingredientToBeValidated) {
        this.tabToTest = tabToTest;
        this.ingredientToBeValidated = ingredientToBeValidated;
    }

    @Parameterized.Parameters(name = "Переход на табу {0}")
    public static Object[][] testData(){
        return new Object[][]{
                {"Соусы", "Соус Spicy-X"},
                {"Булки", "Флюоресцентная булка R2-D3"},
                {"Начинки", "Мясо бессмертных моллюсков Protostomia"}

        };
    }

    @Before
    @Override
    @Step("Пререквизиты")
    public void setUp(){
        super.setUp();
    }

    @Test
    public void openTabTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage
                .openMainPage();
        if(tabToTest.equals("Булки")){ // для проверки что переход на дефолтную табу действительно работает, сначала навигируемся на другую табу
            mainPage.clickOnTab("Соусы")
                    .checkCurrentTab("Соусы");
        }
        mainPage
                .clickOnTab(tabToTest)
                .checkCurrentTab(tabToTest)
                .checkIngredientInViewport(ingredientToBeValidated);
    }

    @Override
    @After
    @Step("Восстановление исходного состояния")
    public void tearDown() {
        super.tearDown();
    }
}
