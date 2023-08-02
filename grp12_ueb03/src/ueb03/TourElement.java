package ueb03;

/**
 * Rekursives Zusammenfügen der Wegpunkte zu einer Liste und Bestimmung der Eigenschaften der 
 * Elemente
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class TourElement {

    //TODO DONE
    /**
     * Nutzlast der Liste
     */   
    private Waypoint waypoint;
    
    /**
     * Verknüpfung zum nächsten Listenelement
     */
    private TourElement next;

    /**
     * Getter-Methode für den Wegpunkt
     *
     * @return waypoint der aktuelle Wegpunkt
     */
    public Waypoint getWaypoint() {
        return waypoint;
    }

    /**
     * Getter-Methode für das nächste Listenelement
     *
     * @return TourElement das nächste ListenElement
     */
    public TourElement getNext() {
        return next;
    }

    /**
     * Setter-Methode für den Wegpunkt
     *
     * @param waypoint
     */
    public void setWaypoint(Waypoint waypoint) {
        this.waypoint = waypoint;
    }

    /**
     * Setter-Methode für das nächste Listenelement
     *
     * @param next
     */
    public void setNext(TourElement next) {
        this.next = next;
    }

    /**
     * Überprüft ob auf das aktuelle Listenelement ein weiteres folgt
     *
     * @return boolean ob es ein nächstes Listenelement gibt
     */
    public boolean hasNext() {
        // Wenn das nächste Listenelement null ist, ist das Ende der Liste erreicht
        return null != this.next;
    }

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

    /**
     * Fügt einen Wegpunkt am Anfang der Liste hinzu
     *
     * @param waypoint Wegpunkt der hinzugefügt werden soll
     * @return TourElement das Listenelement, das nun am den neuen Anfang der Liste beschreibt
     */
    public TourElement addStart(Waypoint waypoint) {
        //TODO DONE unveraendert zurueckgeben wenn waypoint null
        // Überprüfung ob Wegpunkt gültig ist
        if (waypoint != null) {
        TourElement te = new TourElement();
            te.waypoint = waypoint;
            te.next = this;
        // te wird zurückgegeben, da es den neuen Anfang der Liste beschreibt
        return te;
        }else{
            return this;
        }
    }

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
}
