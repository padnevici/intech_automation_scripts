package intech.automation.framework;

import org.openqa.selenium.support.PageFactory;
import intech.automation.framework.websites.insegment.*;

public class WebSites {
	public static class Insegment {
		public static ContactUsPage getContactUs() {
			ContactUsPage page = new ContactUsPage();
			PageFactory.initElements(Browser.getWebDriver(), page);
			return page;
		}
	}
}
