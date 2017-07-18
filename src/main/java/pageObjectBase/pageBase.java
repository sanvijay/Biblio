package pageObjectBase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pageObjectBase.pageBase;

public class pageBase {
	public Document page_model() {
		Document doc = null;
		return doc;
	}

	// Constructor which creates objects for all the elements
	// mentioned in the page object xml.
	pageBase() {
		pageBase pB = new pageBase();
		Document doc = pB.page_model();

		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("element");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				System.out.println("Text Area : " + eElement.getAttribute("id"));
			}
		}
	}
}