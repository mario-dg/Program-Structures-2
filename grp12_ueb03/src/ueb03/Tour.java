/**
 * Auf einer zweidimensionalen Karte sollen Wanderwege bestehend aus Wegepunkten
 * erstellt und anschließend verschiedene Kennzahlen ermittelt werden können.
 */
package ueb03;

/**
 * Aufruf der Methoden aus TourElememt mit Überprüfung ob eventuell keine Liste vorhanden ist, bzw
 * diese leer ist.
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Tour {

    //TODO DONE 
    /**
     * Kopf der Liste
     */
    private TourElement tour;

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

    /**
     * Untersucht ob eine Liste leer ist
     *
     * @return boolean ob die Liste leer ist
     */
    public boolean isEmpty() {
        return tour == null;
    }

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

}
