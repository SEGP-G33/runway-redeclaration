package seg.g33.Helpers;

import seg.g33.Entitites.Airport;
import seg.g33.Entitites.Runway;

//
public class TextPrint {
    public static void main(String[] args) {
        Airport airport = new Airport("New");
        System.out.println(textAirport(airport));
    }

    public static String textAirport(Airport airport){
        StringBuilder string = new StringBuilder();
        int runwayIndex = 0;
        string.append("    _    ___ ____  ____   ___  ____ _____ \n   / \\  |_ _|  _ \\|  _ \\ / _ \\|  _ \\_   _|\n  / _ \\  | || |_) | |_) | | | | |_) || |  \n / ___ \\ | ||  _ <|  __/| |_| |  _ < | |  \n/_/   \\_\\___|_| \\_\\_|    \\___/|_| \\_\\|_|  \n")
                .append("---------- [System Information] ----------\n");
        string.append((airport.getName() == null) ? ("  NAME      :  "+airport.getName()+"\n") : "  NAME      :  null\n");
        if (airport.getAirportRunways() != null)
            for (Runway runway: airport.getAirportRunways())string.append("  RUNWAY" + runwayIndex++ + "\t:  "+runway.getName()+"\n");
        string.append("-------- [System Information End] --------\n");
        return string.toString();
    }

}
