package objectRepo;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	WebDriver driver;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}
	
	public HomePage getHomePage() {
		return new HomePage(driver);
	}

	public OrganizationsPage getOrganizationsPage() {
		return new OrganizationsPage(driver);
	}

	public ContactsPage getContactsPage() {
		return new ContactsPage(driver);
	}

	public LeadsPage getLeadsPage() {
		return new LeadsPage(driver);
	}

	public CreateToDoPage getCreateToDoPage() {
		return new CreateToDoPage(driver);
	}

	public CreatingNewOrganizationPage getCreateOrgPage() {
		return new CreatingNewOrganizationPage(driver);
	}

	public CreatingNewContactPage getCreateContactPage() {
		return new CreatingNewContactPage(driver);
	}

	public CreatingNewLeadPage getCreateLeadPage() {
		return new CreatingNewLeadPage(driver);
	}

	public OrganizationInformationPage getOrgInfoPage() {
		return new OrganizationInformationPage(driver);
	}

	public ContactInformationPage getContactInfoPage() {
		return new ContactInformationPage(driver);
	}

	public LeadInformationPage getLeadInfoPage() {
		return new LeadInformationPage(driver);
	}

	public EventInformationPage getEventInfoPage() {
		return new EventInformationPage(driver);
	}

	public DuplicatingPage getDuplicateLeadPage() {
		return new DuplicatingPage(driver);
	}
}
