package ku.cs.YakinikuWebsite;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.time.Duration;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class LoginTest {



    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        WebElement usernameField = wait.until(webDriver ->
                webDriver.findElement(By.id("inputUsername")));
    }

    @AfterEach
    public void afterEach() throws InterruptedException {
        Thread.sleep(3000);
    }

    @AfterAll
    public static void afterAll() {
        if (driver != null)
            driver.quit();
    }

    @Test
    void testLogin() {
        WebElement usernameField = wait.until(webDriver ->
                webDriver.findElement(By.id("inputUsername")));

        WebElement passwordField = driver.findElement(By.id("inputPassword"));

        WebElement submitButton = driver.findElement(By.id("submit-button"));


        usernameField.sendKeys("manager");

        passwordField.sendKeys("manager");

        submitButton.click();

    }

}
