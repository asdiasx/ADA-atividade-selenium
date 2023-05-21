import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeleniumTest {

    private WebDriver webDriver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://chercher.tech/practice/dynamic-table");
    }

    @AfterEach
    void tearDown() {
        webDriver.quit();
    }

    @Test
    void exercicioSelecionaBlueberryTest() {
        var pathElement = webDriver.findElement(By.xpath("//*[@id=\"blue\"]"));

        pathElement.click();

        var pathOutputElement = webDriver.findElement(By.xpath("//*[@id=\"output\"]"));
        assertEquals("Blueberry", pathOutputElement.getText());
    }

    @Test
    void exercicioSelecionaBananaMinusculasTest() {
        var pathElement = webDriver.findElement(By.xpath("//div[1]/div/div/div/div[1]/div/div[1]/div[4]/button[1]"));

        pathElement.click();

        var pathOutputElement = webDriver.findElement(By.xpath("//*[@id=\"output\"]"));
        assertEquals("banana", pathOutputElement.getText());
    }

    @Test
    void exercicioSelecionaGoogleTest() {
        var checkBoxElements = webDriver.findElements(By.xpath("//div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[1]/input"));
        var rowWebsiteColumnElements = webDriver.findElements(By.xpath("//div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[2]"));
        var desiredElement = rowWebsiteColumnElements.stream()
                .filter(webSite -> webSite.getText().equals("google.com"))
                .findFirst().orElse(null);
        var index = rowWebsiteColumnElements.indexOf(desiredElement);

        checkBoxElements.get(index).click();

        assertTrue(checkBoxElements.get(index).isSelected());
    }

    @Test
    void exercicioSelecionaTodosTest() {
        var checkBoxElements = webDriver.findElements(By.xpath("//div[1]/div/div/div/div[1]/table/tbody/tr[*]/td[1]/input"));

        checkBoxElements.forEach(WebElement::click);

        checkBoxElements.forEach(checkBoxElement -> assertTrue(checkBoxElement.isSelected()));
    }
}
