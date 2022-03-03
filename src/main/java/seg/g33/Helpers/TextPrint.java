package seg.g33.Helpers;

import seg.g33.Entitites.*;

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

    /******************
     * TextPrint Plane *
     ******************/

    public static String text(Plane plane) {
        StringBuilder string = new StringBuilder();
        return string.append("     ____  _        _    _   _ _____ \n" +
                        "    |  _ \\| |      / \\  | \\ | | ____|\n" +
                        "    | |_) | |     / _ \\ |  \\| |  _|  \n" +
                        "    |  __/| |___ / ___ \\| |\\  | |___ \n" +
                        "    |_|   |_____/_/   \\_\\_| \\_|_____|\n")
                .append("  NAME\t\t\t:  " + plane.getName() + "\n")
                .append("  BLAST\t\t:  " + plane.getBlastProtection() + "\n")
                .append("-------- [System Information End] --------\n").toString();
    }

    /********************
     * TextPrint Obstacle *
     ********************/

    public static String text(Obstacle obstacle) {
        StringBuilder string = new StringBuilder();
        return string.append("  ___  ____ ____ _____  _    ____ _     _____ \n" +
                        " / _ \\| __ ) ___|_   _|/ \\  / ___| |   | ____|\n" +
                        "| | | |  _ \\___ \\ | | / _ \\| |   | |   |  _|  \n" +
                        "| |_| | |_) |__) || |/ ___ \\ |___| |___| |___ \n" +
                        " \\___/|____/____/ |_/_/   \\_\\____|_____|_____|\n")
                .append("  NAME\t\t\t:  " + obstacle.getName() + "\n")
                .append("  HEIGHT\t\t:  " + obstacle.getHeight() + "\n")
                .append("  CenterDistance\t:  " + obstacle.getCenterDistance() + "\n")
                .append("  RightDistance\t:  " + obstacle.getRightDistance() + "\n")
                .append("  LeftDistance\t:  " + obstacle.getLeftDistance() + "\n")
                .append("-------- [System Information End] --------\n").toString();
    }

    /**********************
     * TextPrint Calculator *
     **********************/

    public static String text(Calculator calculator) {
        StringBuilder string = new StringBuilder();
        return string.append("  ____    _    _     ____ _   _ _        _  _____ ___  ____  \n" +
                        " / ___|  / \\  | |   / ___| | | | |      / \\|_   _/ _ \\|  _ \\ \n" +
                        "| |     / _ \\ | |  | |   | | | | |     / _ \\ | || | | | |_) |\n" +
                        "| |___ / ___ \\| |__| |___| |_| | |___ / ___ \\| || |_| |  _ < \n" +
                        " \\____/_/   \\_\\_____\\____|\\___/|_____/_/   \\_\\_| \\___/|_| \\_\\\n")
                .append("  NAME\t\t\t:  " + calculator.getName() + "\n")
                .append("  PLANE\t\t:  " + calculator.getPlane() + "\n")
                .append("  RUNWAY\t\t:  " + calculator.getRunway() + "\n")
                .append("  OBSTACLE\t\t:  " + calculator.getObstacle() + "\n")
                .append("-------- [System Information End] --------\n").toString();
    }


    /****************************
     * TextPrint RunwayParameters *
     ****************************/

    public static String text(RunwayParameters parameter) {
        StringBuilder string = new StringBuilder();
        return string.append(" ____   _    ____      _    __  __ _____ _____ _____ ____  \n" +
                        "|  _ \\ / \\  |  _ \\    / \\  |  \\/  | ____|_   _| ____|  _ \\ \n" +
                        "| |_) / _ \\ | |_) |  / _ \\ | |\\/| |  _|   | | |  _| | |_) |\n" +
                        "|  __/ ___ \\|  _ <  / ___ \\| |  | | |___  | | | |___|  _ < \n" +
                        "|_| /_/   \\_\\_| \\_\\/_/   \\_\\_|  |_|_____| |_| |_____|_| \\_\\\n")
                .append("  NAME\t\t\t:  " + parameter.getName() + "\n")
                .append("  RUNWAY\t\t:  " + parameter.getRunway() + "\n")
                .append("  TORA\t\t\t:  " + parameter.getTORA() + "\n")
                .append("  TODA\t\t\t:  " + parameter.getTODA() + "\n")
                .append("  ASDA\t\t\t:  " + parameter.getASDA() + "\n")
                .append("  LDA\t\t\t:  " + parameter.getLDA() + "\n")
                .append("  ALS\t\t\t:  " + parameter.getALS() + "\n")
                .append("  TOCS\t\t\t:  " + parameter.getTOCS() + "\n")
                .append("  DIRECTION\t:  " + parameter.getDirection() + "\n")
                .append("  RESA\t\t\t:  " + parameter.getRESA() + "\n")
                .append("-------- [System Information End] --------\n").toString();
    }
}
