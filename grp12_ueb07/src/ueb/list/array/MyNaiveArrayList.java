package ueb.list.array;

/**
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 * @param <E>
 */
public class MyNaiveArrayList<E> extends MyCapacityArrayList<E> {
    
    //public Object[] naiveList;
    
    /**
     * Konstruktor für das Array
     */
    public MyNaiveArrayList(){
        super();
    }
    
    public MyNaiveArrayList(int capacity){
        super(capacity, 0);
    }
}
