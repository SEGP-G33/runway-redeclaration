package seg.g33.Helpers;

public final class Validator {

    /**
     * Constructor is private so no new instances can be created
     */
    private Validator() { }

    /**
     * Checks whether a given file is an XML file.
     * @param filename the name of the file to be checked.
     * @return true if it's an XML file, false otherwise
     */
    public static Boolean isXMLFile(String filename) {
        return filename.endsWith(".xml");
    }


}
