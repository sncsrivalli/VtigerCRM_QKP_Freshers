package tests;

import java.util.Map;

import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import objectRepo.CreatingNewOrganizationPage;
import objectRepo.OrganizationInformationPage;
import objectRepo.OrganizationsPage;

public class CreateOrgWithTypeAndIndustryTest extends BaseClass {

	@Test
	public void createOrgTest() {
		OrganizationsPage org = pageObject.getOrganizationsPage();
		CreatingNewOrganizationPage createOrg = pageObject.getCreateOrgPage();
		OrganizationInformationPage orgInfo = pageObject.getOrgInfoPage();
		
		home.clickRequiredTab(web, "Accounts");
		org.clickCreateOrgBTN();
		
		Map<String, String> map = excel.readFromExcel("Create Organization With Industry And Type");
		createOrg.setOrganizationName(map.get("Organization Name"));
		createOrg.selectFromIndustryDD(web, map.get("Industry"));
		createOrg.selectFromTypeDD(web, map.get("Type"));
		createOrg.clickSaveBTN();
		
		orgInfo.clickDeleteBTN();
		web.handleAlert("ok");
	}
}
