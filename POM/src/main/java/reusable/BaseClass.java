package reusable;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelRead;
import utilities.ScreenShot;

public class BaseClass {

	public static WebDriver driver;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest test;
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	public static Logger log = Logger.getLogger(BaseClass.class.getName());

	// To write log in console and log file
	public void logger() {
		DOMConfigurator.configure(".\\src\\test\\resources\\log4j.xml");
	}
	

	// To instantiate browsers
	@BeforeTest
	@Parameters("browser")
	public void Login(String browser) throws InterruptedException, IOException, AWTException {

		log.info("******************Browser launch started!!!*****************");

		switch (browser) {
		// Check if parameter passed as 'chrome'
		case "chrome":
			try {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("******************Chrome lanched successfully!!!*****************");
			} catch (Exception e) {

				log.error("**********************Chrome launch unsuccessfull***************" + e.getMessage());
			}
			break;
		// Check if parameter passed as 'firefox'
		case "firefox":
			try {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("******************Firefox lanched successfully!!!*****************");
			} catch (Exception e) {

				log.error("**********************Firefox launch unsuccessfull***************" + e.getMessage());
			}
			break;
		// Check if parameter passed as 'edge'
		case "edge":
			try {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.info("******************Edge lanched successfully!!!*****************");
			} catch (Exception e) {

				log.error("**********************Edge launch unsuccessfull***************" + e.getMessage());
			}
			break;
		// Check if parameter passed as 'safari'
		case "safari":
			try {
				driver = new SafariDriver();
				log.info("******************Safari lanched successfully!!!*****************");
			} catch (Exception e) {

				log.error("**********************Safari launch unsuccessfull***************" + e.getMessage());
			}
			break;

		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

	}

	ExcelRead Excel = new ExcelRead();
	ScreenShot SS = new ScreenShot();

	// To read configuration data
	public Hashtable<String, String> ConfigDataRead() throws InterruptedException, IOException, AWTException {
		Hashtable<String, String> ConfigData = null;
		try {

			ConfigData = Excel.setMapData(
					".\\Test Components\\Test configuration File.xlsx",
					"0");
		} catch (Exception e) {

			log.error("Error in ConfigDataRead " + e.getMessage());
		}
		return ConfigData;

	}

	// To read the Application locators data
	public Hashtable<String, String> LocatorDataRead() throws InterruptedException, IOException, AWTException {
		Hashtable<String, String> LocatorData = null;
		try {

			Hashtable<String, String> ConfigData = ConfigDataRead();
			LocatorData = Excel.setMapData(ConfigData.get("LocatorFilePath"), ConfigData.get("SheetIndex"));
		} catch (Exception e) {

			log.error("Error in LocatorDataRead " + e.getMessage());
		}
		return LocatorData;
	}

	// To read test script
	public Map<String, Map<String, String>> TestScriptaRead() throws InterruptedException, IOException, AWTException {

		Map<String, Map<String, String>> TestScript = null;
		try {

			Hashtable<String, String> ConfigData = ConfigDataRead();

			TestScript = Excel.getExcelAsMap(ConfigData.get("TestScriptpath"), ConfigData.get("SheetIndex"));
		} catch (Exception e) {

			log.error("Error in TestScriptRead " + e.getMessage());
		}
		return TestScript;

	}

	// To find the row index of a passed cell value
	public int RowIndex(String testname) throws InterruptedException, IOException, AWTException {
		int RI = 0;
		try {

			Hashtable<String, String> ConfigData = ConfigDataRead();
			RI = Excel.GetrowNum(testname, ConfigData.get("TestScriptpath"), ConfigData.get("SheetIndex"));
		} catch (Exception e) {

			log.error("Error in RowIndex " + e.getMessage());
		}
		return RI;

	}

	// To read Test Data
	public Map<String, Map<String, String>> TestDataRead() throws InterruptedException, IOException, AWTException {

		Map<String, Map<String, String>> TestData = null;
		try {

			Hashtable<String, String> ConfigData = ConfigDataRead();

			TestData = Excel.getExcelAsMap(ConfigData.get("TestDataFilePath"), ConfigData.get("SheetIndex"));
		} catch (Exception e) {

			log.error("Error in TestDataRead " + e.getMessage());
		}
		return TestData;

	}

	// To take screenshot of present screen
	public String ScreenShot(String status, String testname) throws Exception {

		Hashtable<String, String> ConfigData = ConfigDataRead();
		String path = ConfigData.get("ScreenshotFolderPath");
		String Ext = ConfigData.get("ScreenshotExtension");
		try {

			SS.takeSnapShot(ConfigData.get("ScreenshotFolderPath"), status, testname,
					ConfigData.get("ScreenshotExtension"), driver);

		} catch (Exception e) {

			log.error("Error in ScreenShot " + e.getMessage());
		}
		return path + status + "/" + testname + "." + Ext;

	}

	// To take screenshot of full screen
	public String FullScreenShot(String status, String testname) throws Exception {
		Hashtable<String, String> ConfigData = ConfigDataRead();
		String path = ConfigData.get("ScreenshotFolderPath");
		String Ext = ConfigData.get("ScreenshotExtension");

		try {

			SS.FullScreenShot(ConfigData.get("ScreenshotFolderPath"), status, testname,
					ConfigData.get("ScreenshotExtension"), driver);
		} catch (Exception e) {

			log.error("Error in FullScreenShot " + e.getMessage());
		}
		return path + status + "\\" + testname + "." + Ext;

	}

	@BeforeTest
	public void startReport() {

		try {
			ExtentSparkReporter spark = new ExtentSparkReporter("Spark" + "_" + timeStamp + ".html");
			extent.attachReporter(spark);
			
			log.info("*******************Report Started!!!***************");
			
		} catch (Exception e) {
			log.error(e);
		}

	}

	@AfterTest
	public void postcondition() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	// To Stop the extent report

	@AfterTest
	public void stopReport() {

		try {
			
			extent.flush();
			
			log.info("*******************Report Created!!!***************");

		} catch (Exception e) {
			log.info(e);
		}
	}

}
