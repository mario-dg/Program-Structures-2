package ueb.list.linked;

import ueb.list.MyList;

/**
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 * @param <E>
 */
public class MyNaiveLinkedList<E> extends MyList<E> {

    /**
     * Instance Variables
     */
    public MyElement<E> head;

    /**
     * Konstruktór dé Default
     */
    public MyNaiveLinkedList() {
        head = new MyEmptyElement<>();
    }

    /**
     * Konstruktor mit einem Parameter
     *
     * @param list
     */
    public MyNaiveLinkedList(MyList<E> list) {
        head = new MyEmptyElement<>();
        for (int i = 0; i < list.length(); i++) {
            head = head.append(list.getValueAt(i));
        }
    }

    /**
     * Konstruktor zum erstellen einer gefüllten Liste
     *
     * @param varArgs
     */
    public MyNaiveLinkedList(E... varArgs) {
        head = new MyEmptyElement<>();
        for (E values : varArgs) {
            head = head.append(values);
        }
    }

    /**
     * Ermittelt die Länge der Liste
     * @return
     */
    @Override
    public int length() {
        return this.head.length();
    }

    /**
     * Fügt an einer Stelle in der Liste ein
     * @param i Stelle an der eingfügt wird
     * @param value Nutzlast
     */
    @Override
    public void insertAt(int i, E value) {
        this.head = this.head.insertAt(i, value);
    }

    /**
     * Ermittelt den Wert an einer Stelle in der Liste
     * @param i Stelle an der der Wert ermittelt wird
     * @return Wert an der Stelle i
     */
    @Override
    public E getValueAt(int i) {
        return this.head.getValueAt(i);
    }

    /**
     * Entfernt ein Element aus der Liste
     * @param i Stelle an der gelöscht werden soll
     */
    @Override
    public void removeAt(int i) {
        this.head = this.head.removeAt(i);
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
}
