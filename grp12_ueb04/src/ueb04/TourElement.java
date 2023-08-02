package ueb04;

/**
 * Rekursives Zusammenfügen der Wegpunkte zu einer Liste und Bestimmung der Eigenschaften der
 * Elemente
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class TourElement {

    //<editor-fold defaultstate="collapsed" desc="Constructor Default">
    /**
     * Defaul-Konstruktor
     *
     * @deprecated
     */
    TourElement() {
        waypoint = null;
        next = null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Contructor 1 Parameter">
    /**
     * Konstruktor, der den Wegpunkt setzt
     *
     * @param waypoint Wegpunkt, der gesetzt werden soll
     */
    TourElement(Waypoint waypoint) {
        this(waypoint, null);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 2 Paramters">
    /**
     * Konstruktor, der den Wegpunkt-Konstruktor mit übergebenem Wegpunkt setzt und das nächste
     * Listenelement setzt
     *
     * @param next Listenelement, dass als nächstes gesetzt werden soll
     */
    TourElement(Waypoint waypoint, TourElement next) {
        assert waypoint != null;
        this.waypoint = waypoint;
        this.next = next;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="instance Variable waypoint">
    /**
     * Nutzlast der Liste
     */
    private Waypoint waypoint;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="instance Variable next">
    /**
     * Verknüpfung zum nächsten Listenelement
     */
    private TourElement next;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method isEqual">
    /**
     * Prüft, ob zwei Touren identisch sind
     *
     * @param other Tour, mit der verglichen werden soll
     * @return boolean, ob gleich oder nicht
     */
    public boolean isEqual(TourElement other) {
        //XXX Versucht mal dies ohne if und else zu schreiben (geht auch in einen langen return statement)
        if (other != null) {
            boolean equal = this.waypoint.isEqual(other.waypoint);
            if (this.hasNext() && other.hasNext()) {
                if (equal) {
                    return this.next.isEqual(other.next);
                } else {
                    return false;
                }
            } else {
                if (!(this.hasNext() ^ other.hasNext())) {
                    return equal;
                } else {
                    return false;
                }
            }

        } else {
            return false;
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getLastElement">
    /**
     * Sucht das letzte Element einer Liste raus
     *
     * @return TourElement, letztes Element
     */
    private TourElement getLastElement() {
        if (this.hasNext()) {
            return this.next.getLastElement();
        } else {
            return this;
        }

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method concat">
    /**
     * Fügt eine Liste ans Ende einer anderen Liste
     *
     * @param other, Liste die ans Ende angefügt wird
     * @return TourElement, zusammengefügte Liste
     */
    public TourElement concat(TourElement other) {
        this.getLastElement().next = other;
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Help-Method calcShortestDistance">
    /**
     * Berechnet die kürzeste Distanz zu einem Wegpunkt in einer Liste
     *
     * @param distance, übergibt die momentan kürzeste Distanz
     * @param waypoint, Wegpunkt, von dem die Distanz aus berechnet werden soll
     * @return double, kürzeste Distanz
     */
    private int calcShortestDistance(double distance, Waypoint waypoint, int index, int shortestindex) {
        // Prüfung, ob momentan kürzeste Distanz immernoch die kürzeste ist
        if (distance > waypoint.calcDistance(this.waypoint)) {
            distance = waypoint.calcDistance(this.waypoint);
        shortestindex = index;
            
        }
        //rekursiver Aufruf, bis Ende der Liste erreicht
        if (this.hasNext()) {
            index++;
            return this.next.calcShortestDistance(distance, waypoint, index, shortestindex);
        }
        return shortestindex;
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method getIdxOfClosestWaypoint">
    /**
     * Sucht den Index eines Wegpunktes, mit der kürzesten Distanz zum übergebenen Wegpunkt
     *
     * @param waypoint, Wegpunkt von dem aus die Distanz berechnet wird
     * @return int, Index vom Wegpunkt mit der kürzesten Distanz
     */
    public int getIdxOfClosestWaypoint(Waypoint waypoint) {
        //TODO DONE Hier geht ihr zweimal durch die Liste. Versucht eure Hilfsmethode so umzuschreiben, dass ein Index zurückgegeben wird
        return this.calcShortestDistance(Double.MAX_VALUE, waypoint, 0, 0);

    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getWaypoint">
    /**
     * Getter-Methode für den Wegpunkt
     *
     * @return waypoint der aktuelle Wegpunkt
     */
    public Waypoint getWaypoint() {
        return waypoint;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getNext">
    /**
     * Getter-Methode für das nächste Listenelement
     *
     * @return TourElement das nächste ListenElement
     */
    public TourElement getNext() {
        return next;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method setWaypoint">
    /**
     * Setter-Methode für den Wegpunkt
     *
     * @param waypoint
     * @deprecated
     */
    public void setWaypoint(Waypoint waypoint) {
        this.waypoint = waypoint;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method setNext">
    /**
     * Setter-Methode für das nächste Listenelement
     *
     * @param next
     * @deprecated
     */
    public void setNext(TourElement next) {
        this.next = next;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method hasNext">
    /**
     * Überprüft ob auf das aktuelle Listenelement ein weiteres folgt
     *
     * @return boolean ob es ein nächstes Listenelement gibt
     */
    public boolean hasNext() {
        // Wenn das nächste Listenelement null ist, ist das Ende der Liste erreicht
        return null != this.next;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getNoOfWaypoints">
    /**
     * Berechnet die Anzahl der Wegpunkte in einer Liste
     *
     * @return integer Anzahl der Wegpunkte
     */
    public int getNoOfWaypoints() {
        int counter;

        /*
        Solange das Ende der Liste nicht erreicht ist, wird diese Methode erneut für das
        nächste Element aufgerufen. Am Ende werden, bei der Rückgabe in die vorherigen
        Aufrufe, die Listenelemente gezählt
         */
        if (this.hasNext()) {
            counter = this.next.getNoOfWaypoints() + 1;
        } else {
            counter = 1;
        }
        return counter;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method contains">
    /**
     * Untersucht ob ein Wegpunkt schon in der Liste enthalten ist
     *
     * @param waypoint Wegpunkt nach dem gesucht wird
     * @return boolean ob der Wegpunkt schon enthalten ist
     */
    public boolean contains(Waypoint waypoint) {
        /*
        Die Methode wird immer wieder für das Folge-Element aufgerufen bist das letze oder
        das übereinstimmende Listenelement gefunden wurde
         */
        if (!waypoint.isEqual(this.waypoint)) {
            if (this.hasNext()) {
                return this.next.contains(waypoint);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method addStart">
    /**
     * Fügt einen Wegpunkt am Anfang der Liste hinzu
     *
     * @param waypoint Wegpunkt der hinzugefügt werden soll
     * @return TourElement das Listenelement, das nun am den neuen Anfang der Liste beschreibt
     */
    public TourElement addStart(Waypoint waypoint) {
        // Überprüfung ob Wegpunkt gültig ist
        if (waypoint != null) {
            TourElement te = new TourElement();
            te.waypoint = waypoint;
            te.next = this;
            // te wird zurückgegeben, da es den neuen Anfang der Liste beschreibt
            return te;
        } else {
            return this;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method calcDistance">
    /**
     * Berechnet die Gesamtlänge der Tour
     *
     * @return double die Länge der Tour
     */
    public double calcDistance() {
        double distance;

        /*
        Bis das Ende der Liste erreicht wird, wird diese Methode für das jeweils nächste Element
        ausgeführt. Zum Schluss werden die Distanzen zwischen den Wegpunkten bei der Rückgabe
        in die aufrufenden Methoden addiert
         */
        if (this.hasNext()) {
            distance = this.waypoint.calcDistance(this.next.waypoint);
            distance += this.next.calcDistance();
        } else {
            distance = 0;
        }
        return distance;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method append">
    /**
     * Hängt einen Wegpunkt am Anfang der Liste ein
     *
     * @param waypoint Wegpuntk der angehängt werden soll
     * @return TourElement das Listenelement, das den Anfang der Liste beschreibt
     */
    public TourElement append(Waypoint waypoint) {
        // Überprüfung ob Wegpunkt gültig ist
        if (waypoint != null) {
            if (!this.hasNext()) {
                // Neues Element wird anghängt
                this.next = new TourElement();
                this.next.setWaypoint(waypoint);
            } else {
                // Methode wird wieder aufgerufen, bis Stelle Erreicht wurde an der etwas gemacht
                // werden soll
                this.next = this.next.append(waypoint);
            }
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getWaypointAt">
    /**
     * Getter-Methode für einen Wegpunkt in der Liste
     *
     * @param index Stelle des Wegpunktes in der Liste
     * @return Waypoint den Wegpunkt an der Stelle index
     */
    public Waypoint getWaypointAt(int index) {
        /*
        Das Aufrufen der Methode wird so oft wiederholt, bis die Stelle erreicht wird an der der
        Wegpunkt abgefragt wird. Dieser wird durch alle Methodenaufrufe bis zum Ersten zurückgeben
         */
        if (index == 0) {
            return this.waypoint;
        } else {
            if (this.hasNext()) {
                return this.next.getWaypointAt(--index);
            } else {
                return null;
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method insertAt">
    /**
     * fügt einen Wegpunkt in die Liste ein
     *
     * @param index die Stelle an der eingefügt werden soll
     * @param waypoint Wegpunkt der eingefügt werden soll
     * @return TourElement das Listenelement, das den Anfang der Liste beschreibt
     */
    public TourElement insertAt(int index, Waypoint waypoint) {
        TourElement te2 = new TourElement();
        // Überprüfung ob Wegpunkt gültig ist
        if (waypoint != null) {
            // Wenn index 0 ist wird einfach am Anfang eingefügt
            if (index == 0) {
                return this.addStart(waypoint);
                // Wenn der Index der Länge der Liste entspricht, wird das Element am Ende angehängt
            } else if (index == this.getNoOfWaypoints()) {
                this.append(waypoint);
                // Wenn sich die Stelle zum einfügen "mitten" in der Liste befindet, wird das Element
                // dort eingefügt
            } else if (this.hasNext()) {
                if (this.next.waypoint == this.getWaypointAt(index)) {
                    te2.next = this.next;
                    this.next = te2;
                    te2.waypoint = waypoint;
                    // Wiederholter Aufruf dieser Methode bis die Stelle, an der eingefügt werden soll
                    // erreicht wurde
                } else {
                    this.next.insertAt(--index, waypoint);
                }
            }
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method removeAt">
    /**
     * Entfernt ein Element aus der Liste
     *
     * @param index Stelle, an der das zulöschende Element steht
     * @return TourElement das Element, das den Anfang der Liste beschreibt aus der gelöscht wurde
     */
    public TourElement removeAt(int index) {

        if (index == 0) {
            return this.next;
        } else if (this.hasNext()) {
            this.next = this.next.removeAt(--index);
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method toString">
    /**
     * Wandelt eine Liste in einen String um
     *
     * @return String, der die Liste enthält
     */
    public String toString() {
        String waypointString;

        // Formatierung nach Vorgabe
        if (this.hasNext()) {
            waypointString = this.waypoint.toString() + " -> " + this.next.toString();
        } else {
            waypointString = this.waypoint.toString();
        }
        return waypointString;
    }
//</editor-fold>
}
