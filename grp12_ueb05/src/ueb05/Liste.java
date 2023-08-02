/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb05;

/**
 * Klasse zum abfangen der Leeren Liste
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Liste {

    //<editor-fold defaultstate="collapsed" desc="Instance Variable">
    /**
     * Instance Variable - Start der Liste
     */
    Element start;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor Default">
    /**
     * Default Konstruktor
     */
    Liste() {
        start = null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor Figure">
    /**
     * Konstruktor mit beliebig vielen Figuren
     *
     * @param figures Beliebig viele Figuren
     */
    Liste(Figure... figures) {
        start = null;
        if (figures.length > 0) {
            start = new Element(figures[figures.length - 1]);
            for (int i = figures.length - 2; i >= 0; i--) {
                start = new Element(figures[i], start);
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method insert">
    /**
     * Fügt eine Figur in die Liste ein
     *
     * @param figure einzufügende Figur
     * @return Liste, mit eingefügter Figur
     */
    public Liste insert(Figure figure) {
        if (start == null) {
            this.start = new Element(figure);
        } else {
            this.start = this.start.insert(figure);
        }
        return this;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getValues">
    /**
     * Schreibt alle Figuren in ein Array
     *
     * @return Array mit allen Figuren
     */
    public Figure[] getValues() {
        if (this.start == null) {
            return new Figure[]{};
        } else {
            return this.start.getValues();
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method size">
    /**
     * Bestimmt die Länge der Liste
     * @return Länge der Liste
     */
    public int size() {
        if (this == null) {
            return 0;
        } else {
            return this.start.size();
        }
    }
//</editor-fold>
}
