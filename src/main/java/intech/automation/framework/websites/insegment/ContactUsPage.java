package intech.automation.framework.websites.insegment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import intech.automation.framework.Browser;
import intech.automation.framework.Configs;


public class ContactUsPage {
	private static final Logger logger = LogManager
			.getLogger(ContactUsPage.class);

	public void goTo() {
		logger.info(String.format("Navigating to [%s] page", this.getClass().getName()));
		Browser.navigate(Configs.getInstance().getUrl());
	}

	public boolean isAt() {
		boolean result = Browser.getTitle().trim().toLowerCase()
				.equals("Contact Us - inSegment Boston Digital Marketing Agency | InSegment");
		if (result) {
			logger.info(String.format("Title for [%s] is correct", this.getClass().getName()));
			return result;
		}

		logger.info(String.format("Title for [%s] page is not correct", this.getClass().getName()));
		return false;
	}
}
