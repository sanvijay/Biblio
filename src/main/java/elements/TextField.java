package elements;

import org.openqa.selenium.WebDriver;

public class TextField extends ElementBase {
	/**
	 * Get the value of the element.
	 * 
	 * @return String - The value in the text field.
	 */
	public String value() {
		return element.getText();
	}
	
	/**
	 * Set a value to the text field.
	 * 
	 * @param str (String)
	 */
	public void set(String str) {
		element.sendKeys(str);
	}

	/**
	 * Constructor - Initialize the Web element.
	 * 
	 * @param dr
	 * @param elementID
	 */
	public TextField(WebDriver dr, String elementID) {
		super(dr, elementID);
	}
}
