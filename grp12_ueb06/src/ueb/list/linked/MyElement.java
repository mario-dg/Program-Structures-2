package ueb.list.linked;

import ueb.list.functions.PredicateFunctionObject;

/**
 * Leere Liste, bildet Ende der Liste. Macht Prüfung auf null nichtig
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
class MyElement<E> {

    /**
     * Getter-Methode für die Nutzlast
     *
     * @return Nutzlast
     */
    public E getPayload() {
        assert false;
        return null;
    }

    /**
     * Getter-Methode für das nächste Element
     *
     * @return nächste Element
     */
    public MyElement getNext() {
        assert false;
        return null;
    }

    /**
     * Prüft, ob die Liste leer ist
     *
     * @return true
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Berechnet die Länge der Liste
     *
     * @return 0, weil Liste immer leer
     */
    public int length() {
        return 0;
    }

    /**
     * Fügt ein Element an die Liste an
     *
     * @param value, Nutzlast die das Element enthält
     * @return neues Listenelement mit der übergebenen Nutzlast
     */
    public MyElement<E> append(E value) {
        return new MyFilledElement<>(value, this);
    }

    /**
     * Fügt ein Element an den Anfang der Liste an
     *
     * @param value, Nutzlast die das Element enthält
     * @return Liste mit neuem Startwert
     */
    public MyElement<E> insertAtFront(E value) {
        return new MyFilledElement(value, this);
    }

    /**
     * Liefert die Nutzlast an der übergebenen Stelle
     *
     * @param i, index an dem Nutzlast zurückgegeben soll
     * @return null, weil Liste leer
     */
    public E getValueAt(int i) {
        return null;
    }

    /**
     * Überschreibt die Object.toString() Methode
     *
     * @return leeren String, weil Liste immer leer
     */
    @Override
    public String toString() {
        return "";
    }

    /**
     * Überschreibt die Object.equals() Methode und prüft, ob zwei Listen gleich sind
     *
     * @param obj Liste mit der geprüft werden soll
     * @return boolean, ob Listen gleich sind oder nicht
     */
    @Override
    public boolean equals(Object obj) {
        // übergebene Liste muss vom Typ MyElement sein und eine Länge von 0 haben, weil Liste immer leer
        return obj instanceof MyElement && ((MyElement) obj).length() == 0;
    }

    /**
     * Fügt ein Element an der Stelle des übergebenen indexes ein
     *
     * @param i index an dem eingefügt werden soll
     * @param value Nutzlast des Elements
     * @return Liste mit eingefügtem Element
     */
    public MyElement<E> insertAt(int i, E value) {
        if (i == 0) {
            return new MyFilledElement(value, this);
        } else {
            return this;
        }
    }

    /**
     * Löscht das Element an dem übergebenen Index
     *
     * @param i, index an dem gelöscht werden soll
     * @return Liste mit gelöschten Element, bzw nicht veränderte Liste bei leerer Liste
     */
    public MyElement<E> removeAt(int i) {
        return this;
    }

    /**
     * Entfernt alle Elemente, die nicht zum Prädikat passen
     *
     * @param f Prädikat
     * @return Liste nur mit Elementen die zum Prädikat passen
     */
    public MyElement<E> filterThis(PredicateFunctionObject f) {
        return this;
    }

    /**
     * Liefert erste Nutzlast, die zum Prädikat passt
     *
     * @param f Prädikat
     * @return Nutzlast, die zum Prädikat passt
     */
    public E find(PredicateFunctionObject f) {
        return null;
    }

    /**
     * Prüft, ob eine Liste nur aus Elementen besteht, die zum Prädikat passt
     *
     * @param f Prädikat
     * @return boolean, ob Bedingung stimmt oder nicht
     */
    public boolean forAll(PredicateFunctionObject f) {
        return true;
    }
}
