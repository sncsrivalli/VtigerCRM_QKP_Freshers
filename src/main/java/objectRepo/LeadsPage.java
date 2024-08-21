package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	// Declaration
	@FindBy(xpath = "//img[@alt='Create Lead...']")
	private WebElement createLeadBTN;

	// Initialization
	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization

	/**
	 * This method clicks on the create organization button
	 */
	public void clickCreateLeadBTN() {
		createLeadBTN.click();
	}
}
