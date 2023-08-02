package ueb05;

/**
 * Klasse für die Figur Dreieck
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Triangle extends Figure {

    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    /**
     * Instanz Variablen - 3 Seiten des Dreiecks
     */
    final double edge_1;
    final double edge_2;
    final double edge_3;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 4 Parameters">
    /**
     * Konstruktor mit 4 Parametern
     * @param color Farbe, die die Form hat
     * @param edge_1 Seite 1
     * @param edge_2 Seite 2
     * @param edge_3 Seite 3
     */
    Triangle( Color color, double edge_1, double edge_2, double edge_3) {
        this.edge_1 = edge_1;
        this.edge_2 = edge_2;
        this.edge_3 = edge_3;
        this.color = color;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getPerimeter">
    /**
     * Berechnet den Umfang des Dreiecks
     * @return double, Umfang des Dreiecks
     */
    @Override
    public double getPerimeter() {
        return edge_1 + edge_2 + edge_3;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getArea">
    /**
     * Berechnet die Fläche
     * @return double, Fläche des Dreiecks
     */
    @Override
    public double getArea() {
        //FIXME DONE Formel ueberpruefen
        double halfPerimeter = this.getPerimeter()/2;
        return Math.sqrt(halfPerimeter*(halfPerimeter-edge_1)*(halfPerimeter-edge_2)*(halfPerimeter-edge_3));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDescription">
    /**
     * Schreibt alle Form spezifischen Daten in einen String
     * @return String, mit allen wichtigen Daten
     */
    @Override
    public String getDescription() {
        return String.format("Dreieck  mit Kantenlängen ( %5.2f, %5.2f, %5.2f),",
                edge_1, edge_2, edge_3);
    }
//</editor-fold>
}
