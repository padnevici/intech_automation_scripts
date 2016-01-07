package intech.automation.framework.websites.insegment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import intech.automation.framework.Browser;
import intech.automation.framework.Configs;
import intech.automation.framework.enums.InsegmentPages;
import intech.automation.framework.enums.WebSiteNames;

public class ContactUsPage {
	private static final Logger logger = LogManager.getLogger(ContactUsPage.class);

	public void goTo() {
		logger.info(String.format("Navigating to [%s] -> [%s] page", WebSiteNames.Insegment, InsegmentPages.ContactUs));
		Browser.navigate(Configs.getInstance().getPageUrl(WebSiteNames.Insegment, InsegmentPages.ContactUs));
	}

	public boolean isAt() {
		boolean result = Browser.getTitle().trim().toLowerCase()
				.equals(Configs.getInstance().getPageTitle(WebSiteNames.Insegment, InsegmentPages.ContactUs));
		if (result) {
			logger.info(String.format("Title for [%s] -> [%s] page", WebSiteNames.Insegment, InsegmentPages.ContactUs));
			return result;
		}

		logger.info(String.format("Title for [%s] -> [%s] page", WebSiteNames.Insegment, InsegmentPages.ContactUs));
		return false;
	}
}
