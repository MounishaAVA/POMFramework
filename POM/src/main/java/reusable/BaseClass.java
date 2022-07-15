package reusable;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseClass {

	public static WebDriver driver;
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	public static ExtentReports extent;
	public static ExtentTest test;

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

	}

	// To read excel and store as excel hash table Key and value Configuration files
	@SuppressWarnings("resource")
	public Hashtable<String, String> setMapData(String filepathConfig, String sheetIndex) throws IOException {

		FileInputStream fis = new FileInputStream(filepathConfig);

		Workbook workbook = new XSSFWorkbook(fis);

		int Index = Integer.parseInt(sheetIndex);

		Sheet sheet = workbook.getSheetAt(Index);

		int lastRow = sheet.getLastRowNum();

		Hashtable<String, String> my_dict = new Hashtable<String, String>();

		for (int i = 0; i <= lastRow; i++) {

			DataFormatter formatter = new DataFormatter();

			Row row = sheet.getRow(i);

			String key = formatter.formatCellValue(row.getCell(0));
			String value = formatter.formatCellValue(row.getCell(1));

			// Putting key & value in dataMap
			my_dict.put(key, value);

		}
		return my_dict;
	}

	// To get the row index by passing Test Script ID
	public int GetrowNum(String LookupVal, String filePath, String sheetIndex) {
		int Rowindex = 0;
		try {
			String toFind = LookupVal;
			Workbook wb = WorkbookFactory.create(new File(filePath));
			DataFormatter formatter = new DataFormatter();
			int Index = Integer.parseInt(sheetIndex);
			org.apache.poi.ss.usermodel.Sheet sheet1 = wb.getSheetAt(Index);
			for (Row row : sheet1) {
				for (Cell cell : row) {
					CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
					String text = formatter.formatCellValue(cell);

					// is it an exact match?
					if (toFind.equals(text)) {

						Rowindex = cellRef.getRow();
					}
				}
			}
		} catch (Exception e) {
		}
		return Rowindex;
	}

	// To read excel and store as hash map for test data
	private XSSFSheet getSheet(String filePathReadData, int sheetIndex) throws IOException {
		FileInputStream fis = new FileInputStream(filePathReadData);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		workbook.close();
		return sheet;
	}

	public Map<String, Map<String, String>> getExcelAsMap(String filePathReadData, String sheetIndex)
			throws IOException {

		int Index = Integer.parseInt(sheetIndex);
		XSSFSheet sheet = getSheet(filePathReadData, Index);
		Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
		List<String> columnHeader = new ArrayList<String>();
		Row row = sheet.getRow(0);
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			columnHeader.add(cellIterator.next().getStringCellValue());
		}
		int rowCount = sheet.getLastRowNum();
		int columnCount = row.getLastCellNum();
		for (int i = 1; i <= rowCount; i++) {
			Map<String, String> singleRowData = new HashMap<String, String>();
			Row row1 = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row1.getCell(j);
				singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
			}
			completeSheetData.put(String.valueOf(i), singleRowData);
		}
		return completeSheetData;
	}

	public String getCellValueAsString(Cell cell) {
		String cellValue = null;
		try {
			switch (cell.getCellType()) {
			case NUMERIC:
				if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

					cellValue = String.valueOf(cell.getNumericCellValue());
					if (DateUtil.isCellDateFormatted(cell)) {
						DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
						Date date = cell.getDateCellValue();
						cellValue = df.format(date);
					}
				}

				break;
			case STRING:
				cellValue = cell.getStringCellValue();
				break;
			case BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA:
				cellValue = cell.getCellFormula();
			default:
				cellValue = "DEFAULT";
			}
		} catch (NullPointerException npe) {
			cellValue = " ";
		}

		return cellValue;
	}

	// To take screen shot of present screen
	public void takeSnapShot(String Folderpath, String FolderName, String SSName, String Extension, WebDriver webdriver)
			throws Exception {

		String scrFolder = Folderpath + FolderName + "/".toString();
		new File(scrFolder).mkdir();
		File file = new File(scrFolder);

		if (!file.isDirectory()) {

			// To create folder and save screenshot inside the folder
			scrFolder = Folderpath.toString();
			new File(scrFolder).mkdir();

			// To set an environment variable to point to the screenshot folder
			System.setProperty("scr.folder", scrFolder);

		} else {
			// To set an environment variable to point to the screenshot folder
			System.setProperty("scr.folder", scrFolder);

		}

		// get the screenshot folder location from environment variable set in beginning
		// of test
		String scrFolder1 = System.getProperty("scr.folder");

		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		// copy screenshot to screenshot folder
		FileUtils.copyFile(scrFile, new File(scrFolder1 + SSName + "." + Extension));
	}

	// To take screen shot of full screen
	public void FullScreenShot(String Folderpath, String FolderName, String SSName, String Extension,
			WebDriver webdriver) throws Exception {

		String scrFolder = Folderpath + FolderName + "/".toString();
		new File(scrFolder).mkdir();
		File file = new File(scrFolder);

		if (!file.isDirectory()) {

			// To create folder and save screenshot inside the folder
			scrFolder = Folderpath.toString();
			new File(scrFolder).mkdir();
			// To set an environment variable to point to the screenshot folder
			System.setProperty("scr.folder", scrFolder);

		} else {
			// To set an environment variable to point to the screenshot folder
			System.setProperty("scr.folder", scrFolder);

		}
		// get the screenshot folder location from environment variable set in beginning
		// of test
		String scrFolder1 = System.getProperty("scr.folder");
		// To capture full page screenshot and store the image
		ru.yandex.qatools.ashot.Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(webdriver);
		ImageIO.write(s.getImage(), Extension, new File(scrFolder1 + SSName + "." + Extension));

	}

	@BeforeSuite
	// To Write report in Extend report
	public void StartReport() {
		try {
			ExtentSparkReporter reporter = new ExtentSparkReporter("./results" + timeStamp + ".html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		} catch (Exception e) {
			log.error("Error in StartReport " + e.getMessage());
		}

	}

	// To read configuration data
	public Hashtable<String, String> ConfigDataRead() throws InterruptedException, IOException, AWTException {
		Hashtable<String, String> ConfigData = null;
		try {

			ConfigData = setMapData(".\\Test Components\\Test configuration File.xlsx", "0");

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
			LocatorData = setMapData(ConfigData.get("LocatorFilePath"), ConfigData.get("SheetIndex"));

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

			TestScript = getExcelAsMap(ConfigData.get("TestScriptpath"), ConfigData.get("SheetIndex"));

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
			RI = GetrowNum(testname, ConfigData.get("TestScriptpath"), ConfigData.get("SheetIndex"));
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

			TestData = getExcelAsMap(ConfigData.get("TestDataFilePath"), ConfigData.get("SheetIndex"));

		} catch (Exception e) {

			log.error("Error in TestDataRead " + e.getMessage());
		}
		return TestData;

	}

	// To take screenshot of present screen
	public String ScreenShot(String status, String testname) throws Exception {
		try {
			Hashtable<String, String> ConfigData = ConfigDataRead();

			takeSnapShot(ConfigData.get("ScreenshotFolderPath"), status, testname,
					ConfigData.get("ScreenshotExtension"), driver);
		} catch (Exception e) {

			log.error("Error in ScreenShot " + e.getMessage());
		}

		return status;
	}

	// To take screenshot of full screen
	public String FullScreenShot(String status, String testname) throws Exception {
		try {
			Hashtable<String, String> ConfigData = ConfigDataRead();

			takeSnapShot(ConfigData.get("ScreenshotFolderPath"), status, testname,
					ConfigData.get("ScreenshotExtension"), driver);
		} catch (Exception e) {

			log.error("Error in FullScreenShot " + e.getMessage());
		}

		return status;
	}

	// To Write test details in Extend report
	public void testDetails(String testnmae) throws InterruptedException, IOException, AWTException {
		try {
			Map<String, Map<String, String>> TestScript = TestScriptaRead();
			int RI = RowIndex(testnmae);
			String R = String.valueOf(RI);

			test = extent.createTest(testnmae, TestScript.get(R).get("Test Script Description"));
			test.assignCategory(TestScript.get(R).get("Module Name"));
			test.assignCategory(TestScript.get(R).get("Testtype"));
			test.assignCategory(TestScript.get(R).get("testCategory"));
			test.assignCategory(TestScript.get(R).get("Severity"));
			test.assignCategory(TestScript.get(R).get("Priority"));
			test.assignAuthor(TestScript.get(R).get("Executed By"));
		} catch (Exception e) {

			log.error("Error in testDetails" + e.getMessage());
		}
	}

	// To write result in extend report with present screen screenshot
	public void ExtentReportSS(String Testname, String Status, String ResultMsg) throws Exception {
		try {
			if (Status.equalsIgnoreCase("Pass")) {
				test.pass(ResultMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot(Status, Testname)).build());
			} else if (Status.equalsIgnoreCase("Fail")) {
				test.pass(ResultMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot(Status, Testname)).build());
			} else if (Status.equalsIgnoreCase("Exception")) {
				test.pass(ResultMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot(Status, Testname)).build());
			}
		} catch (Exception e) {

			log.error("Error in ExtentReportSS" + e.getMessage());
		}

	}

	// To write result in extend report with full screen screenshot
	public void ExtentReportFSS(String Testname, String Status, String ResultMsg) throws Exception {
		try {
			if (Status.equalsIgnoreCase("Pass")) {
				test.pass(ResultMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(FullScreenShot(Status, Testname)).build());
			} else if (Status.equalsIgnoreCase("Fail")) {
				test.pass(ResultMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(FullScreenShot(Status, Testname)).build());
			} else if (Status.equalsIgnoreCase("Exception")) {
				test.pass(ResultMsg,
						MediaEntityBuilder.createScreenCaptureFromPath(FullScreenShot(Status, Testname)).build());
			}
		} catch (Exception e) {

			log.error("Error in ExtentReportSS" + e.getMessage());
		}

	}

	@AfterTest
	public void postcondition() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	@AfterSuite
	// To Stop the extent report
	public void stopReport() {
		extent.flush();

	}

}
