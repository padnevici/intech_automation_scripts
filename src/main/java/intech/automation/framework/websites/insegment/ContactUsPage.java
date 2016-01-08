package intech.automation.framework.websites.insegment;

import java.util.ArrayList;
import java.util.List;

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

	@FindBy(how = How.XPATH, using = "//input[contains(@class,'error') and @name='c-name']")
	protected WebElement yourNameErr;
	@FindBy(how = How.XPATH, using = "//input[contains(@class,'error') and @name='c-company']")
	protected WebElement companyErr;
	@FindBy(how = How.XPATH, using = "//input[contains(@class,'error') and @name='c-email']")
	protected WebElement emailErr;
	@FindBy(how = How.XPATH, using = "//textarea[contains(@class,'error')and @name='c-comments']")
	protected WebElement messageErr;

	/////////// Helpers ///////////
	public void clickSendBtn() {
		logger.debug("Clicking on [Send] button");
		Browser.clickOnWebElement(sendBtn);
	}

	public void enterName(String name) {
		logger.debug(String.format("Entering following [%s] into name", name));
		Browser.enterInWebElement(yourNameFld, name);
	}

	public void enterCompany(String companyName) {
		logger.debug(String.format("Entering following [%s] into company", companyName));
		Browser.enterInWebElement(companyFld, companyName);
	}

	public void enterEmail(String email) {
		logger.debug(String.format("Entering following [%s] into email", email));
		Browser.enterInWebElement(emailFld, email);
	}

	public void enterMessage(String message) {
		logger.debug(String.format("Entering following [%s] into message", message));
		Browser.enterInWebElement(messageFld, message);
	}

	public void enterDataInForm() {
		enterName(Generator.getRandomName());
		enterCompany(Generator.getRandomName());
		enterEmail(Generator.getRandomEmail());
		enterMessage(Generator.getRandomMessage());
	}

	public void enterDataInForm(String name, String companyName, String email, String message) {
		enterName(name);
		enterCompany(companyName);
		enterEmail(email);
		enterMessage(message);
	}
	/////////// End of Helpers ///////////

	/////////// Form submission ///////////
	public void submitForm() {
		submitForm(Generator.getRandomName(), Generator.getRandomName(), Generator.getRandomEmail(),
				Generator.getRandomMessage());
	}

	public void submitForm(String name, String companyName, String email, String message) {
		logger.debug("Submiting web form");
		enterDataInForm(name, companyName, email, message);
		clickSendBtn();
	}
	/////////// End of Form submission ///////////

	/////////// Getters ///////////
	public List<String> getFormValues() {
		logger.debug("Getting values from web form");
		List<String> values = new ArrayList<String>();
		values.add(yourNameFld.getText());
		values.add(companyFld.getText());
		values.add(emailFld.getText());
		values.add(messageFld.getText());

		return values;
	}
	/////////// End of Getters ///////////

	/////////// Checkers ///////////
	public Boolean checkIfAllErrorsArePresent() {
		logger.info(String.format("Checking if errors for %s-%s are present", WebSiteNames.Insegment,
				InsegmentPages.ContactUs));

		Boolean result = true;
		result = result & Browser.checkIfElementExists(yourNameErr);
		result = result & Browser.checkIfElementExists(companyErr);
		result = result & Browser.checkIfElementExists(emailErr);
		result = result & Browser.checkIfElementExists(messageErr);

		return result;
	}

	public Boolean checkIfEmailErrorIsPresent() {
		logger.info(String.format("Checking if error for email field on %s-%s is present", WebSiteNames.Insegment,
				InsegmentPages.ContactUs));

		Boolean result = true;
		result = result & Browser.checkIfElementExists(emailErr);

		return result;
	}
	/////////// End of Checkers ///////////

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
