package intech.automation.framework;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {
	private static WebDriver webDriver = null;
	private static Logger logger = null;
	private static String screenshotDirName = "screenshots";

	public static void initialize() {
		configureLog4jXML();
		logger = LogManager.getLogger(Browser.class.getName());

		logger.debug(String.format("Initialising"));
		String browserType = Configs.getInstance().getBrowser().toLowerCase().trim();
		if (browserType.equals("chrome"))
			initChrome();
		else if (browserType.equals("firefox"))
			initFirefox();
		else
			initFirefox();

		createDirForScreenshots();
	}

	private static void configureLog4jXML() {
		String content = null;
		File file = new File("src/resources/log4j2Tmp.xml"); // for ex foo.txt
		try {
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();

			File newTextFile = new File("src/resources/log4j2.xml");

			FileWriter fw = new FileWriter(newTextFile);
			fw.write(content.replace("%EPOCHTIME%", (System.currentTimeMillis() / 1000) + ""));
			fw.close();

		} catch (IOException e) {
			logger.error("Failed to find log4j2Tmp.xml template file", e);
		}
	}

	private static void initChrome() {
		logger.debug(String.format("Starting Chrome browser"));

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions chrome = new ChromeOptions();
		chrome.addArguments("test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, chrome);
		capabilities.setCapability("chrome.binary", "./chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		webDriver = new ChromeDriver(capabilities);
		initWindow();
	}

	private static void initFirefox() {
		logger.debug(String.format("Starting Firefox browser"));
		webDriver = new FirefoxDriver();
		initWindow();
	}

	private static void initWindow() {
		navigate("http://www.google.com");
		webDriver.manage().window().setPosition(new Point(0, 0));
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(Configs.getInstance().getImplicitSeleniumWaitTime(),
				TimeUnit.SECONDS);
	}

	private static void createDirForScreenshots() {

		File theDir = new File(screenshotDirName);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			logger.debug("creating directory: " + screenshotDirName);
			try {
				theDir.mkdir();
			} catch (SecurityException se) {
				logger.fatal("Security error while creating folder for screenshots");
			}
		}
	}

	public static void navigate(String url) {
		logger.info(String.format("Go to [%s] url", url));
		webDriver.navigate().to(url);
		implicitWait();
	}

	public static void quit() {
		logger.debug(String.format("Quiting the browser"));
		webDriver.quit();
	}

	public static void implicitWait() {
		implicitWait(Configs.getInstance().getImplicitWaitTime());
	}

	public static void implicitWait(long milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error("An error during implicit waiting", e);
		}
	}

	public static String getTitle() {
		return webDriver.getTitle();
	}

	public static WebDriver getWebDriver() {
		return webDriver;
	}

	public static void clickOnWebElement(WebElement element) {
		// logger.debug("Clicking on web element");
		element.click();
		implicitWait();
	}

	public static void enterInWebElement(WebElement element, String text) {
//		logger.debug(String.format("Entering following [%s] string", text));
		element.sendKeys(text);
		implicitWait();
	}

	public static boolean checkIfElementExists(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static void executeJavaScript(String jsCode) {
		JavascriptExecutor jsx = (JavascriptExecutor) webDriver;
		jsx.executeScript(jsCode, "");
	}

	public static void takeScreenshot() {
		try {
			WebDriver augmentedDriver = new Augmenter().augment(webDriver);
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			String path = String.format("./%s/%s", screenshotDirName, source.getName());
			FileUtils.copyFile(source, new File(path));
		} catch (Exception e) {
			logger.warn("Failed to capture screenshot: " + e.getMessage());
			logger.warn(e.getStackTrace());
		}
	}
}
