package ueb.list;

import ueb.list.functions.PredicateFunctionObject;

/**
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 * @param <E>
 */
public abstract class MyList<E> {

    public abstract int length();

    /**
     * Überprüft ob die Liste leer ist
     *
     * @return ob die Liste leer ist oder nicht
     */
    public boolean isEmpty() {
        return this.length() == 0;
    }

    public abstract void insertAt(int i, E value);

    /**
     * Fügt ein Element am Anfang ein
     *
     * @param value einzufügender Wert
     */
    public void insertAtFront(E value) {
        this.insertAt(0, value);
    }

    /**
     * Hängt ein Element ans Ende an
     *
     * @param value Wert der eingefügt werden soll
     */
    public void append(E value) {
        this.insertAt(this.length(), value);
    }

    public abstract E getValueAt(int i);

    public abstract void removeAt(int i);

    /**
     * Ersetzt ein Element
     *
     * @param i Stelle an der Operiert wird
     * @param value Wert der eingesetzt wird
     */
    public void replaceAt(int i, E value) {
        //FIXME DONE Indexpruefung noch einmal genauer checken
        if (!this.isEmpty() && (this.getValueAt(i) != null)) {
            this.removeAt(i);
            this.insertAt(i, value);
        }
    }

    /**
     * Überprüft ob 2 Listen gleich sind
     *
     * @param obj übergebene Listenstruktur
     * @return true wenn 2 Listen gleich sind
     */
    @Override
    public boolean equals(Object obj
    ) {
        boolean equal = true;
        if (obj instanceof MyList && this.length() == ((MyList) obj).length()) {
            for (int i = 0; i < this.length(); i++) {
                equal = equal && this.getValueAt(i) == ((MyList) obj).getValueAt(i);
            }
        } else {
            equal = false;
        }
        return equal;
    }
    //TODO done leere listen, oder nix gefunden fuehrt zu NullPointerException

    /**
     * Entfernt alle Elemente aus der Liste die nicht dem Prädikat entsprechen
     *
     * @param f Prädikat
     */
    public void filterThis(PredicateFunctionObject<E> f) {
        if (!this.isEmpty()) {
            for (int i = 0; i < this.length(); i++) {
                if (!f.call(this.getValueAt(i))) {
                    this.removeAt(i);
                }
            }
        }
    }

    /**
     * Gibt das erste Element zurück, dass dem Prädikat entspricht
     *
     * @param f Prädikat
     * @return
     */
    public E find(PredicateFunctionObject<E> f) {
        int i = 0;
        if (!this.isEmpty()) {
            //FIXME DONE Schleife kann out of bounds laufen
            //gerade wenn kein Element in der Liste dem Suchkriterium entspricht
            while (!f.call(this.getValueAt(i)) && i < this.length()) {
                i++;
            }
            if (i < this.length()) {
                return this.getValueAt(i);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean exists(PredicateFunctionObject<E> f) {
        boolean exist = false;

        if (!this.isEmpty()) {
            for (int i = 0; i < this.length(); i++) {
                exist = exist || f.call(this.getValueAt(i));
            }
        }
        return exist;
    }

    /**
     * Prüft, ob eine Liste nur aus Elementen besteht, die dem Prädikat entsprechen
     *
     * @param f Prädikat
     * @return true wenn die Liste nur aus einem Pädikattypen besteht
     */
    public boolean forAll(PredicateFunctionObject<E> f) {
        boolean exist = true;

        if (!this.isEmpty()) {
            for (int i = 0; i < this.length(); i++) {
                exist = exist && f.call(this.getValueAt(i));
            }
        }
        return exist;
    }
}
