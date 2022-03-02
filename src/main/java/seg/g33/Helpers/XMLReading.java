package seg.g33.Helpers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLReading {

    public static void main(String[] args) {
        var reader = new XMLReading();
//        var name = XMLReading.class.getResource("Airport.xml").toExternalForm();

        reader.configureAiportFromXMLFile("src/main/resources/Airport.xml");
    }

    public void configureAiportFromXMLFile(String filename) {
        var factory = DocumentBuilderFactory.newInstance();
        Document xmlFile;

        try {
            var builder = factory.newDocumentBuilder();
            var file = new File(filename);

            xmlFile = builder.parse(file);
            xmlFile.getDocumentElement().normalize();

            var root = xmlFile.getDocumentElement();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
