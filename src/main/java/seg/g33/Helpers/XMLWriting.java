package seg.g33.Helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Obstacle;
import seg.g33.Entitites.Runway;
import seg.g33.Entitites.RunwaySection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

public class XMLWriting {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(XMLWriting.class);

    /**
     * Creates an XML file for a given Airport.
     * @param airport The Airport class instance to be saved
     * @param filename Filename for the XML file
     */
    public void createAirportXMLFile(Airport airport, String filename) {
        if (airport == null) {
            throw new IllegalArgumentException("Airport can't be null");
        }

        var builderFactory = DocumentBuilderFactory.newInstance();
        var transformerFactory = TransformerFactory.newInstance();

        try {
            var builder = builderFactory.newDocumentBuilder();
            var transformer = transformerFactory.newTransformer();
            var xmlFile = builder.newDocument();

            var rootElement = xmlFile.createElement("airport");
            rootElement.appendChild(createElementForValue(xmlFile, "airportName", airport.getName()));
            rootElement.appendChild(createElementForValue(xmlFile, "airportCode", airport.getShortcode()));

            var runwaysRootElement = xmlFile.createElement("airportRunways");

            for (Runway runway : airport.getAirportRunways()) {
                var runwayElement = xmlFile.createElement("airportRunway");
                runwayElement.appendChild(createElementForValue(xmlFile, "name", runway.getName()));

                for (RunwaySection runwaySection: runway.getRunwaySections()) {
                    var sectionElement = xmlFile.createElement("runwaySection");
                    var params = runwaySection.getDefaultParameters();

                    var angle = Integer.toString(runwaySection.getAngle());
                    var direction = String.valueOf(runwaySection.getDirection());
                    var length = Double.toString(23.0); // TODO: Need to fix this later. Remove from XML spec..
                    var clearway = Double.toString(runwaySection.getClearWayLength());
                    var stopway = Double.toString(runwaySection.getStopWayLength());
                    var resa = Double.toString(runwaySection.getRESALength());
                    var tora = Double.toString(params.getTORA());
                    var toda = Double.toString(params.getTODA());
                    var asda = Double.toString(params.getASDA());
                    var lda = Double.toString(params.getLDA());
                    var stripend = Double.toString(runwaySection.getStripEndLength());
                    var displaced = Double.toString(runwaySection.getDisplacedThreshold());

                    sectionElement.appendChild(createElementForValue(xmlFile, "angle", angle));
                    sectionElement.appendChild(createElementForValue(xmlFile, "direction", direction));
                    sectionElement.appendChild(createElementForValue(xmlFile, "length", length));
                    sectionElement.appendChild(createElementForValue(xmlFile, "clearway", clearway));
                    sectionElement.appendChild(createElementForValue(xmlFile, "stopway", stopway));
                    sectionElement.appendChild(createElementForValue(xmlFile, "resa", resa));
                    sectionElement.appendChild(createElementForValue(xmlFile, "tora", tora));
                    sectionElement.appendChild(createElementForValue(xmlFile, "toda", toda));
                    sectionElement.appendChild(createElementForValue(xmlFile, "asda", asda));
                    sectionElement.appendChild(createElementForValue(xmlFile, "lda", lda));
                    sectionElement.appendChild(createElementForValue(xmlFile, "stripend", stripend));
                    sectionElement.appendChild(createElementForValue(xmlFile, "displaced", displaced));

                    runwayElement.appendChild(sectionElement);
                }

                runwaysRootElement.appendChild(runwayElement);
            }

            rootElement.appendChild(runwaysRootElement);

            xmlFile.appendChild(rootElement);

            var source = new DOMSource(xmlFile);
            var outputStream = new FileOutputStream(filename);
            var result = new StreamResult(outputStream);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

        } catch (Exception e) {
            // TODO: Handle exception
            e.printStackTrace();
        }
    }

    /**
     * Creates an XML file for a given obstacle.
     * @param obstacle The obstacle class instance to be saved
     * @param filename Filename for the XML file
     */
    public void createObstacleXMLFile(Obstacle obstacle, String filename) {
        var builderFactory = DocumentBuilderFactory.newInstance();
        var transformerFactory  = TransformerFactory.newInstance();

        if (obstacle == null) {
            throw new IllegalArgumentException("Obstacle can't be null...");
        }

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
            var outputStream = new FileOutputStream(filename + ".xml");
            var result = new StreamResult(outputStream);

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
