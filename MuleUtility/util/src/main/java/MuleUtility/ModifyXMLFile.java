package MuleUtility;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import MuleUtility.PropertyFileValidator;
import hudson.model.BuildListener;
public class ModifyXMLFile {
	static Map<String, String> maps = new HashMap<String, String>();
	/*public static void main(String arg[]) {
		String filepath = "src/main/resources/mule.xml";
		getTagAndValueValidator(filepath);
	}*/

	public static void getTagAndValueValidator(String filepath,BuildListener listener) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			Element element = null;
			NodeList entries = doc.getElementsByTagName("*");

			for (int i = 1; i < entries.getLength(); i++) {
				element = (Element) entries.item(i);

				NamedNodeMap innerElmnt_gold_attr = element.getAttributes();
				for (int j = 0; j < innerElmnt_gold_attr.getLength(); ++j) {
					Node attr = innerElmnt_gold_attr.item(j);
					if (attr != null && attr.getNodeValue() != null && attr.getNodeValue().isEmpty()) {
						maps.put(element.getNodeName().replaceAll(":", "") + "_" + attr.getNodeName(),
								attr.getNodeValue());
					}
				}
			}
			if (maps.size() != 0) {
				PropertyFileValidator.getDescription(maps,listener);
			} else {
				System.out.println("All Validation seems fine!!!!");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
