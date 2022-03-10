package seg.g33.Helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import seg.g33.Entitites.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReading {

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(XMLReading.class);

    /**
     * Reads an Airport from an XML file.
     * @param filename the filename of the XML file
     * @return an instance of the Airport class
     */
    public Airport configureAirportFromXMLFile(String filename) {
        var factory = DocumentBuilderFactory.newInstance();

        Airport airport = null;

        try {
            var builder = factory.newDocumentBuilder();
            var file = new File(filename);
            var xmlFile = builder.parse(file);
            xmlFile.getDocumentElement().normalize();

            var name =  xmlFile.getElementsByTagName("airportName").item(0).getTextContent();
            airport = new Airport(name);

            var airportRunwaysList = xmlFile.getElementsByTagName("airportRunway");
            for (int i = 0; i < airportRunwaysList.getLength(); i++) {
                var runwayNode = airportRunwaysList.item(i);
                var runway = buildRunwayFromNode(runwayNode, airport);
                airport.addRunway(runway);
            }
        }
        catch (Exception e) {
            // TODO: Handle exceptions
            e.printStackTrace();
            return null;
        }

        return airport;
    }

    /**
     * Builds a Runway instance from a Node
     * @param runwayNode the node of the XMl file
     * @param airport the airport which the runway is a part of
     * @return an instance of the Runway class
     */
    private Runway buildRunwayFromNode(Node runwayNode, Airport airport) {
        Runway runway = null;

        if (runwayNode.getNodeType() == Node.ELEMENT_NODE) {
            var runwayElement = ((Element) runwayNode);
            var runwayName = runwayElement.getElementsByTagName("name").item(0).getTextContent();
            System.out.println("***** " + runwayName + " *****");

            var runwaySectionsList = runwayElement.getElementsByTagName("runwaySection");
            System.out.println(runwayName + " has " + runwaySectionsList.getLength() + " sections");

            for (int j=0; j < runwaySectionsList.getLength(); j++) {
                var runwaySectionNode = runwaySectionsList.item(j);
                if (runwayNode.getNodeType() == Node.ELEMENT_NODE) {
                    var runwaySectionElement = (Element) runwaySectionNode;
                    var runwaySection = buildRunwaySection(runway, runwaySectionElement);
                    // TODO: There is something wrong here. In line 73 this will be set for every run of the loop.
                    // TODO: We don't want that. I'll change later tho.
                    runway = buildRunwayFromElement(runwayName, airport, runwaySectionElement);
                    runway.addRunwaySection(runwaySection);
                }
            }
        }
        return runway;
    }

    /**
     * Builds a RunwaySection instance
     * @param runway The runway the section is a part of
     * @param runwaySectionElement the XML element the RunwaySection is a part of
     * @return an instance of the RunwaySection class
     */
    private RunwaySection buildRunwaySection(Runway runway, Element runwaySectionElement) {
        var sectionParams = buildRunwaySectionParams(runwaySectionElement);
        var angle = Integer.parseInt(extractValue("angle", runwaySectionElement));
        var displaced = Double.parseDouble(extractValue("displaced", runwaySectionElement));
        return new RunwaySection(runway, angle, displaced, sectionParams);
    }

    /**
     * Builds an instance of the RunwayParams
     * @param runwaySectionElement the XML element the RunwaySection is a part of
     * @return an instance of the RunwayParams class
     */
    private RunwayParameters buildRunwaySectionParams(Element runwaySectionElement) {
        var tora = Double.parseDouble(extractValue("tora", runwaySectionElement));
        var asda = Double.parseDouble(extractValue("asda", runwaySectionElement));
        var toda = Double.parseDouble(extractValue("toda", runwaySectionElement));
        var lda = Double.parseDouble(extractValue("lda", runwaySectionElement));
        return new RunwayParameters(tora, asda, toda, lda);
    }

    /**
     * Builds an instance of the Runway class
     */
    private Runway buildRunwayFromElement(String runwayName, Airport airport, Element runwaySectionElement) {
        var angle = Integer.parseInt(extractValue("angle", runwaySectionElement));
        var direction = extractValue("direction", runwaySectionElement);
        var length = Double.parseDouble(extractValue("length", runwaySectionElement));
        var clearway = Double.parseDouble(extractValue("clearway", runwaySectionElement));
        var stopway = Double.parseDouble(extractValue("stopway", runwaySectionElement));
        var resa = Double.parseDouble(extractValue("resa", runwaySectionElement));
        var tora = Double.parseDouble(extractValue("tora", runwaySectionElement));
        var asda = Double.parseDouble(extractValue("asda", runwaySectionElement));
        var toda = Double.parseDouble(extractValue("toda", runwaySectionElement));
        var lda = Double.parseDouble(extractValue("lda", runwaySectionElement));
        var stripend = Double.parseDouble(extractValue("stripend", runwaySectionElement));
        var displaced = Double.parseDouble(extractValue("displaced", runwaySectionElement));

        return new Runway(runwayName, airport, angle, direction.charAt(0), tora, asda, toda, lda, clearway, stopway, resa, stripend, displaced);
    }

    /**
     * Reads an obstacle XML file and creates an obstacle object
     * @param filename the filename of the XML file
     * @return an instance of the Obstacle class
     */
    public Obstacle configureObstacleFromXMLFile(String filename) {
        Obstacle obstacle = null;

        var builderFactory = DocumentBuilderFactory.newInstance();

        try {
            var builder = builderFactory.newDocumentBuilder();

            var file = new File(filename);
            var xmlFile = builder.parse(file);
            xmlFile.getDocumentElement().normalize();

            var obstacleRoot = xmlFile.getDocumentElement();
            obstacle = buildObstacleFromElement(obstacleRoot);

        } catch (Exception e) {
            // TODO: Handle exception.
            e.printStackTrace();
            return null;
        }

        return obstacle;
    }

    /**
     * Builds an instance of the Obstacle class from the given root node.
     * @param root the root of the obstacle XML file
     * @return an instance of the Obstacle class
     */
    private Obstacle buildObstacleFromElement(Element root) {
        var name = extractValue("name", root);
        var height = Double.parseDouble(extractValue("height", root));
        var center = Double.parseDouble(extractValue("centerDistance", root));
        var left = Double.parseDouble(extractValue("leftDistance", root));
        var right = Double.parseDouble(extractValue("rightDistance", root));

        return new Obstacle(name, height, center, left, right);
    }

    /**
     * Helper function to avoid repeatedly writing 'getElementsByTagName(tagName).item(0).getTextContent()';
     * @param tagName the name of the tag in which the value we extract is located
     * @param root the parent or root node that its children is the tag with the extracted value
     * @return the text contents of the tag
     */
    private String extractValue(String tagName, Element root) {
        return root.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
