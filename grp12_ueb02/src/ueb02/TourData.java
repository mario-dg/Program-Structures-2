package ueb02;

import java.util.Arrays;

/**
 * Datenquelle für Touren.
 *
 * @author Yannick Schröder, Gerit Kaleck
 */
public class TourData {

    /**
     * Mehrere Touren mit mehreren Wegepunkten, die aus jeweils x- und y-Wert
     * bestehen.
     */
    private static final int[][][] TOUR = new int[][][]{
        {{0, 0}, {4, 0}, {4, 3}, {0, 3}}, //Jede Zeile ist ein Tour
        {{0, 0}, {3, 0}, {3, 4}, {0, 0}},
        {{1, 3}, {3, 2}, {0, 4}, {2, 2}, {3, 1}, {1, 4}, {2, 3}},
        {{-2, -1}, {-2, +3}, {4, 3}, {0, 0}}
    };

    /**
     * Liefert die Anzahl der vorhandenen Touren in der Konstante.
     *
     * @return die Anzahl der vorhandenen Touren
     */
    public static int getCountOfTours() {
        return TOUR.length; //Ermittlung der Länge der 1. Dimension
    }

    /**
     * Liefert die tiefe Kopie der Tour.
     *
     * @param idx index der zu kopierenden Tour
     * @return tiefe Kopie der Tour
     */
    public static int[][] createDeepCopyOfTour(int idx) {
        int[][] copy = new int[TOUR[idx].length][TOUR[idx].length];
        for (int i = 0; i < TOUR[idx].length; i++) {
            copy[i] = TOUR[idx][i].clone();
        }
        return copy;
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {        
        int[][] copyTour = createDeepCopyOfTour(0);        
        copyTour[0] = new int[]{5,6};
        System.out.println(Arrays.toString(copyTour[0]));
        
        System.out.println(Arrays.toString(createDeepCopyOfTour(0)[0]));
    }
}
