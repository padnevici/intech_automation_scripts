package intech.automation.scripts.formvalidation;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import intech.automation.framework.Browser;
import intech.automation.framework.WebSites;
import intech.automation.framework.enums.InsegmentPages;
import intech.automation.framework.enums.WebSiteNames;
import intech.automation.framework.websites.insegment.ContactUsPage;

public class FormValidation {
	private static final Logger logger = LogManager.getLogger(ContactUsPage.class);

	public static class Insegment {
		public static void checkAll() {
			Boolean result = checkOnContactUs();

			Assert.assertTrue(result,
					String.format("Some web forms from [%s] got some errors. Please check log file for more details",
							WebSiteNames.Insegment));
		}

		private static Boolean checkOnContactUs() {
			Boolean result = true;
			Boolean resultTmp = true;

			WebSites.Insegment.getContactUs().goTo();

			// Check all required fields
			WebSites.Insegment.getContactUs().clickSendBtn();
			Browser.implicitWait(3000);
			resultTmp = WebSites.Insegment.getContactUs().checkIfAllErrorsArePresent();
			if (resultTmp == false) {
				logger.error(String.format("Required fields are not highlighted", WebSiteNames.Insegment,
						InsegmentPages.ContactUs));
				Browser.takeScreenshot();
				result = result & resultTmp;
			} else
				logger.info(String.format("Required fields are highlighted", WebSiteNames.Insegment,
						InsegmentPages.ContactUs));

			// check if required fields disappeared
			WebSites.Insegment.getContactUs().clickSendBtn();
			WebSites.Insegment.getContactUs().enterDataInForm();
			resultTmp = WebSites.Insegment.getContactUs().checkIfAllErrorsArePresent();
			List<String> values = WebSites.Insegment.getContactUs().getFormValues();
			resultTmp = resultTmp & values.contains("");
			if (resultTmp) {
				logger.error(String.format("Required fields are not de-highlighted", WebSiteNames.Insegment,
						InsegmentPages.ContactUs));
				Browser.takeScreenshot();
				result = result & resultTmp;
			} else
				logger.info(String.format("Required fields are de-highlighted", WebSiteNames.Insegment,
						InsegmentPages.ContactUs));

			// check if email field is checked correct
			WebSites.Insegment.getContactUs().enterEmail("test@test");
			WebSites.Insegment.getContactUs().clickSendBtn();
			Browser.implicitWait(3000);
			resultTmp = WebSites.Insegment.getContactUs().checkIfEmailErrorIsPresent();
			if (resultTmp == false) {
				logger.error(String.format("Email field is not highlighted", WebSiteNames.Insegment,
						InsegmentPages.ContactUs));
				Browser.takeScreenshot();
				result = result & resultTmp;
			} else
				logger.info(
						String.format("Email field is highlighted", WebSiteNames.Insegment, InsegmentPages.ContactUs));

			// check if form is submitted
			WebSites.Insegment.getContactUs().submitForm();
			values = WebSites.Insegment.getContactUs().getFormValues();
			resultTmp = true;
			for (String value : values)
				if (value != "")
					continue;
				else
					resultTmp = false;
			
			if (resultTmp == false) {
				logger.error(String.format("Form was not submitted", WebSiteNames.Insegment,
						InsegmentPages.ContactUs));
				Browser.takeScreenshot();
				result = result & resultTmp;
			} else
				logger.info(
						String.format("Form was submitted", WebSiteNames.Insegment, InsegmentPages.ContactUs));
			
			return result;
		}
	}

}
