package testing;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebQuiz {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup(); // This line automatically downloads and sets up the compatible
													// ChromeDriver

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

	@Test
	public void testSubmitQuiz1() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz1.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/quiz1.html");
		driver.findElement(By.id("firstName")).click();
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("test");
		driver.findElement(By.id("lastName")).click();
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("userAutomated");
		driver.findElement(By.name("integer0")).click();
		driver.findElement(By.name("integer1")).click();
		driver.findElement(By.name("integer2")).click();
		driver.findElement(By.name("integer3")).click();
		driver.findElement(By.name("integer4")).click();
		driver.findElement(By.name("integer5")).click();
		driver.findElement(By.name("integer6")).click();
		driver.findElement(By.name("integer7")).click();
		driver.findElement(By.name("integer8")).click();
		acceptNextAlert = true;
		driver.findElement(By.name("integer9")).click();
		driver.findElement(By.xpath("//input[@value='Submit Quiz']")).click();
		// assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to
		// submit the quiz[\\s\\S]$"));
		try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(5000);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
		driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}

	@Test
	public void testSubmitQuiz2() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz2.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/quiz2.html");
		driver.findElement(By.id("firstName")).click();
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("test");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("userAutomated");
		driver.findElement(By.name("integer0")).click();
		driver.findElement(By.name("integer1")).click();
		driver.findElement(By.name("integer2")).click();
		driver.findElement(By.name("integer3")).click();
		driver.findElement(By.name("integer4")).click();
		driver.findElement(By.name("integer5")).click();
		driver.findElement(By.name("integer6")).click();
		driver.findElement(By.name("integer7")).click();
		acceptNextAlert = true;
		driver.findElement(By.name("integer8")).click();
		driver.findElement(By.xpath("//input[@value='Submit Quiz']")).click();
		// assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to
		// submit the quiz[\\s\\S]$"));
		try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(5000);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
		driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}

	@Test
	public void testSubmitQuiz3() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz3.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/quiz3.html");
		driver.findElement(By.id("firstName")).click();
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("firstName")).sendKeys("test");
		driver.findElement(By.id("lastName")).clear();
		driver.findElement(By.id("lastName")).sendKeys("userAutomated");
		driver.findElement(By.name("integer0")).click();
		driver.findElement(By.name("integer1")).click();
		driver.findElement(By.name("integer2")).click();
		driver.findElement(By.name("integer3")).click();
		driver.findElement(By.name("integer4")).click();
		driver.findElement(By.name("integer5")).click();
		driver.findElement(By.name("integer6")).click();
		driver.findElement(By.name("integer7")).click();
		acceptNextAlert = true;
		driver.findElement(By.name("integer8")).click();
		driver.findElement(By.xpath("//input[@value='Submit Quiz']")).click();
		// assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to
		// submit the quiz[\\s\\S]$"));
		try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(5000);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
		driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}

	@Test
	public void testViewQuiz1() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz1.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/quiz1.html");
		driver.findElement(By.xpath("//button[@onclick='redirectToView()']")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizProcessorServlet?action=view&quizName=quiz1");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}

	@Test
	public void testViewQuiz2() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz2.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/quiz2.html");
		driver.findElement(By.xpath("//button[@onclick='redirectToView()']")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizProcessorServlet?action=view&quizName=quiz2");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}

	@Test
	public void testViewQuiz3() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz3.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/quiz3.html");
		driver.findElement(By.xpath("//button[@onclick='redirectToView()']")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizProcessorServlet?action=view&quizName=quiz3");
		driver.findElement(By.xpath("//button[@onclick=\"location.href='QuizLanding.html'\"]")).click();
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}
	
	@Test
	public void testReturnQuiz1() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	    driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz1.html'\"]")).click();
	    acceptNextAlert = true;
	    driver.get("http://localhost:8080/WebQuiz-GroupV/quiz1.html");
	    driver.findElement(By.xpath("//button[@onclick='redirectToQuizLanding()']")).click();
	    //assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to go back to the Quiz Landing page[\\s\\S]$"));
	    try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(500);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
	    driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}
	
	@Test
	public void testReturnQuiz2() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	    driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz2.html'\"]")).click();
	    acceptNextAlert = true;
	    driver.get("http://localhost:8080/WebQuiz-GroupV/quiz2.html");
	    driver.findElement(By.xpath("//button[@onclick='redirectToQuizLanding()']")).click();
	    //assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to go back to the Quiz Landing page[\\s\\S]$"));
	    try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(500);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
	    driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}
	
	@Test
	public void testReturnQuiz3() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	    driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz3.html'\"]")).click();
	    acceptNextAlert = true;
	    driver.get("http://localhost:8080/WebQuiz-GroupV/quiz3.html");
	    driver.findElement(By.xpath("//button[@onclick='redirectToQuizLanding()']")).click();
	    //assertTrue(closeAlertAndGetItsText().matches("^Are you sure you want to go back to the Quiz Landing page[\\s\\S]$"));
	    try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(500);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
	    driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}
	
	@Test
	public void testNoNameQuiz1() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	    driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz1.html'\"]")).click();
	    driver.get("http://localhost:8080/WebQuiz-GroupV/quiz1.html");
	    acceptNextAlert = true;
	    driver.findElement(By.xpath("//input[@value='Submit Quiz']")).click();
	    driver.findElement(By.xpath("//button[@onclick='redirectToQuizLanding()']")).click();
	    try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(500);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
	    driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}
	
	@Test
	public void testNoNameQuiz2() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	    driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz2.html'\"]")).click();
	    driver.get("http://localhost:8080/WebQuiz-GroupV/quiz2.html");
	    acceptNextAlert = true;
	    driver.findElement(By.xpath("//input[@value='Submit Quiz']")).click();
	    driver.findElement(By.xpath("//button[@onclick='redirectToQuizLanding()']")).click();
	    try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(500);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
	    driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	}
	
	@Test
	public void testNoNameQuiz3() throws Exception {
		driver.get("http://localhost:8080/WebQuiz-GroupV/QuizLanding.html");
	    driver.findElement(By.xpath("//button[@onclick=\"location.href='quiz3.html'\"]")).click();
	    driver.get("http://localhost:8080/WebQuiz-GroupV/quiz3.html");
	    acceptNextAlert = true;
	    driver.findElement(By.xpath("//input[@value='Submit Quiz']")).click();
	    driver.findElement(By.xpath("//button[@onclick='redirectToQuizLanding()']")).click();
	    try {
			// Switch to the alert
			Alert alert = driver.switchTo().alert();

			// Accept the alert
			alert.accept();

			Thread.sleep(500);
		} catch (NoAlertPresentException e) {
			// Handle the case where there is no alert present
		}
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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}