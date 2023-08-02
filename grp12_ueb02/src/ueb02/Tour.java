package ueb02;

/**
 * Statische Methoden zur Untersuchung einer Tour, die aus Wegepunkten besteht, welche aus einem
 * int-Array mit einem x- und einem y-Wert bestehen.
 *
 * @author Yannick Schröder, Gerit Kaleck
 */
public class Tour {

    /**
     * Position des x-Wertes im Array eines Wegepunktes
     */
    private static final int X = 0;

    /**
     * Position des y-Wertes im Array eines Wegepunktes
     */
    private static final int Y = 1;

    /**
     * Sortiert ein 2D Array nach BubbleSort-Verfahren
     *
     * @param tourToSort Array, welches sortiert werden soll(Aufsteigend)
     */
    private static void bubbleSort(int[][] tourToSort) {
        for (int i = 1; i < tourToSort.length; i++) {
            for (int j = 0; j < tourToSort.length - i; j++) {
                if (tourToSort[j][1] > tourToSort[j + 1][1]) {
                    swapWayPoints(tourToSort, j, j + 1);
                }
            }
        }
    }

    /**
     * Tauscht 2 gegebene Arrays in einem 2D Array
     *
     * @param tourToSwap Array, indem die Werte getauscht werden sollen
     * @param j Waypoint 1, der mit
     * @param i Waypoint 2 getauscht werden soll
     */
    private static void swapWayPoints(int[][] tourToSwap, int j, int i) {
        int[] temp = new int[2];
        temp[0] = tourToSwap[j][0];
        temp[1] = tourToSwap[j][1];
        tourToSwap[j][0] = tourToSwap[i][0];
        tourToSwap[j][1] = tourToSwap[i][1];
        tourToSwap[i][0] = temp[0];
        tourToSwap[i][1] = temp[1];
    }

    /**
     * Überschreibt das übergebene Element im Array, indem er die Elemente dahinter, um einen
     * aufschiebt
     *
     * @param tourToDelete Array, in dem "gelöscht" werden soll
     * @param idx Element welches gelöscht werden soll
     */
    private static void deleteFromArray(int[][] tourToDelete, int idx) {
        for (int i = idx; i < tourToDelete.length - 1; i++) {
            tourToDelete[i] = tourToDelete[i + 1].clone();
        }
    }

    /**
     * Berechnet die direkte Entfernung zwischen zwei Wegepunkten. Bei der Berechnung wird dabei ein
     * rechtwinkliges Dreieck zwischen Wegepunkt src und dest gespannt. Anschließend wird die Länge
     * der Hypotenuse berechnet und als direkter Weg bezeichnet. Diagonal zu laufen ist hierbei
     * gewollt.
     *
     * @param src - Wegepunkt des Starts
     * @param dest - Wegepunkt des Ziels
     * @return direkte Dinstanz zwischen src und dest
     * @pre <code>src</code> und <code>dest</code> müssen jeweils 2 Werte enthalten
     */
    private static double calcDistanceBetweenWaypoints(int[] src, int[] dest) {
        //Führt die Methode nur aus, wenn die beiden asserts erfüllt sind
        assert src.length == 2;
        assert dest.length == 2;
        //euklidische Methode
        double distance = Math.sqrt(Math.pow(dest[X] - src[X], 2) + Math.pow(dest[Y] - src[Y], 2));
        return distance;
    }

    /**
     * Berechnet den Weg, welcher zurück gelegt werden muss, um vom ersten gesetzten Wegepunkt über 
     * alle weiteren bis zum letzten Wegpunkt zu kommen. Hierbei soll die Methode
     * <code>calcDistanceBetweenWaypoints()</code> verwendet werden.
     *
     * @param tour - mehrere Wegepunkte in einem Array
     * @return Länge des Wanderweges
     */
    public static double calcDistanceOverAllWaypoints(int[][] tour) {
        double distance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            distance += calcDistanceBetweenWaypoints(tour[i], tour[i + 1]);
        }
        return distance;
    }

    /**
     * Liefert die beiden benachbarten Wegepunkte, deren Distanz am kürzesten ist.
     *
     * @param tour - mehrere Wegepunkte in einem Array
     * @return benachbarte Wegepunkte, deren Distanz zueinander minimal ist,
     * <br>
     * ein leeres Array, wenn weniger als zwei Punkte vorhanden
     */
    public static int[][] getClosestWaypoints(int[][] tour) {
        int[][] closestWaypoints = {};
        // Bei nur einem oder keinem Wegpunkt, wird ein leeres Array zurückgegeben
        if (tour.length < 2) {
            return closestWaypoints;
        } else {
            closestWaypoints = new int[2][2];
            closestWaypoints[X] = tour[X].clone();
            closestWaypoints[Y] = tour[Y].clone();
            double distance;
            // Bestimmt kürzeste Distanz von 2 Wegpunkten
            for (int i = 1; i < tour.length - 1; i++) {
                distance = calcDistanceBetweenWaypoints(closestWaypoints[X],
                        closestWaypoints[Y]);
                if (calcDistanceBetweenWaypoints(tour[i], tour[i + 1])
                        < distance) {
                    closestWaypoints[X] = tour[i];
                    closestWaypoints[Y] = tour[i + 1];
                }
            }
        }
        return closestWaypoints;
    }

    /**
     * Liefert eine Tour, die die Wegepunkte der übergebenen Tour von Nord nach Süd sortiert
     * enthält. Sind Punkte auf gleicher Höhe, so werden sie von West nach Ost sortiert. Je größer
     * ein Koordinatenwert, desto südlicher bzw. östlicher liegt er.
     *
     * @param tourToSort zu sortierende Tour (darf nicht verändert werden)
     * @return erstellte Tour mit sortierten Wegepunkten
     */
    public static int[][] createTourSortedNorthSouth(int[][] tourToSort) {
        int[][] tourSorted = new int[tourToSort.length][tourToSort.length];
        boolean swapping = true;

        for (int i = 0; i < tourSorted.length; i++) {
            tourSorted[i] = tourToSort[i].clone();
        }
        // Sortiert das Array aufsteigen nach den Y-Werten
        bubbleSort(tourSorted);
        // Wenn gleiche Y-Werte vorhanden sind, wird aufsteigend nach X-Werten sortiert
        while (swapping) {
            // Sobald nicht mehr getauscht wurde (alle Elemente sind sortiert), 
            // bricht die Schleife ab
            swapping = false;
            for (int j = 0; j < tourSorted.length - 1; j++) {
                if (tourSorted[j][1] == tourSorted[j + 1][1] && tourSorted[j][0]
                        > tourSorted[j + 1][0]) {
                    swapWayPoints(tourSorted, j, j + 1);
                    swapping = true;
                }
            }
        }
        return tourSorted;
    }

    /**
     * Berechnet eine "kürzeste" Tour. Kürzeste bedeutetet, dass ausgehend von einem Startpunkt
     * jeweils der nächste naheliegendste Punkt besucht wird, der noch nicht in der Tour
     * berücksichtigt wurde, bis alle Punkte besucht wurden. Je nach Lage der Punkte kann der
     * Algorithmus in einer ineffizient langen Route resultieren. Ob tatsächlich die kürzeste Route
     * gefunden wurde, kann nicht bestimmt werden.
     *
     * @param tour Tour, für die eine kürzeste Route gefunden werden soll (Array darf nicht
     * verändert werden)
     * @param idxStartPnt Index des Startpunktes für die kürzeste Tour
     * @return eine neue, (hoffentlich) kürzeste Tour
     */
    public static int[][] createShortestTour(int[][] tour, int idxStartPnt) {
        int[][] tourCopy = new int[tour.length][2];
        int[][] shortestTour = new int[tour.length][2];
        int shortCounter = 0;
        int hold = 0;
        int length = tourCopy.length;

        //XXX Kommt wiederholt vor. Eine Hilfsmethode wäre angebracht
        for (int i = 0; i < tourCopy.length; i++) {
            tourCopy[i] = tour[i].clone();
        }

        shortestTour[shortCounter] = tourCopy[idxStartPnt].clone();
        shortCounter++;
        deleteFromArray(tourCopy, idxStartPnt);
        length--;
        
        while (shortCounter < tour.length) {
            for (int i = 1; i < length; i++) {
                // Berechnet die kürzeste Distanz zwischen 2 Wegpunkten
                if (calcDistanceBetweenWaypoints(shortestTour[shortCounter - 1], tourCopy[i])
                        < calcDistanceBetweenWaypoints(shortestTour[shortCounter - 1],
                                tourCopy[hold])) {
                    // Merkt sich den mit der kürzesten Distanz
                    hold = i;
                }
            }
            // Schreibt ihn in das auszugeben Array und löscht den Wegpunkt aus der Kopie
            // -> Verhindert, dass die Tour sich im Kreis dreht, weil 2 Wegpunkte den kürzesten
            // Abstand zueinander haben
            shortestTour[shortCounter] = tourCopy[hold];
            deleteFromArray(tourCopy, hold);
            shortCounter++;
            length--;
            hold = 0;
        }
        return shortestTour;
    }

    /**
     * Liefert eine Tour mit den Wegepunkten der übergebenen Tour in der Reihenfolge, wie sie durch
     * die im Parameter gegebenen Indizes bestimmt werden.
     *
     * @param tour Tour, aus der eine neue gestaltet werden soll (darf nicht verändert werden)
     * @param indices Indizes der zu verwendenden Wegepunkte
     * @return Tour mit den Wegepunkten in der gegebenen Reihenfolge
     */
    public static int[][] createTourWithOrder(int[][] tour, int[] indices) {
        int[][] tourOrdered = new int[indices.length][2];
        
        for (int i = 0; i < indices.length; i++) {
            tourOrdered[i] = tour[indices[i]].clone();
        }
        return tourOrdered;
    }

    /**
     * Konvertiert einen Wegepunkt in einen String. Die Werte werden in runde Klammern
     * eingeschlossen und mit Schrägstrich voneinander getrennt: <br> {4, 10} -> (4/10)
     *
     * @param waypoint - Wegepunkt, welcher in einen String überführt werden soll
     * @return der Wegepunkt in Stringdarstellung
     */
    private static String waypointToString(int[] waypoint) {
        return "(" + waypoint[X] + "/" + waypoint[Y] + ")";
    }

    /**
     * Stellt eine Tour bestehend aus mehreren Wegepunkten in einem String dar. Hierbei wird die
     * Methode <code>waypointToString</code> genutzt, um jeden einzelnen Wegepunkt in das gewünschte
     * Format zu bringen. <br>
     * Die Tour wird mit eckigen Klammern umgeben und die Punkte mit Bindestrich-Größer voneinander
     * getrennt:<br>
     * {{}} -> "[]" <br> {{1,4},{4,10},{12,18}} -> "[(1/4) -> (4/10) -> (12/18)]"
     *
     * @param tour - mehrere Wegepunkte in einem Array
     * @return die Tour als String
     */
    public static String tourToString(int[][] tour) {
        String tourToString = "";
        boolean firstRun = true; //XXX Ist nicht notwendig. Man kann auch mit der Laufvariable der Schleife arbeiten
        
        for (int[] i : tour) {
            // Beim ersten Durchlauf (vor dem ersten Wegpunkt) soll kein Pfeil "->" stehen
            if (firstRun) {
                tourToString += waypointToString(i);
                firstRun = false;
            } else {
                tourToString += " -> " + waypointToString(i);
            }
        }
        // Tour soll von eckigen Klammern umschlossen sein
        return "[" + tourToString + "]";
    }
}
