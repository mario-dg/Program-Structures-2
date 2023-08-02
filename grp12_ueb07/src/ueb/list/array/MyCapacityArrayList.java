package ueb.list.array;

import ueb.list.MyList;

/**
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 * @param <E>
 */
public class MyCapacityArrayList<E> extends MyList<E> {

    /**
     * Konstanten für die Defaultkontruktion des Arrays
     */
    final static protected int DEFAULT_CAPACITY = 10;
    final static protected float DEFAULT_GROW_FACTOR = (float) 0.3;

    /**
     * Instanzvariablen für den Wachstumsfaktor und das Array an sich
     */
    protected float growth;
    protected Object[] list;

    /**
     * Default-Konstruktor
     */
    public MyCapacityArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        growth = DEFAULT_GROW_FACTOR;
    }

    /**
     * Konstruktor mit einem Übergabeparameter
     *
     * @param capacity Länge des Array
     */
    public MyCapacityArrayList(int capacity) {
        list = new Object[capacity];
        growth = DEFAULT_GROW_FACTOR;
    }

    /**
     * Konstruktor mit 2 Parametern
     *
     * @param capacity Länge des Arrays
     * @param growth Wachstumsfaktor
     */
    public MyCapacityArrayList(int capacity, float growth) {
        list = new Object[capacity];
        this.growth = growth;
    }

    /**
     * Getter-Methode für den Wachstumsfaktor
     *
     * @return growth den Wachstumsfaktor
     */
    public float getGrowFactor() {
        return growth;
    }

    /**
     * Getter-Methode für die Kapazität
     *
     * @return Capacity die Kapazität
     */
    public int getCapacity() {
        return this.list.length;
    }

    /**
     * Ermittelt die Anzahl der Element im Array
     *
     * @return Integer die Anzahl der Elemente
     */
    @Override
    public int length() {
        int size = 0;
        for (int i = 0; i < this.getCapacity(); i++) {
            if (this.list[i] != null) {
                size++;
            }
        }
        return size;
    }

    /**
     * Verkleinert das Array, so dass die Elemente genau hineinpassen
     *
     * @return Object das verkleinerte Array
     */
    public Object[] shrinkToFit() {
        int length = this.length();
        Object[] fittingList = new Object[length];

        System.arraycopy(this.list, 0, fittingList, 0, length);

        return fittingList;
    }

    /**
     * Berechnet die Kapazität, die ein Array bekommen wird nach der Vergrößerung mit dem Wachstums-
     * faktor
     *
     * @return Integer die Kapatzität
     */
    protected int getAllocationSize() {
        return (int) Math.ceil(this.getCapacity() + (this.getCapacity() * this.getGrowFactor()) + 1);
    }

    /**
     * Fügt einen Wert in ein Array ein
     *
     * @param i Stelle an der eingefügt werden soll
     * @param value der einzufügende Wert
     */
    @Override
    public void insertAt(int i, E value) {
        if (i >= 0 && i <= this.length()) {
            //TODO done nicht immer eine neue Liste erstellen

            //FIXME DONE Elemente werden doppelt eingefuegt, wenn keine neue Liste erstellt werden musste
            //ueberlegt euch, wann und wie oft ihr wirklich Arraycopy braucht
            if (this.length() == this.getCapacity()) {
                Object[] newList = new Object[this.getAllocationSize()];
                System.arraycopy(this.list, 0, newList, 0, i);
                newList[i] = value;
                System.arraycopy(this.list, i, newList, i + 1, this.length() - i);
                list = newList.clone();
            } else {
                //    System.arraycopy(this.list, 0, this.list, 0, i);
                System.arraycopy(this.list, i, this.list, i + 1, this.length() - i);
                this.list[i] = value;
            }

        }
    }

    /**
     * Gibt den Wert an einem Punkt im Array aus
     *
     * @param i Punkt an dem der Wert ermittelt werden soll
     * @return den Wert
     */
    @Override
    public E getValueAt(int i) {
        if (i < 0 || (i >= this.length())) {
            return null;
        } else {
            return (E) this.list[i];
        }
    }

    /**
     * Entfernt eine Stelle im Array
     *
     * @param i Stelle die entfernt werden soll
     */
    @Override
    public void removeAt(int i) {
        //TODO done nicht immer eine neue Liste erstellen
        if (i >= 0 && i < this.length()) {
            System.arraycopy(this.list, 0, this.list, 0, i);
            System.arraycopy(this.list, i + 1, this.list, i, this.length() - i - 1);
            this.list[this.length() - 1] = null;
            list = this.shrinkToFit();
        }
    }

    //TODO done toString
    @Override
    public String toString() {
        String output = "[" + this.getValueAt(0);
        for (int i = 1; i < this.length(); i++) {
            output += ", " + this.getValueAt(i);
        }
        output += "]";
        return output;
    }
}
