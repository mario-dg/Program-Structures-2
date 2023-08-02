package ueb05;

/**
 * Klasse für die Figur Quadrat
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Square extends Figure {

    //<editor-fold defaultstate="collapsed" desc="Instance Variable">
    /**
     * Instance Variable - Seite
     */
    final double edge;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 2 Parameter">
    /**
     * Konstruktor 2 Parameter
     * @param color Farbe der Figur
     * @param edge Seite des Quadrats
     */
    Square(Color color, double edge) {
        this.edge = edge;
        this.color = color;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getPerimeter">
    /**
     * Berechnet den Umfang des Kreises
     * @return double, Umfang
     */
    @Override
    public double getPerimeter() {
        return 4 * edge;
    }
 //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method getArea">
    /**
     * Berechnet die Fläche des Kreises
     * @return double, Flächeninhalt
     */
    @Override
    public double getArea() {
        return Math.pow(edge, 2);
    }
 //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method getDescription">
    /**
     * Schreibt alle Form spezifischen Daten in einen String
     * @return String, mit allen wichtigen Daten
     */
    @Override
    public String getDescription() {
        return String.format("Quadrat  mit Kantenlänge  ( %5.2f),", edge);
    }
     //</editor-fold>
}
