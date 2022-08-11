package appPages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import reusable.BaseClass;
import utilities.SeleniumActions;

public class HomePage extends BaseClass {

	SeleniumActions Actions = new SeleniumActions();

	BaseClass Base = new BaseClass();
	
	public void WaitApptitle() throws InterruptedException, IOException, AWTException {
		
		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();
		
		Actions.ExplicitWait(20, Locator.get("AppTitle"), "visibilityOfElementLocated");
	}

	public boolean Apptitle() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		boolean exist = Actions.IsExists(Locator.get("AppTitle"));

		return exist;
	}

}
