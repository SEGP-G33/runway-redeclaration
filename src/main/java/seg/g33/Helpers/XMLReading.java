package seg.g33.Helpers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Obstacle;
import seg.g33.Entitites.Runway;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLReading {

    // TODO: Here just to test the reading of XML Files. DELETE LATER ON.
//    public static void main(String[] args) {
//        var reader = new XMLReading();
//
//        // Reads Airport
//        reader.configureAirportFromXMLFile("src/main/resources/Airport.xml");
//
//        // Reads Obstacle and prints it
//        System.out.println(reader.configureObstacleFromXMLFile("src/main/resources/Obstacle.xml"));
//    }

    public Airport configureAirportFromXMLFile(String filename) {

        var factory = DocumentBuilderFactory.newInstance();
        Document xmlFile;

        Airport airport = null;

        try {
            var builder = factory.newDocumentBuilder();
            var file = new File(filename);

            xmlFile = builder.parse(file);
            xmlFile.getDocumentElement().normalize();

            var name =  xmlFile.getElementsByTagName("airportName").item(0).getTextContent();
            System.out.println("Airport Name: " +  name);

            var airportRunwaysList = xmlFile.getElementsByTagName("airportRunway");
            System.out.println(name + " has " + airportRunwaysList.getLength() + " runways");

            airport = new Airport(name);

            for (int i = 0; i < airportRunwaysList.getLength(); i++) {
                var runwayNode = airportRunwaysList.item(i);
                var runway = buildRunwayFromNode(runwayNode, airport);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return airport;
    }

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
                    var angle = Integer.parseInt(runwaySectionElement.getElementsByTagName("angle").item(0).getTextContent());
                    var direction = runwaySectionElement.getElementsByTagName("direction").item(0).getTextContent();
                    var length = Double.parseDouble(runwaySectionElement.getElementsByTagName("length").item(0).getTextContent());
                    var clearway = Double.parseDouble(runwaySectionElement.getElementsByTagName("clearway").item(0).getTextContent());
                    var stopway = Double.parseDouble(runwaySectionElement.getElementsByTagName("stopway").item(0).getTextContent());
                    var resa = Double.parseDouble(runwaySectionElement.getElementsByTagName("resa").item(0).getTextContent());
                    var tora = Double.parseDouble(runwaySectionElement.getElementsByTagName("tora").item(0).getTextContent());
                    var toda = Double.parseDouble(runwaySectionElement.getElementsByTagName("toda").item(0).getTextContent());
                    var asda = Double.parseDouble(runwaySectionElement.getElementsByTagName("asda").item(0).getTextContent());
                    var lda = Double.parseDouble(runwaySectionElement.getElementsByTagName("lda").item(0).getTextContent());
                    var stripend = Double.parseDouble(runwaySectionElement.getElementsByTagName("stripend").item(0).getTextContent());
                    var displaced = Double.parseDouble(runwaySectionElement.getElementsByTagName("displaced").item(0).getTextContent());

                    runway = new Runway(runwayName, airport, angle, direction.charAt(0), tora, asda, toda, lda, clearway, stopway, resa, stripend, displaced);
                }
            }
        }
        return runway;
    }

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
            e.printStackTrace();
        }

        return obstacle;
    }

    private Obstacle buildObstacleFromElement(Element root) {
        var name = extractValue("name", root);
        var height = extractValue("height", root);
        var center = Double.parseDouble(extractValue("centerDistance", root));
        var left = Double.parseDouble(extractValue("leftDistance", root));
        var right = Double.parseDouble(extractValue("rightDistance", root));

        return new Obstacle(name, Double.parseDouble(height), Double.parseDouble(height), Double.parseDouble(height), Double.parseDouble(height));
    }

    private String extractValue(String tagName, Element root) {
        return root.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
