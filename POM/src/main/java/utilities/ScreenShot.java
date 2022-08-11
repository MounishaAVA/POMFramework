package utilities;

import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShot {
	
	// To take screen shot of present screen
		public void takeSnapShot(String Folderpath, String FolderName, String SSName, String Extension, WebDriver driver)
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

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// copy screenshot to screenshot folder
			FileUtils.copyFile(scrFile, new File(scrFolder1 + SSName + "." + Extension));
			
		}
		
		// To take screen shot of full screen
		public void FullScreenShot(String Folderpath, String FolderName, String SSName, String Extension,
				WebDriver driver) throws Exception {

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
					.takeScreenshot(driver);
			ImageIO.write(s.getImage(), Extension, new File(scrFolder1 + SSName + "." + Extension));

		}

}
