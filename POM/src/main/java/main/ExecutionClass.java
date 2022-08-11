package main;

import org.testng.annotations.Test;

import appPages.HomePage;
import appPages.Login;
import reusable.BaseClass;
import utilities.ExtentReport;

public class ExecutionClass extends BaseClass {

	ExtentReport ER = new ExtentReport();
	BaseClass BS = new BaseClass();
	Login login = new Login();
	HomePage HP = new HomePage();

	
	//Verify whether login page is displayed when punch in the URL in the browser
	@Test
	public void TC_SDIR_001() throws Exception {

		String testname = "TS_SDIR_001";
		try {

			login.AppLoginURL();

			login.WaitAlert();

			login.alert();

			login.WaitLoginToYourAccount();

			boolean exist = login.LoginToYourAccount();

			if (exist) {
				ER.ERSS(testname, "Pass", "Login page displayed");
			} else {
				ER.ERSS(testname, "Fail", "Login page not displayed");
			}
			log.info(testname + " Executed Sucessfully");
		} catch (Exception e) {
			ER.ERSS(testname, "Exception", e.getMessage());
			log.error(testname + " Execution Failed" + e.getMessage());
		}
	}

	//Verify whether user logged in to the application when provide valid credentiails
	@Test
	public void TS_SDIR_016() throws Exception {

		String testname = "TS_SDIR_016";
		try {

			login.AppLoginURL();

			login.WaitAlert();

			login.alert();

			login.WaitUsername();

			login.UserName();

			login.WaitPassword();

			login.Password();

			login.WaitLoginButton();

			login.LoginButton();

			HP.WaitApptitle();
			
			Thread.sleep(5000);

			boolean exist = HP.Apptitle();

			if (exist) {
				ER.ERFSS(testname, "Pass", "Login Sucessfull");
			} else {
				ER.ERFSS(testname, "Fail", "Login failed");
			}
			log.info(testname + " Executed Sucessfully");
		} catch (Exception e) {
			ER.ERFSS(testname, "Exception", e.getMessage());

			log.error(testname + " Execution Failed" + e.getMessage());
		}

	}
}
