package reusable;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumActions extends BaseClass {

	public String GetText(String Locator)

	{

		String Text = driver.findElement(By.xpath(Locator)).getText();
		return Text;

	}

	public void SendKeys(String Locator, String InputVal) {

		driver.findElement(By.xpath(Locator)).sendKeys(InputVal);

	}

	public void Click(String Locator)

	{

		driver.findElement(By.xpath(Locator)).click();

	}

	public void DDSelect(String Locator)

	{

		WebElement roleDropdown = driver.findElement(By.xpath(Locator));
		roleDropdown.click();

	}

	public Boolean IsEnabled(String Locator)

	{

		Boolean Exist = driver.findElement(By.xpath(Locator)).isEnabled();
		return Exist;

	}

	public Boolean IsExists(String Locator)

	{
		Boolean Dislayed = driver.findElement(By.xpath(Locator)).isDisplayed();
		return Dislayed;

	}

	public void ClearInputs(String Locator)

	{

		driver.findElement(By.xpath(Locator)).clear();

	}

	public void SelectByVisibleText(String Locator, String VisibleText)

	{

		WebElement mySelectedElement = driver.findElement(By.xpath(Locator));
		Select dropdown = new Select(mySelectedElement);
		dropdown.selectByVisibleText(VisibleText);

	}

	public void SelectByValue(String Locator, String Value)

	{

		WebElement mySelectedElement = driver.findElement(By.xpath(Locator));
		Select dropdown = new Select(mySelectedElement);
		dropdown.selectByValue(Value);

	}

	public void SelectByIndex(String Locator, int index)

	{

		WebElement mySelectedElement = driver.findElement(By.xpath(Locator));
		Select dropdown = new Select(mySelectedElement);
		dropdown.selectByIndex(index);

	}

	public String GetElementColor(String Locator)

	{

		WebElement target = driver.findElement(By.xpath(Locator));
		String colour = target.getCssValue("color");
		return colour;

	}

	public void ActionClass(String Locator, String Action)

	{
		Actions actions = new Actions(driver);

		switch (Action) {

		case "moveToElement":
			WebElement mouseHover = driver.findElement(By.xpath(Locator));
			actions.moveToElement(mouseHover);
			actions.perform();
			break;
		case "doubleClick":
			WebElement doubleClick = driver.findElement(By.xpath(Locator));
			actions.doubleClick(doubleClick);
			actions.perform();
			break;
		case "clickAndHold":
			WebElement clickAndHold = driver.findElement(By.xpath(Locator));
			actions.doubleClick(clickAndHold);
			actions.perform();
			break;
		case "click":
			WebElement click = driver.findElement(By.xpath(Locator));
			actions.click(click);
			actions.perform();
			break;
		case "contextClick":
			WebElement contextClick = driver.findElement(By.xpath(Locator));
			actions.contextClick(contextClick);
			actions.perform();
			break;
		case "sendKeys":
			WebElement sendKeys = driver.findElement(By.xpath(Locator));
			actions.sendKeys(sendKeys);
			actions.perform();
			break;
		}
	}

	public void ActionClassKey(String Action, String key)

	{
		Actions actions = new Actions(driver);

		switch (Action) {
		case "keyUp":
			actions.keyUp(key);
			actions.perform();
			break;
		case "keyDown":
			actions.keyDown(key);
			actions.perform();
			break;

		}
	}
	
	public void DragAndDrop(String Action, String SourceLocator, String TargetLocator) {
		Actions actions = new Actions(driver);
		
		WebElement Source = driver.findElement(By.xpath(SourceLocator));
		WebElement target = driver.findElement(By.xpath(TargetLocator));
		actions.dragAndDrop(Source, target);
		actions.perform();
	}
	

	public void SwitchToIFrame(int index)

	{

		driver.switchTo().frame(index);

	}

	public void SwitchToDC() {
		driver.switchTo().defaultContent();

	}

	@SuppressWarnings("deprecation")
	public void ImplicitWait(int WaitTime)

	{

		driver.manage().timeouts().implicitlyWait(WaitTime, TimeUnit.SECONDS);

	}

	@SuppressWarnings("unchecked")
	public static void ExplicitWait(Duration WaitTime, String Locator, String ExpectedCondition) {
		WebDriverWait wait = new WebDriverWait(driver, WaitTime);

		switch (ExpectedCondition) {

		case "alertIsPresent":
			wait.until(ExpectedConditions.alertIsPresent());
			break;
		// Need to check
		case "elementSelectionStateToBe":
			wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(Locator), false));
			break;
		case "elementToBeClickable":
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Locator)));
			break;
		case "elementToBeSelected":
			wait.until(ExpectedConditions.elementToBeSelected(By.xpath(Locator)));
			break;
		case "frameToBeAvaliableAndSwitchToIt":
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(Locator)));
			break;
		case "invisibilityOfTheElementLocated":
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Locator)));
			break;

		// Need to check
		case "invisibilityOfElementWithText":
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(Locator), ""));
			break;
		case "presenceOfAllElementsLocatedBy":
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Locator)));
			break;
		case "presenceOfElementLocated":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Locator)));
			break;

		// Need to check
		case "textToBePresentInElement":
			wait.until(ExpectedConditions.textToBePresentInElement((WebElement) By.xpath(Locator), ""));
			break;
		// Need to check
		case "textToBePresentInElementLocated":
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(Locator), ""));
			break;
		// Need to check
		case "textToBePresentInElementValue":
			wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(Locator), ""));
			break;
		// Need to check
		case "titleIs":
			wait.until(ExpectedConditions.titleIs(""));
			break;
		// Need to check
		case "titleContains":
			wait.until(ExpectedConditions.titleContains(""));
			break;
		// Need to check
		case "visibilityOf":
			wait.until(ExpectedConditions.visibilityOf((WebElement) By.xpath(Locator)));
			break;
		// Need to check
		case "visibilityOfAllElements":
			wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>) By.xpath(Locator)));
			break;
		case "visibilityOfAllElementsLocatedBy":
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
			break;
		case "visibilityOfElementLocated":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
			break;

		}
	}

	public void DoubleClick(String Locator, WebDriver driver) {

		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath(Locator));
		act.doubleClick(ele).perform();
	}

	public void Attachments(String Locator, WebDriver driver, String FilePath)
			throws AWTException, InterruptedException {

		DoubleClick(Locator, driver);
		Thread.sleep(5000);

		Robot rb = new Robot();

		StringSelection str = new StringSelection(FilePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);

		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}

}
