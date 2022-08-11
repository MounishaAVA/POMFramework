package appPages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import reusable.BaseClass;
import utilities.SeleniumActions;

public class Login extends BaseClass {

	SeleniumActions Actions = new SeleniumActions();
	BaseClass Base = new BaseClass();

	public void AppLoginURL() throws InterruptedException, IOException, AWTException {

		Base.ConfigDataRead();

		HashMap<String, String> ConfigData = ConfigDataRead();

		driver.get(ConfigData.get("AppLoginURL"));

	}

	public void WaitAlert() {

		Actions.ExplicitWait(20, "", "alertIsPresent");
	}

	public void alert() {

		Actions.Alert("accept");

	}
	
	
	public void WaitLoginToYourAccount() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Actions.ExplicitWait(20, Locator.get("LoginToYourAccount"), "visibilityOfElementLocated");
	}
	
	public boolean LoginToYourAccount() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Boolean exist = Actions.IsExists(Locator.get("LoginToYourAccount"));
		
		return exist;
	}
	
	

	public void WaitUsername() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Actions.ExplicitWait(20, Locator.get("UserName"), "visibilityOfElementLocated");
	}

	public void UserName() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Base.ConfigDataRead();

		HashMap<String, String> ConfigData = ConfigDataRead();

		Actions.SendKeys(Locator.get("UserName"), ConfigData.get("UserName"));

	}

	public void WaitPassword() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Actions.ExplicitWait(20, Locator.get("Password"), "visibilityOfElementLocated");
	}

	public void Password() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Base.ConfigDataRead();

		HashMap<String, String> ConfigData = ConfigDataRead();

		Actions.SendKeys(Locator.get("Password"), ConfigData.get("Password"));

	}

	public void WaitLoginButton() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Actions.ExplicitWait(20, Locator.get("Password"), "visibilityOfElementLocated");
	}

	public void LoginButton() throws InterruptedException, IOException, AWTException {

		Base.LocatorDataRead();

		HashMap<String, String> Locator = LocatorDataRead();

		Actions.ActionClass(Locator.get("LoginButton"), "click");

		Actions.ExplicitWait(30, Locator.get("AppTitle"), "visibilityOfElementLocated");

	}

}
