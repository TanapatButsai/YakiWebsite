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

public class SignupTest {


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
        driver.get("http://localhost:" + port + "/signup");
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
    void testSignup() {
        WebElement nameField = wait.until(webDriver ->
                webDriver.findElement(By.id("inputName")));

        WebElement usernameField = driver.findElement(By.id("inputUsername"));

        WebElement emailField = driver.findElement(By.id("inputEmail"));

        WebElement passwordField = driver.findElement(By.id("inputPassword"));

        WebElement submitButton = driver.findElement(By.id("submit-button"));

        WebElement addressField = driver.findElement(By.id("inputAddress"));

        WebElement noteField = driver.findElement(By.id("inputNote"));
        WebElement phoneField = driver.findElement(By.id("inputPhone"));

        nameField.sendKeys("BallJed");
        usernameField.sendKeys("ball1");
        emailField.sendKeys("ball9@gmail.com");
        passwordField.sendKeys("123");
        addressField.sendKeys("address01");
        noteField.sendKeys("note01");
        phoneField.sendKeys("0919199991");
        submitButton.click();

    }


}

