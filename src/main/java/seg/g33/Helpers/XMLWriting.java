package seg.g33.Helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import seg.g33.Entitites.Obstacle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class XMLWriting {


    /**
     * Creates an XML file for a given obstacle.
     * @param obstacle The obstacle class instance to be saved
     * @param filename Filename for the XML file
     */
    public void createObstacleXMLFile(Obstacle obstacle, String filename) {
        var builderFactory = DocumentBuilderFactory.newInstance();
        var transformerFactory  = TransformerFactory.newInstance();

        try {
            var builder = builderFactory.newDocumentBuilder();
            var transformer = transformerFactory.newTransformer();
            var xmlFile = builder.newDocument();

            var rootElement = xmlFile.createElement("Obstacle");

            // Properties
            rootElement.appendChild(createElementForValue(xmlFile, "name", obstacle.getName()));
            rootElement.appendChild(createElementForValue(xmlFile, "height", Double.toString(obstacle.getHeight())));
            rootElement.appendChild(createElementForValue(xmlFile, "centerDistance", Double.toString(obstacle.getCenterDistance())));
            rootElement.appendChild(createElementForValue(xmlFile, "leftDistance", Double.toString(obstacle.getLeftDistance())));
            rootElement.appendChild(createElementForValue(xmlFile, "rightDistance", Double.toString(obstacle.getRightDistance())));

            xmlFile.appendChild(rootElement);

            var source = new DOMSource(xmlFile);
            var outputStream = new FileOutputStream(filename);
            var result = new StreamResult(outputStream);

            // TODO: Look more into properties for tranformer. These 2 are needed to properly indent the XML File.
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (Exception e) {
            // TODO: Handle exceptions
            e.printStackTrace();
        }
    }

    /**
     * Creates an Element object to be appended to another element.
     * Avoids repeating the same 4 lines all over the saveXML functions
     * @param xmlFile the DOM element that creates the new elements
     * @param tagName the name of the new tag element
     * @param nodeValue the value to be stored in the new tag element
     */
    private Element createElementForValue(Document xmlFile, String tagName, String nodeValue) {
        var elementTag = xmlFile.createElement(tagName);
        var elementTextNode = xmlFile.createTextNode(nodeValue);
        elementTag.appendChild(elementTextNode);
        return elementTag;
    }

}
