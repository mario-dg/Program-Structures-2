package ueb05;

/**
 * Klasse zum Anlegen und verarbeiten einer Liste
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class Element {

    //<editor-fold defaultstate="collapsed" desc="Instance Variables">
    /**
     * Instance Variable figure - Figur in der Liste next - Nächstes Listenelement
     */
    final private Figure figure;
    private Element next;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 2 Parameter">
    /**
     * Konstruktor 2 Parameter
     *
     * @param figure aktuelle Figur
     * @param next nächstes Listenelement
     */
    Element(Figure figure, Element next) {
        this.figure = figure;
        this.next = next;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 1 Parameter">
    /**
     * Konstruktor 1 Parameter
     *
     * @param figure aktuelle Figur
     */
    Element(Figure figure) {
        this(figure, null);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 1 Parameter">
    /**
     * Konstruktor 1 Parameter
     *
     * @param next nächstes Listenelement
     */
    Element(Element next) {
        this(null, next);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor Default">
    /**
     * Default Konstruktor
     */
    Element() {
        this(null, null);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getFigur">
    /**
     * Getter Methode für die Figur
     *
     * @return Liefert die Figur
     */
    public Figure getFigur() {
        return figure;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getNext">
    /**
     * Getter Methode fürs nächste Listenelement
     *
     * @return Liefert das nächste Element in der Liste
     */
    public Element getNext() {
        return next;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method addStart">
    /**
     * Fügt ein neues Element an den Start der Liste
     *
     * @param figure Figur, die an den Start angefügt wird
     * @return Element, neuen Start der Liste
     */
    public Element addStart(Figure figure) {
        Element newFigure = new Element(figure);
        if (figure != null) {
            newFigure.next = this;
            // newFigure wird zurückgegeben, da es den neuen Anfang der Liste beschreibt
            return newFigure;
        } else {
            return this;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method insert">
    /**
     * Fügt ein Element in die Liste ein
     *
     * @param other Figur, die eingefügt wird
     * @return Element, Liste mit eingefügtem Element
     */
    public Element insert(Figure other) {
        //TODO DONE compare verwenden
        if (this.figure.compare(other) == -1) {
            Element newElement = new Element
        (other, this);
            return newElement;
        } else if (this.next == null) {
            Element newElement = new Element(other);
            this.next = newElement;
            return this;
        } else {
            this.next = this.next.insert(other);
            return this;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method size">
    /**
     * Berechnet die Länge der Liste
     *
     * @return int, Länge der Liste
     */
    public int size() {
        if (this.next != null) {
            return 1 + this.next.size();
        } else {
            return 1;
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getValues">
    /**
     * Packst alle Figuren in ein Array
     *
     * @return Figure[], Array mit allen Figuren einer Liste
     */
    public Figure[] getValues() {
        int length = this.size();
        Figure[] figureList = new Figure[length];
        Element newList = this;
        for (int i = 0; i < length; i++) {
            figureList[i] = newList.getFigur();
            newList = newList.next;
        }
        return figureList;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method toString">
    /**
     * Liefert die Liste nach Vorgaben formatiert zurück
     *
     * @return formatierte Liste
     */
    @Override
    public String toString() {
        return toStringHelper(1);
    }
    
    private String toStringHelper(int count) {
        String ausgabe;
        ausgabe = String.format("%2d. " + this.figure.toString(), count);

        if (this.next != null) {
            ausgabe = ausgabe + "\n" + this.getNext().toStringHelper(++count);
        }
        return ausgabe;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method equals">
    /**
     * Vergleicht, ob 2 Listen gleich sind
     *
     * @param obj Liste mit der Verglichen wird
     * @return boolean, ob Listen gleich sind oder nicht
     */
    @Override
    public boolean equals(Object obj) {
        // Obj existiert nicht, ist nicht von der Klasse Lement oder die ersten Element sind nicht gleich
        if ((obj == null) || !(obj instanceof Element) || !(this.figure.equals(((Element) obj).figure))) {
            return false;
        } else {
            // Ende der Liste ist erreicht und beide Elemente sind gleich
            if ((this.getNext() == null) && (((Element) obj).getNext() == null)) {
                return true;
            }
            // Rekursiver Aufruf, wenn Ende der Liste nicht nicht erreich
            if ((this.getNext() != null) && (((Element) obj).getNext() != null)) {
                return this.getNext().equals(((Element) obj).getNext());
            } else {
                return false;
            }
        }
    }
//</editor-fold>
}
