package in.docsapp.generics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Assertion {
	
	//To verify URL
		public static void verifyURL(WebDriver driver,String expectedText){
			Assert.assertTrue(driver.getCurrentUrl().contains(expectedText),
					"URL doesnot contain the expected character sequence");
		}
		
		//To verify if actual text contains character sequence of expected text
		public static void textContains(String expectedText, String actualText){
			Assert.assertTrue(actualText.contains(expectedText),
					"Actual text doesnot contain the expected character sequence");
		}

		// To verify if a WebElement is displayed
		public static boolean displayElement(WebDriver driver,WebElement element) {
			GenericMethods gen =new GenericMethods();
			gen.waitUntilElementISVisible(driver, element);
			 Assert.assertTrue(element.isDisplayed(), "Element is displayed");
			 return true;
		}
		
		// To verify if a WebElement is displayed
			public static boolean elementNotDisplayed(WebDriver driver, WebElement element) {
//				 Wait.waitForElementInvisibility(driver, element);
				 Assert.assertFalse(element.isDisplayed(), "Element is not displayed");
				 return true;
			}

		// To verify if a WebElement is enabled
		public static void enableElement(WebElement element) {
			Assert.assertTrue(element.isEnabled(), "Element is not enabled");
		}

		// To verify if a WebElement is Selected
		public static void selectElement(WebElement element) {
			Assert.assertTrue(element.isSelected(), "Element is not selected");
		}

		// To verify if actual text is equal to expected text
		public static void isTextEqual(WebElement element, String expectedText) {
			Assert.assertEquals(expectedText, element.getText(), "Expected text is not equal to actual text");
		}

		// To verify if a given text is empty
		public static void isTextEmpty(WebElement element) {
			Assert.assertFalse(element.getText().isEmpty(), "Element is empty");
		}

		// To verify if a given text contains expected character sequence
		public static void textContains(WebElement element, String expectedText) {
			Assert.assertTrue(element.getText().contains(expectedText),
					"Actual text doesnot contain the expected character sequence");
		}
}
