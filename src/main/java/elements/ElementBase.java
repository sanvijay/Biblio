package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementBase {
	public WebElement element;

	/**
	 * Constructor - Initialize the Web element.
	 * 
	 * @param dr
	 * @param elementID
	 */
	public ElementBase(WebDriver dr, String elementID) {
		element = dr.findElement(By.id(elementID));
	}
}
