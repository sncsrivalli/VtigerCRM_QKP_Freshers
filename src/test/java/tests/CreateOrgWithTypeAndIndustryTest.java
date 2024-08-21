package tests;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import objectRepo.CreatingNewOrganizationPage;
import objectRepo.OrganizationInformationPage;
import objectRepo.OrganizationsPage;

public class CreateOrgWithTypeAndIndustryTest extends BaseClass {

	@Test
	public void createOrgTest() {
		SoftAssert soft = new SoftAssert();
		OrganizationsPage org = pageObject.getOrganizationsPage();
		CreatingNewOrganizationPage createOrg = pageObject.getCreateOrgPage();
		OrganizationInformationPage orgInfo = pageObject.getOrgInfoPage();
		
		home.clickRequiredTab(web, "Accounts");
		soft.assertTrue(driver.getTitle().contains("Organizations"));
		org.clickCreateOrgBTN();
		soft.assertEquals(createOrg.getPageHeader(), "Creating New Organization");
		
		Map<String, String> map = excel.readFromExcel("Create Organization With Industry And Type");
		createOrg.setOrganizationName(map.get("Organization Name"));
		createOrg.selectFromIndustryDD(web, map.get("Industry"));
		createOrg.selectFromTypeDD(web, map.get("Type"));
		createOrg.clickSaveBTN();
		
		soft.assertTrue(orgInfo.getPageHeader().contains(map.get("Organization Name")));
		if(orgInfo.getPageHeader().contains(map.get("Organization Name")))
			excel.updateStatus("Create Organization With Industry And Type", "Pass");
		else
			excel.updateStatus("Create Organization With Industry And Type", "Fail");
		
		orgInfo.clickDeleteBTN();
		web.handleAlert("ok");
		soft.assertAll();
	}
}
