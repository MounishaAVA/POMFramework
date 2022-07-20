package utilities;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import reusable.BaseClass;

public class SeleniumActions extends BaseClass {

	public String GetText(String Locator)

	{

		String Text = driver.findElement(By.xpath(Locator)).getText();
		return Text;

	}
	
	public String GetAttribute(String Locator, String Value)

	{

		String Text = driver.findElement(By.xpath(Locator)).getAttribute(Value);
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

		Boolean Enabled = driver.findElement(By.xpath(Locator)).isEnabled();
		return Enabled;

	}

	public Boolean IsExists(String Locator)

	{
		Boolean Dislayed = driver.findElement(By.xpath(Locator)).isDisplayed();
		return Dislayed;

	}
	
	public Boolean IsSelected(String Locator)

	{
		Boolean Selected = driver.findElement(By.xpath(Locator)).isSelected();
		return Selected;

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

	public void ActionClassKeyDown(String key)

	{
		Actions actions = new Actions(driver);

		switch (key) {
		case "Enter":
			actions.keyDown(Keys.ENTER);
			actions.perform();
			break;
		case "Esc":
			actions.keyDown(Keys.ESCAPE);
			actions.perform();
			break;
		case "Control":
			actions.keyDown(Keys.LEFT_CONTROL);
			actions.perform();
			break;

		}
	}
	
	public void ActionClassKeyUp(String key)

	{
		Actions actions = new Actions(driver);

		switch (key) {
		case "Enter":
			actions.keyUp(Keys.ENTER);
			actions.perform();
			break;
		case "Esc":
			actions.keyUp(Keys.ESCAPE);
			actions.perform();
			break;
		case "Control":
			actions.keyUp(Keys.LEFT_CONTROL);
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
	
	public String GetWindowHandle() {

				
		String Handle = driver.getWindowHandle();
		
		return Handle;
		
	}
	
	public Set<String> GetWindowHandles() {

		
		Set<String> Handles = driver.getWindowHandles();
		
		return Handles;
		
	}
	
	public void SwitchToWindow(String Handle) {
		
		driver.switchTo().window(Handle);
	}

	public String GetWinddowTitle(String Handle) {
		
		String Title = driver.switchTo().window(Handle).getTitle();
		
		return Title;		
		
	}
	
	public void ImplicitWait(int WaitTime)

	{

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WaitTime));

	}

	public void ExplicitWait(int WaitTime, String Locator, String ExpectedCondition) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTime));

		switch (ExpectedCondition) {

		case "alertIsPresent":
			wait.until(ExpectedConditions.alertIsPresent());
			break;

		case "presenceOfAllElementsLocatedBy":
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Locator)));
			break;
		case "presenceOfElementLocated":
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Locator)));
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
		case "visibilityOfAllElementsLocatedBy":
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Locator)));
			break;
		case "visibilityOfElementLocated":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator)));
			break;
		// Need to check
		case "visibilityOf":
			wait.until(ExpectedConditions.visibilityOf((WebElement) By.xpath(Locator)));
			break;
		// Need to check
		// case "visibilityOfAllElements":
		// wait.until(ExpectedConditions.visibilityOfAllElements((List<WebElement>)
		// By.xpath(Locator)));
		// break;
		// Need to check
		case "elementSelectionStateToBe":
			wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(Locator), false));
			break;
		}
	}

	public void ExplicitWaitText(Duration WaitTime, String Locator, String ExpectedCondition, String Text) {
		WebDriverWait wait = new WebDriverWait(driver, WaitTime);
		switch (ExpectedCondition) {

		case "invisibilityOfElementWithText":
			wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(Locator), Text));
			break;
		case "textToBePresentInElement":
			wait.until(ExpectedConditions.textToBePresentInElement((WebElement) By.xpath(Locator), Text));
			break;
		case "textToBePresentInElementLocated":
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(Locator), Text));
			break;
		case "textToBePresentInElementValue":
			wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(Locator), Text));
			break;
		case "titleIs":
			wait.until(ExpectedConditions.titleIs(Text));
			break;
		case "titleContains":
			wait.until(ExpectedConditions.titleContains(Text));
			break;

		}
	}

	public void Alert(String method) {

		switch (method) {

		case "dismiss":
			driver.switchTo().alert().dismiss();
			break;
		case "accept":
			driver.switchTo().alert().accept();
			break;
		// case "getText":
		// String Text = driver.switchTo().alert().getText();
		// break;
		/*
		 * case "sendKeys": driver.switchTo().alert().sendKeys(Value); break;
		 */

		}

	}

	public void DoubleClick(String Locator) {

		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath(Locator));
		act.doubleClick(ele).perform();
	}

	public void Attachments(String Locator, String FilePath) throws AWTException, InterruptedException {

		DoubleClick(Locator);
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
