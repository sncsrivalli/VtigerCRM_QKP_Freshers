package tests;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import objectRepo.CreateToDoPage;
import objectRepo.EventInformationPage;

public class CreateEventTest extends BaseClass {

	@Test
	public void createEventTest() {
		CreateToDoPage createToDo = pageObject.getCreateToDoPage();
		EventInformationPage eventInfo = pageObject.getEventInfoPage();
		
		SoftAssert soft = new SoftAssert();
		Map<String, String> map = excel.readFromExcel("Create New Event");
		home.selectFromQuickCreateDD(web, map.get("Quick Create"));
		createToDo.setSubject(map.get("Subject"));
		createToDo.clickStartDateWidget();
		createToDo.datePicker(jutil, web, map.get("Start Date"));
		createToDo.clickDueDateWidget();
		createToDo.datePicker(jutil, web, map.get("Due Date"));
		createToDo.clickSaveBTN();
		
		soft.assertTrue(eventInfo.getPageHeader().contains(map.get("Subject")));
		if(eventInfo.getPageHeader().contains(map.get("Subject")))
			excel.updateStatus("Create New Event", "Pass");
		else
			excel.updateStatus("Create New Event", "Fail");
		
		eventInfo.clickDeleteBTN();
		web.handleAlert("ok");
		
		soft.assertAll();
	}
}
