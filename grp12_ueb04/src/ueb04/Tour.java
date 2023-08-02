/**
 * Auf einer zweidimensionalen Karte sollen Wanderwege bestehend aus Wegepunkten
 * erstellt und anschließend verschiedene Kennzahlen ermittelt werden können.
 */
package ueb04;

/**
 * Aufruf der Methoden aus TourElememt mit Überprüfung ob eventuell keine Liste vorhanden ist, bzw
 * diese leer ist.
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Tour {
    //TODO DONE getNoOfWaypoints Variable anlegen da wo sich die Liste nicht verändert aber im Schleifenkopft immer neu berechnet wird

    //<editor-fold defaultstate="collapsed" desc="Constructor Default">
    /**
     * Default-Konstruktor
     *
     * @deprecated
     */
    Tour() {
        tour = null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor one Parameter">
    /**
     * Konstruktor, der aus einem 2D Array, eine Liste erzeugt
     *
     * @param waypoints Array, aus dem Liste erzeugt wird
     */
    Tour(int[][] waypoints) {
        if (waypoints.length > 0) {
            int lastIndex = waypoints.length - 1;
            Waypoint wp = new Waypoint(waypoints[lastIndex][0], waypoints[lastIndex][1]);
            TourElement elem = new TourElement(wp);
            for (int i = lastIndex - 1; i >= 0; i--) {
                wp = new Waypoint(waypoints[i][0], waypoints[i][1]);
                elem = elem.addStart(wp);
            }
            tour = elem;
        } else {
            tour = null;
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="instance Variable TourElement">
    /**
     * Kopf der Liste
     */
    private TourElement tour;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method isEqual">
    /**
     * Prüft, ob zwei Listen identisch sind
     *
     * @param other, Liste mit der verglichen wird
     * @return boolean, ob gleich oder nicht
     */
    public boolean isEqual(Tour other) {
        if (other == null) {
            return false;
        }
        // Sind beide Listen nicht vorhanden -> identisch
        if (this.isEmpty() && other.isEmpty()) {
            return true;
        } else {
            if (!this.isEmpty()) {
                return this.tour.isEqual(other.tour);
            } else {
                return false;
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method copy">
    /**
     * Kopiert eine Liste
     *
     * @return Tour, kopierte Liste
     */
    public Tour copy() {
        Tour tourCopy = new Tour();
        int counter = 0;
        int length = this.getNoOfWaypoints();
        do {
            tourCopy.append(this.getWaypointAt(counter));
            counter++;
        } while (counter <= length);
        return tourCopy;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createConcatenatedTour">
    /**
     * Fügt eine Liste ans Ende einer anderen an
     *
     * @param other, anzufügende Liste
     * @return Tour, zusammengefügte Liste
     */
    public Tour createConcatenatedTour(Tour other) {
        if (other == null) {
            return this;
        } else if (this.isEmpty()) {
            return other.copy();
        } else {
            Tour concatTour = this.copy();
            concatTour.tour = concatTour.tour.concat(other.tour);
            return concatTour;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createTourWithOrder">
    /**
     * Sortiert eine Liste nach vorgegebenen Indizes
     *
     * @param indices, Array mit den Indizes
     * @return Tour, sortierte Liste
     */
    public Tour createTourWithOrder(int[] indices) {
        Tour orderedTour = new Tour();

        for (int i : indices) {
            orderedTour.append(this.getWaypointAt(i));
        }
        return orderedTour;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createPopularTour">
    /**
     * Erstellt eine Tour aus Wegpunkten, die in zwei anderen enthalten sind
     *
     * @param other, eine Liste
     * @return Tour, Liste mit populären Wegpunkten
     */
    public Tour createPopularTour(Tour other) {
        Tour publicTour = new Tour();
        int counter = 0;
        int length = this.getNoOfWaypoints();

        while (counter < length) {
            Waypoint currentWaypoint = this.getWaypointAt(counter);
            if (other.contains(currentWaypoint)) {
                publicTour.append(currentWaypoint);
            }
            counter++;
        }

        return publicTour;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createTourWithoutDuplicates">
    /**
     * Erstellt eine Tour und entfernt doppelte Wegpunkte
     *
     * @return Tour, Liste ohne doppelte Wegpunkte
     */
    public Tour createTourWithoutDuplicates() {
        Tour noDuplicates = new Tour();
        int counter = 0;
        int length = this.getNoOfWaypoints();

        while (counter < length) {
            Waypoint currentWaypoint = this.getWaypointAt(counter);
            if (!noDuplicates.contains(currentWaypoint)) {
                noDuplicates.append(currentWaypoint);
            }
            counter++;
        }
        return noDuplicates;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createShortestTour">
    /**
     * Erstellt eine Tour, bei der die Distanz zwischen den einzelnen Wegpunkten minimal ist
     *
     * @param idxStartPnt, Startpunkt der Liste
     * @return Tour, kürzeste Tour
     */
    public Tour createShortestTour(int idxStartPnt) {
        Tour tourCopy = this.copy();
        Tour shortestTour = new Tour();
        int counter = 0;

        //Startpunkt muss valide sein
        if (idxStartPnt >= 0 && idxStartPnt < this.getNoOfWaypoints()) {
            shortestTour.append(tourCopy.getWaypointAt(idxStartPnt));
            tourCopy.removeAt(idxStartPnt);
            while (tourCopy.getNoOfWaypoints() > 0) {
                int closestWaypoint = tourCopy.tour.getIdxOfClosestWaypoint(
                        shortestTour.getWaypointAt(counter));
                shortestTour.append(tourCopy.getWaypointAt(closestWaypoint));
                counter++;
                tourCopy.removeAt(closestWaypoint);
            }
        }
        return shortestTour;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createUnion">
    /**
     * Erstellt eine Liste, aus 2 Listen, ohne doppelte Wegpunkte und mit der kürzesten Distanz
     *
     * @param other, eine der beiden Listen
     * @return Tour, Liste wie oben erwähnt
     */
    public Tour createUnion(Tour other) {
        //Zuerst werden beide Listen zusammengefügt, dann werden doppelte entfernt und dann
        //wird nach der kürzesten Tour sortiert
        return this.createConcatenatedTour(other).
                createTourWithoutDuplicates().
                createShortestTour(0);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method createNewTour">
    /**
     * Erstellt eine neue Tour
     *
     * @param waypoint Wegpunkt, der am Anfang der Liste/Tour steht
     * @return Tour die neue Liste
     */
    private Tour createNewTour(Waypoint waypoint) {
        tour = new TourElement();
        tour.setWaypoint(waypoint);
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method isEmpty">
    /**
     * Untersucht ob eine Liste leer ist
     *
     * @return boolean ob die Liste leer ist
     */
    public boolean isEmpty() {
        return tour == null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getStartPoint">
    /**
     * Getter-Methode für den ersten Wegpunkt
     *
     * @return Waypoint der Wegpunkt des ersten Listenelemets
     */
    public Waypoint getStartPoint() {
        if (!this.isEmpty()) {
            return this.tour.getWaypointAt(0);
        } else {
            return null;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getNoOfWaypoints">
    /**
     * gibt die Anzahl der enthaltenen Wegpunkte
     *
     * @return integer die Anzahl der enthaltenen Wegpunkte
     */
    public int getNoOfWaypoints() {
        if (!this.isEmpty()) {
            return this.tour.getNoOfWaypoints();
        } else {
            return 0;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method calcDistance">
    /**
     * Berechnet die gesamte Länge einer Tour über alle Wegpunkte
     *
     * @return double die Länge einer Tour
     */
    public double calcDistance() {
        if (!this.isEmpty()) {
            return this.tour.calcDistance();
        } else {
            return 0.00;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getWaypointAt">
    /**
     * Liefert den Wegpunkt an einer Stelle
     *
     * @param index Stelle des Wegpunktes
     * @return Waypoint Wegpunkt an der Stelle index
     */
    public Waypoint getWaypointAt(int index) {
        if (!this.isEmpty()) {
            return this.tour.getWaypointAt(index);
        } else {
            return null;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method contains">
    /**
     * Überprüft ob Wegpunkt in der Tour enthalten ist
     *
     * @param waypoint Wegpunkt nach dem gesucht wird
     * @return boolean ob der Wegpunkt enthalten ist
     */
    public boolean contains(Waypoint waypoint) {
        if (!this.isEmpty()) {
            return this.tour.contains(waypoint);
        } else {
            return false;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method addStart">
    /**
     * Fügt ein Element an den Anfang der Tour an
     *
     * @param waypoint Wegpunkt des einzufügenden Elementes
     * @return Tour Die Liste mit dem eingefügten Element
     */
    public Tour addStart(Waypoint waypoint) {
        if (!this.isEmpty()) {
            this.tour = tour.addStart(waypoint);
        } else {
            createNewTour(waypoint);
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method append">
    /**
     * Hängt ein Element an das Ende der Tour an
     *
     * @param waypoint Wegpunkt des einzufügenden Elementes
     * @return Tour Die Liste mit dem angehängten Element
     */
    public Tour append(Waypoint waypoint) {
        if (!this.isEmpty()) {
            this.tour.append(waypoint);
        } else {
            createNewTour(waypoint);
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method insertAt">
    /**
     * Fügt ein Element in die Tour ein
     *
     * @param index Stelle an der eingefügt wird
     * @param waypoint Wegpunkt des einzufügenden Elementes
     * @return Tour Die Liste mit dem eingefügten Element
     */
    public Tour insertAt(int index, Waypoint waypoint) {
        if (!this.isEmpty()) {
            this.tour = this.tour.insertAt(index, waypoint);
        } else {
            createNewTour(waypoint);
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method removeAt">
    /**
     * Löscht ein Element aus der Tour
     *
     * @param index Stelle an der gelöscht wird
     * @return Tour Die Liste ohne das gelöschte Element
     */
    public Tour removeAt(int index) {
        if (!this.isEmpty()) {
            this.tour = this.tour.removeAt(index);
            return this;
        } else {
            return null;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method toString">
    /**
     * Wandelt die Tour in einen String um
     *
     * @return String, der die Tour visuell veranschaulicht
     */
    public String toString() {

        if (!this.isEmpty()) {
            return "[" + this.tour.toString() + "]";
        } else {
            return "[]";
        }
    }
//</editor-fold>

}
