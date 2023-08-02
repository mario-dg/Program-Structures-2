package ueb.list.linked;

import ueb.list.functions.PredicateFunctionObject;

/**
 * Listenelemente mit Nutzlast cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
class MyFilledElement<E> extends MyElement<E> {

    /**
     * Instance Variables
     */
    public E value;
    public MyElement<E> next;

    /**
     * Konstruktor mit 1 Parameter
     *
     * @param value Nutzlast
     */
    public MyFilledElement(E value) {
        this.value = value;
    }

    /**
     * Konstruktor mit 2 Parametern
     *
     * @param value Nutzlast
     * @param next nächstes Element
     */
    public MyFilledElement(E value, MyElement<E> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Getter Methode für die Nutzlast
     *
     * @return Nutzlast
     */
    @Override
    public E getPayload() {
        return value;
    }

    /**
     * Getter Methode für das nächste Element
     *
     * @return
     */
    @Override
    public MyElement<E> getNext() {
        return next;
    }

    /**
     * Prüft, ob die Liste leer ist
     *
     * @return boolean, false, weil sobald ein Element dieser Klasser erstellt wird, ist die Liste
     * nicht mehr leer
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Bestimmt die Länge der Liste
     *
     * @return int, Länge der Liste
     */
    @Override
    public int length() {
        return 1 + this.getNext().length();
    }

    /**
     * Fügt ein Element ans Ende der Liste an
     *
     * @param value Nutzlast dieses Elements
     * @return Liste mit dem angehängten Element
     */
    @Override
    public MyElement<E> append(E value) {
        this.next = this.getNext().append(value);
        return this;
    }

    /**
     * Fügt ein Element an den Anfang der Liste
     *
     * @param value Nutzlast dieses Elements
     * @return Liste mit neuem Startwert
     */
    @Override
    public MyElement<E> insertAtFront(E value) {
        return new MyFilledElement<>(value, this);
    }

    /**
     * Gibt die Nutzlast an dem übergebenen index wieder
     *
     * @param i, index an dem die Nutzlast übergeben wird
     * @return Nutzlast
     */
    @Override
    public E getValueAt(int i) {

        if ((i < 0) || (i > this.length() - 1)) {
            return null;
        } else {
            if (i == 0) {
                return this.getPayload();
            } else {
                return this.getNext().getValueAt(--i);
            }
        }
    }

    /**
     * Überschreibt die Object.toString() Methode und gibt die Listenelemente formatiert wieder
     *
     * @return String, mit allen Listenelementen
     */
    @Override
    public String toString() {
        String s;
        s = this.getPayload().toString();
        s += this.next.isEmpty() ? this.next.toString() : ", " + this.next.toString();
        return s;
    }

    /**
     * Überschreibt die Object.equals() Methode und prüft, ob zwei Listen gleich sind
     *
     * @param obj, Liste die verglichen werden soll
     * @return boolean, ob die Listen gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyFilledElement) || (this.getPayload() != ((MyElement) obj).getPayload())) {
            return false;
        } else {
            return this.next.equals(((MyElement) obj).getNext());
        }
    }

    /**
     * Fügt ein Element an der Stelle des übergebenen Indexes ein
     *
     * @param i, index an dem eingefügt wird
     * @param value, Nutzlast des einzufügenen Elements
     * @return Liste, mit eingefügtem Element
     */
    @Override
    public MyElement<E> insertAt(int i, E value) {
        //Bei ungültigem Index, wird die Liste ohne Veränderung zurückgegeben
        if (i < 0) {
            return this;
        } else {
            //Am richtigen Index angekommen, wird ein neues Element an der Stelle eingefügt
            if (i == 0) {
                return new MyFilledElement(value, this);
            } else {
                //Rekursiver Aufruf bis an der richtigen Stelle angekommen
                this.next = this.next.insertAt(--i, value);
                return this;
            }
        }
    }

    /**
     * Entfernt ein Element an der übergebenen Stelle
     *
     * @param i, index an dem entfernt werden soll
     * @return Liste, ohne das entfernte Element
     */
    @Override
    public MyElement<E> removeAt(int i) {
        //An der richtigen Stelle angekommen, wird gelöscht
        if (i == 0) {
            return this.next;
        } else {
            //Rekursiver Aufruf, bis an der richtigen Stelle angekommen
            this.next = this.next.removeAt(--i);
            return this;
        }
    }

    /**
     * Entfernt alle Elemente aus der Liste die nicht dem Prädikat entsprechen
     *
     * @param f Prädikat
     * @return Liste, nur mit Elementen die dem Prädikat entsprechen
     */
    @Override
    public MyElement<E> filterThis(PredicateFunctionObject f) {
        this.next = this.next.filterThis(f);
        if (!f.call(this.value)) {
            return this.next;
        }
        return this;
    }

//    private MyElement<E> filterThisHelper(PredicateFunctionObject f, int i) {
//        if (!f.call(this.getValueAt(i))) {
//           return this.removeAt(i);
//        } else {
//            return this.filterThisHelper(f, ++i);
//        }
//        return this;
////        if (!f.call(this.getPayload())) {
////            this.next = this.next.filterThis(f);
////        } else {
////            return this.next.filterThis(f);
////        }
////        return this;
//    }
    /**
     * Gibt das erste Element zurück, dass dem Prädikat entspricht
     *
     * @param f Prädikat
     * @return Nutzlast, die dem Prädikat entspricht
     */
    @Override
    public E find(PredicateFunctionObject f) {

        MyElement<E> copy = this;

        //Filtert alle Elemente raus, die nicht dem Prädikat entsprechen
        copy = copy.filterThis(f);

        //liefert die erste Nutzlast in dieser Liste zurück
        return copy.getValueAt(0);
    }

    /**
     * Prüft, ob eine Liste nur aus Elementen besteht, die dem Prädikat entsprechen
     *
     * @param f
     * @return boolean
     */
    @Override
    public boolean forAll(PredicateFunctionObject f) {
        //TODO DONE testforAll_IsGreater_Not schlägt fehl (verwendet hier wieder call())
        if (f.call(this.getPayload())) {
            return this.next.forAll(f);
        } else {
            return false;
        }
    }

}
