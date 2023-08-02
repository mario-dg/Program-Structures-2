package ueb.list.linked;

import ueb.list.functions.PredicateFunctionObject;

/**
 * Zusammenfügen der Elemente mit und ohen Nutzlast cgt103579(Mario da Graca), tinf103521(Tjorben
 * Klein)
 * @param <E>
 */
public class MyLinkedList<E> {
    //XXX raw type vermeiden!

    /**
     * Instance variable
     */
    MyElement<E> head;

    /**
     * Default-Konstruktor
     */
    public MyLinkedList() {
        head = new MyElement<>();
    }

    /**
     * Konstruktor mit 1 Parameterm Kopiert eine Liste mit vorgegebener länge
     *
     * @param list Liste die kopiert wird
     */
    public MyLinkedList(MyElement<E> list) {
        MyLinkedList<E> copyList = new MyLinkedList<>();
        for (int i = 0; i < list.length(); i++) {
            copyList.head = copyList.head.append(list.getValueAt(i));
        }
    }

    /**
     * Konstruktor mit beliebiger Länge, Erstellt eine Liste aus beliebig vielen Nutzlasten
     *
     * @param values
     */
    public MyLinkedList(E... values) {
        head = new MyElement<>();
        for (E value : values) {
            head = head.append(value);
        }
    }

    /**
     * Prüft, ob die Liste leer ist
     *
     * @return boolean, ob Liste leer oder nicht
     */
    public boolean isEmpty() {
        return this.head.isEmpty();
    }

    /**
     * Bestimmt die Länge der Liste
     *
     * @return int, Länge der Liste
     */
    public int length() {
        return this.head.length();
    }

    /**
     * Fügt ein Element ans Ender der Liste an
     *
     * @param value Liste, mit angefügtem Element
     */
    public void append(E value) {
        this.head = this.head.append(value);
    }

    /**
     * Fügt ein Element an den Anfang der Liste an
     *
     * @param value Nutzlast dieses Element
     */
    public void insertAtFront(E value) {
        this.head = this.head.insertAtFront(value);
    }

    /**
     * Liefert die Nutzlast eines Elements am übergbenen index zurück
     *
     * @param i, index
     * @return Nutzlast
     */
    public E getValueAt(int i) {
        return this.head.getValueAt(i);
    }

    /**
     * Überschreibt die Object.equals() Methode
     *
     * @return String, mit allen Listen Elementen nach Vorgaben formatiert
     */
    @Override
    public String toString() {
        return "[" + this.head.toString() + "]";
    }

    /**
     * Fügt ein Element am übergebenen Index ein
     *
     * @param i, index
     * @param value, Nutzlast
     */
    public void insertAt(int i, E value) {
        this.head = this.head.insertAt(i, value);
    }

    /**
     * Löscht ein Element am übergebenen Index
     *
     * @param i, index
     */
    public void removeAt(int i) {
        this.head = this.head.removeAt(i);
    }

    /**
     * Überschreibt die Object.equals Methode und prüft, ob die Listen gleich sind
     *
     * @param obj, zu vergleichende Liste
     * @return boolean, ob Listen gleich oder nicht
     */
    @Override
    public boolean equals(Object obj) {
        boolean temp = true;
        if (this.length() == ((MyLinkedList) obj).length()) {
            for (int i = 0; i < this.length(); i++) {
                if (this.getValueAt(i) != ((MyLinkedList) obj).getValueAt(i)) {
                    temp = false;
                }
            }
        } else {
            temp = false;
        }
        return temp;
    }

    /**
     * Löscht alle Elemente raus, die nicht dem Prädikat entsprechen
     *
     * @param f Prädikat
     */
    public void filterThis(PredicateFunctionObject f) {
        this.head = this.head.filterThis(f);
    }

    /**
     * Erstellt eine Liste nur mit Elementen die dem Prädikat entsprechen
     *
     * @param f Prädikat
     * @return Liste nur mit Elementen, die dem Prädikat entsprechen
     */
    public MyLinkedList<E> filter(PredicateFunctionObject f) {
        MyLinkedList<E> copy = new MyLinkedList<>();

        for (int i = 0; i < this.length(); i++) {
            if (f.call(this.getValueAt(i))) {
                copy.append(this.getValueAt(i));
            }
        }
        return copy;
    }

    /**
     * Liefert das erste Element zurück, dass dem Prädikat entspricht
     *
     * @param f Prädikat
     * @return Nutzlast
     */
    public E find(PredicateFunctionObject f) {
        MyLinkedList<E> copy = this;
        copy.filterThis(f);
        return copy.head.getValueAt(0);
    }

    /**
     * Prüft, ob in der Liste ein Element existiert, dass dem Prädikat entspricht
     *
     * @param f Prädikat
     * @return boolean, ob so ein Element existiert
     */
    public boolean exists(PredicateFunctionObject f) {
        return this.filter(f).length() != 0;
    }

    /**
     * Prüft, ob die Liste nur aus Elementen besteht, die dem Prädikat entsprechen
     * @param f Prädikat
     * @return boolean, ob diese Bedingung erfüllt ist
     */
    public boolean forAll(PredicateFunctionObject f) {
        return this.head.forAll(f);
    }
}
