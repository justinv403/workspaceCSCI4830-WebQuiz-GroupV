package testing;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebQuiz {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup(); // This line automatically downloads and sets up the compatible ChromeDriver
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/WebQuiz-GroupV/QuizLanding.html";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("WebDriver initialized");
    }

    @Test
    public void testAboutPage() throws Exception {
    	driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
        driver.findElement(By.xpath("//button[@onclick=\"location.href='AboutPage.html'\"]")).click();
        driver.get("http://localhost:8080/WebQuiz-GroupV/AboutPage.html");
        driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
        driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString().trim();
        if (!verificationErrorString.isEmpty()) {
            fail(verificationErrorString);
        }
        verificationErrors.setLength(0); // Clear verificationErrors buffer
    }

}