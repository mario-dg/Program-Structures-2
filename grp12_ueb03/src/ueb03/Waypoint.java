package ueb03;

/**
 * Eigenschaften der Wegpunkte und zusammensetzen dieser aus X und Y Koordinaten
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Waypoint {

    //TODO DONE Kommentar
    /**
     * X-Koordinate des Wegpunktes
     */
    private int X;
    
    /**
     * Y-Koordinate des Wegpunktes
     */
    private int Y;

    /**
     * Getter-Methode von X
     *
     * @return X
     */
    public int getX() {
        return X;
    }

    /**
     * Getter-Methode von Y
     *
     * @return Y
     */
    public int getY() {
        return Y;
    }

    /**
     * Setter-Methode für X und Y
     *
     * @param x Wert auf den X gesetzt werden soll
     * @param y Wert auf den Y gesetzt werden soll
     */
    public void setXY(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Berechnet die Distanz zwischen dem aktuellen Wegpunkt und einem anderen
     *
     * @param other der "andere" Wegpunkt
     * @return double Distanz zwischen den 2 Punkten
     */
    public double calcDistance(Waypoint other) {
        // Euklidische Distanz
        return (Math.sqrt(Math.pow(other.X - this.X, 2)
                + Math.pow(other.Y - this.Y, 2)));
    }

    /**
     * Überprüft ob der aktuelle Wegpunkt gleich zu einem anderen (übergebenen) Wegpunkt ist
     *
     * @param other der "andere" Wegpunkt
     * @return boolean ob die Punkte gleich sind oder nicht
     */
    public boolean isEqual(Waypoint other) {
        //TODO DONE Epsilon
        return (((other != null) && (this != null))
                && ((Math.abs(this.X - other.X) == 0) 
                && (Math.abs(this.Y - other.Y) == 0)));
    }

    /**
     * wandelt den aktuellen Wegpunkt in ein 1-Dimensionales Array um
     *
     * @return intArray das 1-Dimensionale Array, das die Werte des Wegpunktes enthält
     */
    public int[] toArray() {
        int[] wp = new int[2];
        wp[0] = this.X;
        wp[1] = this.Y;
        return wp;
    }

    /**
     * Wandelt den aktuellen Wegpunkt in einen String um
     *
     * @return String, der die Werte des Wegpunktes enthält
     */
    public String toString() {
        // Formatiert nach Vorgabe
        return "(" + this.X + "/" + this.Y + ")";
    }
}
