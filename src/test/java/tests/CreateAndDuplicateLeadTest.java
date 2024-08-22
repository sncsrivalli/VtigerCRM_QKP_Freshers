package tests;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import objectRepo.CreatingNewLeadPage;
import objectRepo.DuplicatingPage;
import objectRepo.LeadInformationPage;
import objectRepo.LeadsPage;

public class CreateAndDuplicateLeadTest extends BaseClass {

	@Test
	public void createLeadTest() {
		LeadsPage leads = pageObject.getLeadsPage();
		CreatingNewLeadPage createLead = pageObject.getCreateLeadPage();
		LeadInformationPage leadInfo = pageObject.getLeadInfoPage();
		DuplicatingPage duplicate = pageObject.getDuplicateLeadPage();
		
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(web, "Leads");
		soft.assertTrue(driver.getTitle().contains("Leads"));
		leads.clickCreateLeadBTN();
		
		soft.assertEquals(createLead.getPageHeader(), "Creating New Lead");
		
		Map<String, String> map = excel.readFromExcel("Create and Duplicate Lead");
		String lastName = map.get("Last Name") + jutil.generateRandomNum(100);
		createLead.setLeadLastName(lastName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSaveBTN();
		
		soft.assertTrue(leadInfo.getPageHeader().contains(lastName));
		leadInfo.clickDuplicateBTN();
		soft.assertTrue(duplicate.getPageHeader().contains("Duplicating"));
		String newLeadName = map.get("New Last Name") + jutil.generateRandomNum(100);
		duplicate.setLeadLastName(newLeadName);
		duplicate.clickSaveBTN();
		
		soft.assertTrue(leadInfo.getPageHeader().contains(newLeadName));
		if(leadInfo.getPageHeader().contains(newLeadName))
			excel.updateStatus("Create and Duplicate Lead", "Pass");
		else
			excel.updateStatus("Create and Duplicate Lead", "Fail");

		soft.assertAll();
	}
}
