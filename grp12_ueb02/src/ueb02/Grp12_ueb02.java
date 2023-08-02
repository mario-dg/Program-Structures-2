package ueb02;

/**
 * Für mehrere Touren soll ausgegeben werden:
 * Die Tour mit ihren Wegepunkten und die Gesamtlänge der Tour
 * 
 * Die am dichtesten liegenden benachbarten Wegepunkte und ihre Distanz
 * 
 * Die Tour mit von Nord nach Süd sortierten Wegepunkten und ihre Länge
 * 
 * Die Tour beginnend bei einem bestimmten Wegepunkt mit jeweils dem dichtesten als nächsten Punkt 
 * und die Gesamtlänge dieser Tour
 * 
 * Eine Tour mit den Wegepunkten, die durch die übergebenen Indizes bestimmt werden, 
 * und ihre Gesamtlänge
 * 
 * Die Originaltour, zum Prüfen, dass sie unverändert ist
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Grp12_ueb02 {

    /**
     * Index für den Start der Tour
     */
    private static final int INDEX = 0;

    /**
     * Indizes zum erstellen einer neuen Tour
     */
    private static final int[] TOUR_INDICES = {1, 2, 0, 3, 1};

    /**
     * Gibt das Array in Stringform aus
     *
     * @param indices Array, dessen Elemente ausgegeben werden sollen
     * @return String mit den Elementen des Arrays
     */
    private static String arrayToString(int[] indices) {
        String arrayString = ""; 
        for (int i = 0; i < indices.length; i++) {
            arrayString += " " + indices[i];
        } 
        
        return arrayString;
    }
    
    /**
     * MAIN METHOD
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        // Formatierte Ausgabe (nach Vorschrift)
        for (int i = 0; i < TourData.getCountOfTours(); i++) {
            //TODO DONE Legt euch Variablen für die veränderten Touren an, 
            //damit ihr die Umstellung nicht zweimal berechnen müsst
            int[][] tour = TourData.createDeepCopyOfTour(i);
            int[][] closestWaypointsTour = Tour.getClosestWaypoints(tour);
            int[][] northSouthTour = Tour.createTourSortedNorthSouth(tour);
            int[][] shortestTour = Tour.createShortestTour(tour, i);
            int[][] orderedTour = Tour.createTourWithOrder(tour, TOUR_INDICES);
            
            System.out.printf("Indices for new order:%s%n", arrayToString(TOUR_INDICES));
            System.out.println("-----------------------------");
            System.out.printf("TOUR[%d]%14s: %s, distance: %1.2f%n", i, " ",
                    Tour.tourToString(tour),
                    Tour.calcDistanceOverAllWaypoints(tour));
            System.out.printf("closest waypoints%4s: %s, distance: %1.2f%n", " ",
                    Tour.tourToString(closestWaypointsTour),
                    Tour.calcDistanceOverAllWaypoints(closestWaypointsTour));
            System.out.printf("sorted NorthSouth%4s: %s, distance: %1.2f%n",
                    " ", Tour.tourToString(northSouthTour),
                    Tour.calcDistanceOverAllWaypoints(northSouthTour));      
            System.out.printf("short tour start at %d: %s, distance: %1.2f%n",
                    i, Tour.tourToString(shortestTour),
                    Tour.calcDistanceOverAllWaypoints(shortestTour));
            System.out.printf("new ordered tour%5s: %s, distance: %1.2f%n", " ",
                    Tour.tourToString(orderedTour),
                    Tour.calcDistanceOverAllWaypoints(orderedTour));
            System.out.printf("TOUR[%d]%14s: %s%n", i, " ",
                    Tour.tourToString(tour));
            System.out.printf("-----------------------------%n%n");
        }
    }         
}
