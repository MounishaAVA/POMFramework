package utilities;

import java.util.Map;

import reusable.BaseClass;

public class ExtentReport extends BaseClass {


	//To write test details in extent report and take screenshot of present screen
	public void ERSS(String testname, String Status, String Restut) throws Exception {
		
		Map<String, Map<String, String>> TestScript =TestScriptaRead(); 
		int RI = RowIndex(testname); String R =String.valueOf(RI);
		
		if(Status == "Pass") {
		test = extent.createTest(testname, TestScript.get(R).get("Test Script Description"));
		test.pass(Restut);
		test.info(TestScript.get(R).get("Test Steps"));
		test.assignCategory(TestScript.get(R).get("Module Name"));
		test.assignCategory(TestScript.get(R).get("Testing type"));
		test.assignCategory(TestScript.get(R).get("Test Category"));
		test.assignCategory(TestScript.get(R).get("Severity"));
		test.assignCategory(TestScript.get(R).get("Priority"));
		test.assignAuthor(TestScript.get(R).get("Executed By"));
		test.addScreenCaptureFromPath(ScreenShot(Status, testname));
		
		}
		
		else if (Status == "Fail") {
			test = extent.createTest(testname, TestScript.get(R).get("Test Script Description"));
			test.fail(Restut);
			test.info(TestScript.get(R).get("Test Steps"));
			test.assignCategory(TestScript.get(R).get("Module Name"));
			test.assignCategory(TestScript.get(R).get("Testing type"));
			test.assignCategory(TestScript.get(R).get("Test Category"));
			test.assignCategory(TestScript.get(R).get("Severity"));
			test.assignCategory(TestScript.get(R).get("Priority"));
			test.assignAuthor(TestScript.get(R).get("Executed By"));
			test.addScreenCaptureFromPath(ScreenShot(Status, testname));
			
		}
		else if (Status == "Exception") {
			test = extent.createTest(testname, TestScript.get(R).get("Test Script Description"));
			test.fail(Restut);
			test.info(TestScript.get(R).get("Test Steps"));
			test.assignCategory(TestScript.get(R).get("Module Name"));
			test.assignCategory(TestScript.get(R).get("Testing type"));
			test.assignCategory(TestScript.get(R).get("Test Category"));
			test.assignCategory(TestScript.get(R).get("Severity"));
			test.assignCategory(TestScript.get(R).get("Priority"));
			test.assignAuthor(TestScript.get(R).get("Executed By"));
			test.addScreenCaptureFromPath(ScreenShot(Status, testname));
			
		}
	}
	
	//To write test details in extent report and take screenshot of present screen
		public void ERFSS(String testname, String Status, String Restut) throws Exception {
			
			
			Map<String, Map<String, String>> TestScript =TestScriptaRead(); 
			int RI = RowIndex(testname); String R =String.valueOf(RI);
			if(Status == "Pass") {
			test = extent.createTest(testname, TestScript.get(R).get("Test Script Description"));
			test.pass(Restut);
			test.info(TestScript.get(R).get("Test Steps"));
			test.assignCategory(TestScript.get(R).get("Module Name"));
			test.assignCategory(TestScript.get(R).get("Testing type"));
			test.assignCategory(TestScript.get(R).get("Test Category"));
			test.assignCategory(TestScript.get(R).get("Severity"));
			test.assignCategory(TestScript.get(R).get("Priority"));
			test.assignAuthor(TestScript.get(R).get("Executed By"));
			test.addScreenCaptureFromPath(FullScreenShot(Status, testname));
			
			}
			
			else if (Status == "Fail") {
				test = extent.createTest(testname, TestScript.get(R).get("Test Script Description"));
				test.fail(Restut);
				test.info(TestScript.get(R).get("Test Steps"));
				test.assignCategory(TestScript.get(R).get("Module Name"));
				test.assignCategory(TestScript.get(R).get("Testing type"));
				test.assignCategory(TestScript.get(R).get("Test Category"));
				test.assignCategory(TestScript.get(R).get("Severity"));
				test.assignCategory(TestScript.get(R).get("Priority"));
				test.assignAuthor(TestScript.get(R).get("Executed By"));
				test.addScreenCaptureFromPath(FullScreenShot(Status, testname));
				
			}
			else if (Status == "Exception") {
				test = extent.createTest(testname, TestScript.get(R).get("Test Script Description"));
				test.fail(Restut);
				test.info(TestScript.get(R).get("Test Steps"));
				test.assignCategory(TestScript.get(R).get("Module Name"));
				test.assignCategory(TestScript.get(R).get("Testing type"));
				test.assignCategory(TestScript.get(R).get("Test Category"));
				test.assignCategory(TestScript.get(R).get("Severity"));
				test.assignCategory(TestScript.get(R).get("Priority"));
				test.assignAuthor(TestScript.get(R).get("Executed By"));
				test.addScreenCaptureFromPath(FullScreenShot(Status, testname));
				
			}
		}


}
