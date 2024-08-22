package tests;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import objectRepo.ContactInformationPage;
import objectRepo.ContactsPage;
import objectRepo.CreatingNewContactPage;

public class CreateContactWithExistingOrgTest extends BaseClass {

	@Test
	public void createContactTest() {
		ContactsPage contacts = pageObject.getContactsPage();
		CreatingNewContactPage createContact = pageObject.getCreateContactPage();
		ContactInformationPage contactInfo = pageObject.getContactInfoPage();
		
		SoftAssert soft = new SoftAssert();
		home.clickRequiredTab(web, "Contacts");
		
		soft.assertTrue(driver.getTitle().contains("Contacts"));
		contacts.clickCreateContactBTN();
		soft.assertEquals(createContact.getPageHeader(), "Creating New Contact");
		
		Map<String, String> map = excel.readFromExcel("Create Contact With Organization");
		createContact.setContactLastName(map.get("Last Name"));
		createContact.selectExistingOrganization(web, map.get("Organization Name"));
		createContact.clickSaveBTN();
		
		soft.assertTrue(contactInfo.getPageHeader().contains(map.get("Last Name")));
		if(contactInfo.getPageHeader().contains(map.get("Last Name")))
			excel.updateStatus("Create Contact With Organization", "Pass");
		else
			excel.updateStatus("Create Contact With Organization", "Fail");

		contactInfo.clickDeleteBTN();
		web.handleAlert("ok");
		
		soft.assertAll();
	}
}
