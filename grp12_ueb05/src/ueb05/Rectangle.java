package ueb05;

/**
 * Klasse für die Figur Rechteck
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Rectangle extends Figure {

    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    /**
     * Instance Variables - 2 Seiten des Rechtecks
     */
    final double edge_1;
    final double edge_2;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 3 Parameter">
    /**
     * Konstruktor mit 3 Parametern
     * @param color Farbe der Figur
     * @param edge_1 Seite 1
     * @param edge_2 Seite 2
     */
    Rectangle( Color color, double edge_1, double edge_2) {
        this.edge_1 = edge_1;
        this.edge_2 = edge_2;
        this.color = color;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getPerimeter">
    /**
     * Berechnet den Umfang des Rechtecks
     * @return double, Umfang
     */
    @Override
    public double getPerimeter() {
        return 2 * (edge_1 + edge_2);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getArea">
    /**
     * Berechnet die Fläche des Rechtecks
     * @return double, Flächeninhalt
     */
    @Override
    public double getArea() {
        return edge_1 * edge_2;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getDescription">
    /**
     * Schreibt alle Form spezifischen Daten in einen String
     * @return String, mit allen wichtigen Daten
     */
    @Override
    public String getDescription() {
        return String.format("Rechteck mit Kantenlängen ( %5.2f, %5.2f),", edge_1, edge_2);
    }
    //</editor-fold>
}
