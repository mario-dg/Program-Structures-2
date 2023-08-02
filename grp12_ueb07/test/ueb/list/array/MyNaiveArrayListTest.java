
package ueb.list.array;

import static org.junit.Assert.*;
import org.junit.Test;
import ueb.list.MyListTestBase;

/**
 * Tests für die Klasse MyNaiveArrayList
 * Um das Testen zu vereinfachen wurde ausschließlich mit Strings als Nutzlast geprüft
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class MyNaiveArrayListTest<E> extends MyListTestBase<E> {

    @Override
    public MyNaiveArrayList<String> createStringList(String... varArgs) {
        MyNaiveArrayList<String> newList = new MyNaiveArrayList<>(varArgs.length);

        System.arraycopy(varArgs, 0, newList.list, 0, varArgs.length);

        return newList;
    }
    
    @Override
    public MyNaiveArrayList<Integer> createIntList(Integer... varArgs) {
        MyNaiveArrayList<Integer> newList = new MyNaiveArrayList<>(varArgs.length);

        System.arraycopy(varArgs, 0, newList.list, 0, varArgs.length);

        return newList;
    }

    @Test
    public void testConstructor() {
        MyNaiveArrayList<String> test = new MyNaiveArrayList<>(234);
        assertEquals(234, test.getCapacity());
        boolean result = (0 == test.getGrowFactor());
        assertTrue(result);
    }
}
