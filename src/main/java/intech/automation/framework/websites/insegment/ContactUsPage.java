package intech.automation.framework.websites.insegment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import intech.automation.framework.Browser;
import intech.automation.framework.Configs;
import intech.automation.framework.Generator;
import intech.automation.framework.enums.InsegmentPages;
import intech.automation.framework.enums.WebSiteNames;

public class ContactUsPage {
	private static final Logger logger = LogManager.getLogger(ContactUsPage.class);

	@FindBy(how = How.ID, using = "c-name")
	protected WebElement yourNameFld;

	@FindBy(how = How.ID, using = "c-company")
	protected WebElement companyFld;

	@FindBy(how = How.ID, using = "c-email")
	protected WebElement emailFld;

	@FindBy(how = How.ID, using = "c-comments")
	protected WebElement messageFld;

	@FindBy(how = How.XPATH, using = "//button[.='Send']")
	protected WebElement sendBtn;

	public void submitForm() {
		logger.debug("Submiting web form");

		Browser.enterInWebElement(yourNameFld, Generator.getRandomName());
		Browser.enterInWebElement(companyFld, Generator.getRandomName());
		Browser.enterInWebElement(emailFld, Generator.getRandomEmail());
		Browser.enterInWebElement(messageFld, Generator.getRandomMessage());
		Browser.clickOnWebElement(sendBtn);
	}

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
