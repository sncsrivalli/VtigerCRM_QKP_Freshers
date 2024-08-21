package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

/**
 * This class contains elements, locators and respective business libraries of Creating New Contact Page
 * @author sncsr
 *
 */
public class CreatingNewContactPage {

	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;

	@FindBy(name = "lastname")
	private WebElement contactLastNameTF;

	@FindBy(xpath = "//input[contains(@title,'Save')]")
	private WebElement saveBTN;

	@FindBy(xpath = "//img[contains(@onclick,'Accounts')]")
	private WebElement organizationPlusBTN;
	
	private String organizationPath = "//a[text()='%s']";
	
	// Initialization
	public CreatingNewContactPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

	// Utilization

	/**
	 * This method fetches the page header
	 * 
	 * @return String
	 */
	public String getPageHeader() {
		return pageHeader.getText();
	}

	/**
	 * This method sets the organization name into the Organization Name text field
	 * 
	 * @param name
	 */
	public void setContactLastName(String name) {
		contactLastNameTF.sendKeys(name);
	}

	/**
	 * This method clicks on Save button
	 */
	public void clickSaveBTN() {
		saveBTN.click();
	}
	
	/**
	 * This method selects the existing organization from the Organizations window
	 * @param driverUtil
	 * @param orgName
	 */
	public void selectExistingOrganization(WebDriverUtility driverUtil, String orgName) {
		organizationPlusBTN.click();
		driverUtil.switchToWindow("Accounts");
		driverUtil.convertdynamicXpathToWebElement(organizationPath, orgName).click();
		driverUtil.switchToWindow("Contacts");
	}

}
