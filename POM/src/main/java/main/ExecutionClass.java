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
	
		@Test
		public void TS_SDIR_016() throws Exception {
	
			String testname = "TS_SDIR_016";
			try {
	
				login.URL();
				
				Thread.sleep(2000);
				
				HP.Search(testname);
	
				Boolean HB = HP.HB();
	
				if (HB) {
					ER.ERSS(testname, "Pass", "Navalur got in map");
				} else {
					ER.ERSS(testname, "Fail", "Navalur not got in map");
				}
				log.info(testname + " Executed Sucessfully");
			} catch (Exception e) {
				ER.ERSS(testname, "Exception", e.getMessage());
				log.error(testname + " Execution Failed" + e.getMessage());
			}
	
		}
	
		@Test
		public void TS_SDIR_017() throws Exception {
	
			String testname = "TS_SDIR_017";
			try {
	
				login.URL();
				
				Thread.sleep(2000);
	
				HP.Search(testname);
				
				
				Boolean HB = HP.HB();
	
				if (HB) {
					ER.ERFSS(testname, "Pass", "CDM found");
				} else {
					ER.ERFSS(testname, "Fail", "CDM not found");
				}
				log.info(testname + " Executed Sucessfully");
			} catch (Exception e) {
				ER.ERFSS(testname, "Exception", e.getMessage());
				log.error(testname + " Execution Failed" + e.getMessage());
			}
	
		}
		
		@Test
		public void TS_SDIR_019() throws Exception {
	
			String testname = "TS_SDIR_019";
			try {
	
				
				login.URL2();			
				
				Boolean HB = HP.Title();
	
				if (HB) {
					ER.ERFSS(testname, "Pass", "CDM found");
				} else {
					ER.ERFSS(testname, "Fail", "CDM not found");
				}
				log.info(testname + " Executed Sucessfully");
			} catch (Exception e) {
				ER.ERFSS(testname, "Exception", e.getMessage());
				log.error(testname + " Execution Failed" + e.getMessage());
			}
	
		}
	
	}
