package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.PageObjectManager;

public class BaseClass {

	//@BeforeSuite
	//@BeforeTest
	
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected WebDriverUtility web;
	protected JavaUtility jutil;
	
	protected WebDriver driver;
	
	protected PageObjectManager pageObject;
	protected LoginPage login;
	protected HomePage home;
	
	@BeforeClass
	public void classConfig() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		web = new WebDriverUtility();
		jutil = new JavaUtility();
		
		property.propertiesInit(IConstantPath.PROPERTIES_PATH);
		
		driver = web.launchBrowser(property.getDataFromProperties("browser"));
		web.maximizeBrowser();
		
		long time = (Long) jutil.convertStringToAnyDataType(property.getDataFromProperties("timeouts"), "long");
		web.waitTillElementFound(time);
	}
	
	@BeforeMethod
	public void methodConfig() {
		pageObject = new PageObjectManager(driver);
		
		web.navigateToApp(property.getDataFromProperties("url"));
		Assert.assertTrue(driver.getTitle().contains("vtiger"));
		login = pageObject.getLoginPage();
		home = pageObject.getHomePage();
		login.loginToVtiger(property.getDataFromProperties("username"), property.getDataFromProperties("password"));
		Assert.assertTrue(driver.getTitle().contains("Home"));
		excel.excelInit(IConstantPath.EXCEL_PATH, "Sheet1");
	}
	
	@AfterMethod
	public void methodTeardown() {
		home.signOutOfVtiger(web);
		excel.saveExcel(IConstantPath.EXCEL_PATH);
	}
	
	@AfterClass
	public void classTeardown() {
		web.quitBrowsers();
		excel.closeExcel();
	}
	
	//@AfterTest
	//@AfterSuite
}
