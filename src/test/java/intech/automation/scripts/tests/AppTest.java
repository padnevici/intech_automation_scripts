package intech.automation.scripts.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import intech.automation.framework.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestBase {
	@Test
	public void test_1(/*String searchString, String applicationName*/) {
		try {
			Browser.initialize();
		} catch (Exception e) {
			logger.error(e);
			logger.error(e.getStackTrace());
			Assert.fail("An error occured during execution", e);
		}
	}
}
