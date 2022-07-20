package appPages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import reusable.BaseClass;
import utilities.SeleniumActions;

public class HomePage extends BaseClass {

	SeleniumActions Actions = new SeleniumActions();

	BaseClass Base = new BaseClass();
	

	public String Apptitle() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();

		String Apptitle = Actions.GetText(Locator.get("Home"));

		return Apptitle;
	}
	
	public Boolean Title() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();

		Boolean Apptitle = Actions.IsExists(Locator.get("Title"));

		return Apptitle;
	}
	
	public void HPSearch (String testname) throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();
		
		Base.TestDataRead();
		
		Map<String, Map<String, String>> Data = TestDataRead();
		
		int RI = RowIndex(testname); 
		
		String R =String.valueOf(RI);

		Actions.SendKeys(Locator.get("HPSearch"), Data.get(R).get("Search"));

	}
	
	public void Search(String testname) throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();
		
		Base.TestDataRead();
		
		Map<String, Map<String, String>> Data = TestDataRead();
		
		int RI = RowIndex(testname); 
		
		String R =String.valueOf(RI);

		Actions.SendKeys(Locator.get("Search"), Data.get(R).get("Search"));
		
		Actions.ActionClassKeyDown("Enter");
		
		Thread.sleep(2000);
		


	}
	
	public Boolean HB() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();

		Boolean HB = Actions.IsExists(Locator.get("HB"));

		return HB;
	}
	
	

}
