package ueb.list.linked;

import ueb.list.functions.PredicateFunctionObject;
import ueb.list.MyList;

/**
 * Zusammenfügen der Elemente mit und ohen Nutzlast 
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 * @param <E>
 */
public class MyLinkedList<E> extends MyList<E> {

    /**
     * Instance Variables
     */
    public MyElement<E> head;

    /**
     * Default-Konstruktor
     */
    public MyLinkedList() {
        head = new MyEmptyElement<>();
    }

    /**
     * Konstruktor mit einem Parameter
     * @param list 
     */
    public MyLinkedList(MyList<E> list) {
        head = new MyEmptyElement<>();
        for (int i = 0; i < list.length(); i++) {
            head = head.append(list.getValueAt(i));
        }
    }

    /**
     * Konsturtor zum erzeugen einer ganzen Liste
     * @param varArgs Nutzlasten der Liste
     */
    public MyLinkedList(E... varArgs) {
        head = new MyEmptyElement<>();
        for (E values : varArgs) {
            head = head.append(values);
        }
    }

    /**
     * Ermittelt die Länge der Liste
     * @return Integer Länge der Liste
     */
    @Override
    public int length() {
        return this.head.length();
    }

    /**
     * Fügt ein Element an die Liste an
     *
     * @param value, Nutzlast die das Element enthält
     */
    @Override
    public void append(E value) {
        this.head = this.head.append(value);
    }

    /**
     * Fügt ein Element an den Anfang der Liste an
     *
     * @param value, Nutzlast die das Element enthält
     */
    @Override
    public void insertAtFront(E value) {
        this.head = this.head.insertAtFront(value);
    }

    /**
     * Überschreibt die Object.equals() Methode und prüft, ob zwei Listen gleich sind
     *
     * @param obj Liste mit der geprüft werden soll
     * @return boolean, ob Listen gleich sind oder nicht
     */
    @Override
    public boolean equals(Object obj) {
        boolean temp = true;
        if (obj instanceof MyLinkedList && this.length() == ((MyLinkedList) obj).length()) {
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
     * Fügt Werte in die Liste ein
     * @param i Stelle an der eingefügt wird
     * @param value Nutzlast des einzufügenden Listenelements
     */
    @Override
    public void insertAt(int i, E value) {
        this.head = this.head.insertAt(i, value);
    }

    /**
     * Liefert die Nutzlast an einer Stelle in der Liste
     * @param i Stelle an der die Nutzlast  ermittelt werden soll
     * @return Wert der Nutzlast
     */
    @Override
    public E getValueAt(int i) {
        return this.head.getValueAt(i);
    }

    /**
     * Entfernt ein Element aus der Liste
     * @param i Stelle des Elements das entfernt wird
     */
    @Override
    public void removeAt(int i) {
        this.head = this.head.removeAt(i);
    }

    /**
     * Entfernt alle Elemente, die nicht zum Prädikat passen
     *
     * @param f Prädikat
     */
    @Override
    public void filterThis(PredicateFunctionObject<E> f) {
        this.head = this.head.filterThis(f);
    }

    /**
     * Liefert erste Nutzlast, die zum Prädikat passt
     *
     * @param f Prädikat
     * @return Nutzlast, die zum Prädikat passt
     */
    @Override
    public E find(PredicateFunctionObject<E> f) {
        return this.head.find(f);
    }

    /**
     * Prüft, ob eine Liste nur aus Elementen besteht, die zum Prädikat passt
     *
     * @param f Prädikat
     * @return boolean, ob Bedingung stimmt oder nicht
     */
    @Override
    public boolean forAll(PredicateFunctionObject<E> f) {
        return this.head.forAll(f);
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
     * 
     * @param f
     * @return 
     */
    public boolean exists(PredicateFunctionObject<E> f){
        return this.head.exists(f);
    }

}
