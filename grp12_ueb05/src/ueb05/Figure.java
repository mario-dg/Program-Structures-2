package ueb05;

/**
 * Superklasse für alle Figuren. Beinhaltet wichtige Methoden, welche von den Subklassen überschrieben
 * werden
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Figure {

    //<editor-fold defaultstate="collapsed" desc="Instance Variable">
    /**
     * Instance Variable - Farbe der Figur
     */
    protected Color color;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 1 Parameter">
    /**
     * Konstruktor mit 1 Parameter
     *
     * @param color Farbe die gesetzt wird
     */
    Figure(Color color) {
        this.color = color;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor Default">
    /**
     * Default-Constructor
     */
    Figure() {
        this(null);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getInstanceVariable">
    /*Methoden die nur zum Überschreiben da sind.
    * Wird in den Subklassen überschrieben
    */
    /**
     * Methoden für die Instanzvariablen der Subklassen
     * @return
     */
    public double getArea() {
        assert false;
        return 0;
    }
    
    public double getPerimeter() {
        assert false;
        return 0;
    }
    
    public String getDescription() {
        return "";
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method compare">
    /**
     * Vergleicht 2 Figuren auf gleichen Flächeninhalt
     * @param other Figur mit der verglichen wird
     * @return 0 - gleiche Fläche; 1 - übergebene Figur ist kleiner; -1 - übergebene Figur ist größer
     */
    public int compare(Figure other) {
        final double EPSILON = 0.0001;
        //Vergleich von double Werten. Differenz über EPSILON nötig
        if ((this.getArea() - other.getArea()) > EPSILON) {
            return 1;
        } else if ((this.getArea() - other.getArea()) < -EPSILON) {
            return -1;
        } else {
            return 0;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method equals">
    /**
     * Prüft, ob 2 Figuren gleich sind
     * @param obj, welches verglichen wird
     * @return boolean, ob Figuren gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        return (obj != null) && (obj instanceof Figure)
                && (this.compare((Figure) obj) == 0);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method toString">
    /**
     * Schreibt alle Werte einer Figur formatiert in einen String
     * @return String, mit allen Werten
     */
    @Override
    public String toString() {
        return String.format("%-16s %-52s Umfang: %6.2f, Fläche: %6.2f", 
                this.color == null ? "unknown" : this.color.toString(),
                this.getDescription(), this.getPerimeter(), this.getArea());
    }
//</editor-fold>
}
