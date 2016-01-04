package intech.automation.framework;

import org.openqa.selenium.support.PageFactory;
import intech.automation.framework.websites.insegment.*;

public class WebSites {
	public static class Insegment {
		 public static ContactUsPage getPage() {
			 ContactUsPage page = new ContactUsPage();
		 PageFactory.initElements(Browser.getWebDriver(), page);
		 return page;
		 }
	}

	//
	// public static GoogleSearchPage getGoogleSearchPage() {
	// GoogleSearchPage page = new GoogleSearchPage();
	// PageFactory.initElements(Browser.getWebDriver(), page);
	// return page;
	// }
	//
	// public static GooglePlayPage getGooglePlayPage() {
	// GooglePlayPage page = new GooglePlayPage();
	// PageFactory.initElements(Browser.getWebDriver(), page);
	// return page;
	// }
	//
	// public static ApplicationPage getApplicationPage() {
	// ApplicationPage page = new ApplicationPage();
	// PageFactory.initElements(Browser.getWebDriver(), page);
	// return page;
	// }
}
