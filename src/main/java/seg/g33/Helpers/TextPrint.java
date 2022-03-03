package seg.g33.Helpers;

import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Plane;
import seg.g33.Entitites.Runway;

/**
 * ╺┳╸┏━╸╻ ╻╺┳╸┏━┓┏━┓╻┏┓╻╺┳╸
 *  ┃ ┣╸ ┏╋┛ ┃ ┣━┛┣┳┛┃┃┗┫ ┃
 *  ╹ ┗━╸╹ ╹ ╹ ╹  ╹┗╸╹╹ ╹ ╹
 *  A script print out the results of the currently viewed situation in a textual format.
 */
public class TextPrint {

    /**************
     * Test Script *
     * need delete *
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
                .append("  NAME\t\t\t:  " + airport.getName() + "\n");
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
                .append("  NAME\t\t\t:  " + runway.getName() + "\n")
                .append("  AIRPORT\t\t:  " + runway.getAirport().getName() + "\n")
                .append("  ANGLE\t\t\t:  " + runway.getAngle() + "\n")
                .append("  DIRECTION\t\t:  " + runway.getDirection() + "\n")
                .append("  TORA\t\t\t:  " + runway.getDefTORA() + "\n")
                .append("  ASDA\t\t\t:  "+ runway.getDefASDA() + "\n")
                .append("  TODA\t\t\t:  " + runway.getDefTODA() + "\n")
                .append("  LDA\t\t\t:  " + runway.getDefLDA() + "\n")
                .append("  ClearWay\t\t:  " + runway.getClearWayLength() + "\n")
                .append("  StopWay\t\t:  " + runway.getStopWayLength() + "\n")
                .append("  THRESHOLD\t\t:  " + runway.getDisplacedThreshold() + "\n")
                .append("-------- [System Information End] --------\n").toString();
    }

    public static String text(Plane plane) {
        StringBuilder string = new StringBuilder();
        return string.append("     ____  _        _    _   _ _____ \n" +
                        "    |  _ \\| |      / \\  | \\ | | ____|\n" +
                        "    | |_) | |     / _ \\ |  \\| |  _|  \n" +
                        "    |  __/| |___ / ___ \\| |\\  | |___ \n" +
                        "    |_|   |_____/_/   \\_\\_| \\_|_____|")
                .append("  NAME\t\t\t:  " + plane.getName() + "\n")
                .append("  BLAST\t\t:  " + plane.getBlastProtection() + "\n")
                .append("-------- [System Information End] --------\n").toString();
    }


    /**
     * TODO: Plane; Obstacle; Calculate; RunwayParameters
     */
}
