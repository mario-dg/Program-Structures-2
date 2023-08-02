package ueb05;

/**
 * Klasse für die Figur Kreis
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Circle extends Figure{
    
    //<editor-fold defaultstate="collapsed" desc="Instance Variable">
    /**
     * Instance Variable - Durchmesser
     */
    final double diameter;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor 2 Parameter">
    /**
     * Konstruktor 2 Parameter
     * @param color Farbe der Figur
     * @param diameter Durchmesser des Kreises
     */
    Circle(Color color, double diameter){
        this.color = color;
        this.diameter = diameter;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method getPerimeter">
    /**
     * Berechnet den Umfang des Kreises
     * @return double, Umfang
     */
    @Override
    public double getPerimeter(){
        return Math.PI * diameter;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method getArea">
    /**
     * Berechnet die Fläche des Kreises
     * @return double, Flächeninhalt
     */
    @Override
    public double getArea() {
        return Math.PI * Math.pow(diameter/2 ,2);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method getDescription">
    /**
     * Schreibt alle Form spezifischen Daten in einen String
     * @return String, mit allen wichtigen Daten
     */
    @Override
    public String getDescription() {
        return String.format("Kreis    mit Durchmesser  ( %.2f),", diameter);
    }
    //</editor-fold>
    
}
