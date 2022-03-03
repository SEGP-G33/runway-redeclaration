package seg.g33.Helpers;

import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Runway;

/**
 * ╺┳╸┏━╸╻ ╻╺┳╸┏━┓┏━┓╻┏┓╻╺┳╸
 *  ┃ ┣╸ ┏╋┛ ┃ ┣━┛┣┳┛┃┃┗┫ ┃
 *  ╹ ┗━╸╹ ╹ ╹ ╹  ╹┗╸╹╹ ╹ ╹
 * A script print out the results of the currently viewed situation in a textual format.
 */
public class TextPrint {

    /**************
     * Test Script *
     **************/

    public static void main(String[] args) {
        Airport airport = new Airport("LHR");
        System.out.println(text(airport));
        Runway runway = new Runway("R27", airport, 10, 'L', 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0);
        System.out.println(text(runway));
    }

    /********************
     * TextPrint Airport *
     ********************/

    public static String text(Airport airport) {
        StringBuilder string = new StringBuilder();
        var runwayIndex = 0;
        string.append("    _    ___ ____  ____   ___  ____ _____ \n" +
                        "   / \\  |_ _|  _ \\|  _ \\ / _ \\|  _ \\_   _|\n" +
                        "  / _ \\  | || |_) | |_) | | | | |_) || |  \n" +
                        " / ___ \\ | ||  _ <|  __/| |_| |  _ < | |  \n" +
                        "/_/   \\_\\___|_| \\_\\_|    \\___/|_| \\_\\|_|  \n")
                .append("---------- [System Information] ----------\n")
                .append((airport.getName() == null) ? ("  NAME\t\t\t:  " + airport.getName() + "\n") : "  NAME\t\t\t:  null\n");
        if (airport.getAirportRunways() != null)
            for (Runway runway : airport.getAirportRunways())
                string.append("  RUNWAY" + runwayIndex++ + "\t\t:  " + runway.getName() + "\n");
        return string.append("-------- [System Information End] --------\n").toString();
    }

    /*******************
     * TextPrint Runway *
     *******************/

    public static String text(Runway runway) {
        StringBuilder string = new StringBuilder();
        return string.append("  ____  _   _ _   ___        ___ __   __\n" +
                        " |  _ \\| | | | \\ | \\ \\      / / \\\\ \\ / /\n" +
                        " | |_) | | | |  \\| |\\ \\ /\\ / / _ \\\\ V / \n" +
                        " |  _ <| |_| | |\\  | \\ V  V / ___ \\| |  \n" +
                        " |_| \\_\\\\___/|_| \\_|  \\_/\\_/_/   \\_\\_|  \n")
                .append("---------- [System Information] ----------\n")
                .append((runway.getAirport() == null) ? ("  AIRPORT\t\t:  " + runway.getAirport().getName() + "\n") : "  AIRPORT\t\t:  null\n")
                .append((runway.getName() == null) ? ("  NAME\t\t\t:  " + runway.getName() + "\n") : "  NAME\t\t\t:  null\n")
                .append((runway.getAngle() == null) ? ("  ANGLE\t\t\t:  " + runway.getAngle() + "\n") : "  ANGLE\t\t\t:  null\n")
                .append((runway.getDirection() == null) ? ("  DIRECTION\t\t:  " + runway.getDirection() + "\n") : "  DIRECTION\t\t:  null\n")
                .append((runway.getDefTORA() == null) ? ("  TORA\t\t\t:  " + runway.getDefTORA() + "\n") : "  TORA\t\t\t:  null\n")
                .append((runway.getDefASDA() == null) ? ("  ASDA\t\t\t:  " + runway.getDefASDA() + "\n") : "  ASDA\t\t\t:  null\n")
                .append((runway.getDefTODA() == null) ? ("  TODA\t\t\t:  " + runway.getDefTODA() + "\n") : "  TODA\t\t\t:  null\n")
                .append((runway.getDefLDA() == null) ? ("  LDA\t\t\t:  " + runway.getDefLDA() + "\n") : "  LDA\t\t\t:  null\n")
                .append((runway.getClearWayLength() == null) ? ("  ClearWay\t\t:  " + runway.getClearWayLength() + "\n") : "  ClearWay\t\t:  null\n")
                .append((runway.getStopWayLength() == null) ? ("  StopWay\t\t:  " + runway.getStopWayLength() + "\n") : "  StopWay\t\t:  null\n")
                .append((runway.getDisplacedThreshold() == null) ? ("  THRESHOLD\t\t:  " + runway.getDisplacedThreshold() + "\n") : "  THRESHOLD\t\t:  null\n")
                .append("-------- [System Information End] --------\n").toString();
    }

    /**
     * TODO: Plane; Obstacle; Calculate; RunwayParameters
     */
}
