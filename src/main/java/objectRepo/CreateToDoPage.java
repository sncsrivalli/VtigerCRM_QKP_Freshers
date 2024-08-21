package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.JavaUtility;
import genericUtilities.WebDriverUtility;

/**
 * This class contains elements, locators and respective business libraries of Create To Do page
 * @author sncsr
 *
 */
public class CreateToDoPage {

	// Declaration
	@FindBy(name = "subject")
	private WebElement subjectTF;
	
	@FindBy(id = "jscal_trigger_date_start")
	private WebElement startDateWidget;
	
	@FindBy(xpath = "//div[@class='calendar' and contains(@style,'block')]/descendant::td[@class='title']")
	private WebElement calendarTitle;
	
	private String calendarCommonPath = "//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='%s']";
	
	@FindBy(id = "jscal_trigger_due_date")
	private WebElement dueDateWidget;
	
	@FindBy(xpath = "//input[@value='  Save']")
	private WebElement saveBTN;
	
	// Initialization
	public CreateToDoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Utilization
	
	/**
	 * This method is used to set the subject of the event
	 * @param subject
	 */
	public void setSubject(String subject) {
		subjectTF.sendKeys(subject);
	}
	
	/**
	 * This method clicks on the start date calendar widget
	 */
	public void clickStartDateWidget() {
		startDateWidget.click();
	}

	/**
	 * This method clicks on the due date calendar widget
	 */
	public void clickDueDateWidget() {
		dueDateWidget.click();
	}
	
	/**
	 * This method picks required date from calendar
	 * @param jutil
	 * @param driverUtil
	 * @param reqDate
	 */
	public void datePicker(JavaUtility jutil, WebDriverUtility driverUtil, String reqDate) {
		String[] startDate = jutil.splitString(reqDate, "-");
		int reqStartYear = (Integer) jutil.convertStringToAnyDataType(startDate[0], "int");
		String reqStartDate = startDate[2];
		int reqStartMonth = jutil.convertMonthToInt(startDate[1]);

		String currentMonthYear = calendarTitle.getText();
		String[] str = jutil.splitString(currentMonthYear, ", ");
		int currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], "int");

		while (currentYear < reqStartYear) {
			driverUtil.convertdynamicXpathToWebElement(calendarCommonPath, "»").click();

			currentMonthYear = calendarTitle.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentYear = (Integer) jutil.convertStringToAnyDataType(str[1], "int");
		}

		int currentMonth = jutil.convertMonthToInt(str[0]);

		while (currentMonth < reqStartMonth) {
			driverUtil.convertdynamicXpathToWebElement(calendarCommonPath, "›").click();
			currentMonthYear = calendarTitle.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		while (currentMonth > reqStartMonth) {
			driverUtil.convertdynamicXpathToWebElement(calendarCommonPath, "‹").click();
			currentMonthYear = calendarTitle.getText();
			str = jutil.splitString(currentMonthYear, ", ");
			currentMonth = jutil.convertMonthToInt(str[0]);
		}

		driverUtil.convertdynamicXpathToWebElement(calendarCommonPath, reqStartDate).click();
	}
	
	/**
	 * This method clicks on save button
	 */
	public void clickSaveBTN() {
		saveBTN.click();
	}
}
