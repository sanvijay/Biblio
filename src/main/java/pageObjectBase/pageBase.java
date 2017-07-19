package pageObjectBase;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import elements.Button;
import elements.TextField;

public class pageBase {
	public Map<String, TextField> text_fields = new HashMap<String, TextField>();
	public Map<String, Button> buttons = new HashMap<String, Button>();
	public WebDriver driver;

	/**
	 * This function will be overriding in all the test pages, if the
	 * test page has XML file mapped.
	 *
	 * @return File - XML file, in which the page elements are defined.
	 */
	public File page_xml_file() {
		File doc = null;
		return doc;
	}

	/**
	 * This function will be overriding in all the test pages, if
	 * the XML is created in the function.
	 *
	 * @return Document - Parsed XML document.
	 */
	public Document page_doc_model() {
		Document doc = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			File fXmlFile = page_xml_file();
			if(fXmlFile != null) {
				return dBuilder.parse(fXmlFile);
			}

			String xmlString = "<body></body>";
			doc = dBuilder.parse( new InputSource( new StringReader( xmlString ) ) );
		} catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch(SAXException se) {
			se.printStackTrace();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return doc;
	}

	/**
	 * Constructor which creates objects for all the elements
	 * mentioned in the page object xml.
	 *
	 * @param dr - the web driver handle.
	 */
	protected pageBase(WebDriver dr) {
		driver = dr;
		Document doc = this.page_doc_model();

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		// handle the input fields
		NodeList inputList = doc.getElementsByTagName("input");
		handleInputFields(inputList);
	}

	/**
	 * Handle the input list and Store the Web elements in the list.
	 *
	 * @param NodeList - List of all input tags parsed.
	 */
	private void handleInputFields(NodeList inputList) {
		for (int temp = 0; temp < inputList.getLength(); temp++) {

			Node nNode = inputList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String custom_name = eElement.getAttribute("var_name");
				String elementID = eElement.getAttribute("id");

				if(eElement.getAttribute("type").equals("text")) {
					text_fields.put(custom_name, new TextField(driver, elementID));
				} else if(eElement.getAttribute("type").equals("submit"))  {
					buttons.put(custom_name, new Button(driver, elementID));
				}

			}
		}
	}

	/**
	 * Get the title of the browser currently working.
	 *
	 * @return String - the title content.
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Take screenshot of the page.
	 *
	 * @param String - location to save images.
	 */
	public void screenShot(String location) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		// Copy the image to the location mentioned.
		try {
			FileUtils.copyFile(scrFile, new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}