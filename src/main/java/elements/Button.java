package elements;

import org.openqa.selenium.WebDriver;

public class Button extends ElementBase {
	/**
	 * Clicks the button.
	 */
	public void click() {
		element.click();
	}

	/**
	 * Constructor - Initialize the Web element.
	 * 
	 * @param dr
	 * @param elementID
	 */
	public Button(WebDriver dr, String elementID) {
		super(dr, elementID);
	}
}
