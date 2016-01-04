package intech.automation.framework;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase extends TestListenerAdapter {

	protected static Logger logger = null;
	
	@BeforeClass
	public void setUp() {
		Browser.initialize();
		logger = LogManager
				.getLogger(TestBase.class);
	}

	@AfterClass
	public void tearDown() throws IOException {
		System.in.read();
		Browser.quit();
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		logger.error("[Test Failed]: "+ tr.getMethod());
		logger.error(tr.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.info("[Test Skipped]: "+ tr.getMethod());
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger.info("[Test Passed]: "+ tr.getMethod());
	}
}
