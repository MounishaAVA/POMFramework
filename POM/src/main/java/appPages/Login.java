package appPages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import reusable.BaseClass;
import utilities.SeleniumActions;

public class Login extends BaseClass {

	SeleniumActions Actions = new SeleniumActions();

	BaseClass Base = new BaseClass();
	
	public void URL() throws InterruptedException, IOException, AWTException {

		Base.ConfigDataRead();

		Hashtable<String, String> ConfigData = ConfigDataRead();
		
		
		driver.get(ConfigData.get("AppLoginURL"));
		
		
	}
	
	public void URL2() throws InterruptedException, IOException, AWTException {

		Base.ConfigDataRead();

		Hashtable<String, String> ConfigData = ConfigDataRead();
		
		
		driver.get(ConfigData.get("URL"));
		
		
	}

	public void alert() {

		Actions.Alert("accept");
		
	}

	public void UserName() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();

		Base.ConfigDataRead();

		Hashtable<String, String> ConfigData = ConfigDataRead();

		Actions.SendKeys(Locator.get("UserName"), ConfigData.get("UserName"));

	}
	
	public void Password() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();

		Base.ConfigDataRead();

		Hashtable<String, String> ConfigData = ConfigDataRead();	

		Actions.SendKeys(Locator.get("Password"), ConfigData.get("Password"));
		

	}
	
	public void LoginButton() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		Hashtable<String, String> Locator = LocatorDataRead();

		Actions.ActionClass(Locator.get("LoginButton"), "click");
		
		Actions.ExplicitWait(30,Locator.get("AppTitle"), "visibilityOfElementLocated");
		

	} 
	

}
