package ueb.list.array;

import static org.junit.Assert.*;
import org.junit.Test;
import ueb.list.MyListTestBase;

/**
 * Tests für die Klasse MyCapacityArrayList
 * Um das Testen zu vereinfachen wurde ausschließlich mit Strings als Nutzlast geprüft
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class MyCapacityArrayListTest<E> extends MyListTestBase<E> {

    /**
     * Hilfsmethode zum befüllen eines neuen Arrays
     *
     * @param varArgs Eingabeparameter
     * @return newList neues Array beefüllt mit Eingabeparametern
     */
    @Override
    public MyCapacityArrayList<String> createStringList(String... varArgs) {
        MyCapacityArrayList<String> newList = new MyCapacityArrayList<>(varArgs.length, (float) 1);

        System.arraycopy(varArgs, 0, newList.list, 0, varArgs.length);
        return newList;
    }
    
    @Override
    public MyCapacityArrayList<Integer> createIntList(Integer... varArgs) {
        MyCapacityArrayList<Integer> newList = new MyCapacityArrayList<>(varArgs.length, (float) 1);

        System.arraycopy(varArgs, 0, newList.list, 0, varArgs.length);
        return newList;
    }

    /**
     * Tests der Konstruktoren
     */
    @Test
    public void testConstructor_TwoParameter() {
        MyCapacityArrayList<String> test = new MyCapacityArrayList<>(0, (float) 0.7);
        assertEquals(0, test.getCapacity());
        boolean result = ((float) 0.7 == test.getGrowFactor());
        assertTrue(result);
    }

    @Test
    public void testConstruct_OneParameter() {
        MyCapacityArrayList<String> test = new MyCapacityArrayList<>(0);
        assertEquals(0, test.getCapacity());
        boolean result = (MyCapacityArrayList.DEFAULT_GROW_FACTOR == test.getGrowFactor());
        assertTrue(result);
    }

    @Test
    public void testConstruct() {
        MyCapacityArrayList<String> test = new MyCapacityArrayList<>();
        assertEquals(MyCapacityArrayList.DEFAULT_CAPACITY, test.getCapacity());
        boolean result = (MyCapacityArrayList.DEFAULT_GROW_FACTOR == test.getGrowFactor());
        assertTrue(result);
    }

    /**
     * Test der Methode shrinkToFit
     */
    @Test
    public void testShrinkToFit() {
        MyCapacityArrayList<String> unused = createStringList((String) "a", (String) "b", (String) "c", (String) "d");

        Object[] shrinked = new Object[unused.length()];
        shrinked = unused.shrinkToFit();
        int result = shrinked.length;
        assertEquals(result, unused.length());
    }
}
