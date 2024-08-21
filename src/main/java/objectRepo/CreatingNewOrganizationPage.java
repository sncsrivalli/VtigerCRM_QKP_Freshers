package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

/**
 * This class contains elements, locators and respective business libraries of Creating New Organization Page
 * @author sncsr
 *
 */
public class CreatingNewOrganizationPage {

	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	
	@FindBy(name = "accountname")
	private WebElement organizationNameTF;
	
	@FindBy(xpath = "//input[contains(@title,'Save')]")
	private WebElement saveBTN;
	
	@FindBy(name="industry")
	private WebElement industryDD;
	
	@FindBy(name="accounttype")
	private WebElement typeDD;
	
	// Initialization
	public CreatingNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	
	/**
	 * This method fetches the page header
	 * @return String
	 */
	public String getPageHeader() {
		return pageHeader.getText();
	}
	
	/**
	 * This method sets the organization name into the Organization Name text field
	 * @param name
	 */
	public void setOrganizationName(String name) {
		organizationNameTF.sendKeys(name);
	}
	
	/**
	 * This method clicks on Save button
	 */
	public void clickSaveBTN() {
		saveBTN.click();
	}
	
	/**
	 * This method is used to select an industry from industry drop down
	 * @param driverUtil
	 * @param value
	 */
	public void selectFromIndustryDD(WebDriverUtility driverUtil, String value) {
		driverUtil.selectAnOption(industryDD, value);
	}
	
	/**
	 * This method is used to select a type from type drop down
	 * @param driverUtil
	 * @param value
	 */
	public void selectFromTypeDD(WebDriverUtility driverUtil, String value) {
		driverUtil.selectAnOption(typeDD, value);
	}
}
