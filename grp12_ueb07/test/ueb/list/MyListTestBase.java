package ueb.list;

import static org.junit.Assert.*;
import org.junit.Test;
import ueb.Waypoint;
import ueb.list.functions.IsGreater;
import ueb.list.linked.MyLinkedList;

/**
 *
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 * @param <E>
 */
public abstract class MyListTestBase<E> {

    public abstract MyList<String> createStringList(String... varArgs);

    public abstract MyList<Integer> createIntList(Integer... varArgs);
    //MyCapacityArray, MyNaiveArray, MyLinkedList und MyNaiveLinkedList ereben und implementieren 
    //die createList methode

    //TODO DONE alle Tests hierher und in MyList<gewaehlter Typ> verallgemeinern
    /**
     * Test der length-Methode
     */
    @Test
    public void testLength() {
        MyList<String> unused = createStringList((String) "a", (String) "b", (String) "c", (String) "d");
        int result = unused.length();

        assertEquals(4, result);
    }

    /**
     * Test von insertAt: Getestet wurde fürs am Start, in der Mitte und am Ende einfügen
     */
    @Test
    public void testInsertAt() {
        MyList<String> test = createStringList((String) "a", (String) "b", (String) "d");

        test.insertAt(0, (String) "ß");
        assertEquals((String) "ß", test.getValueAt(0));

        test.insertAt(3, (String) "c");
        assertEquals((String) "c", test.getValueAt(3));

        test.insertAt(5, (String) "e");
        assertEquals((String) "e", test.getValueAt(5));
    }

    /**
     * Tests für getValueAt
     */
    @Test
    public void testGetValueAt() {
        MyList<String> test = createStringList((String) "a", (String) "b", (String) "c",
                (String) "d", (String) "e");
        assertEquals((String) "c", test.getValueAt(2));
        assertEquals((String) "a", test.getValueAt(0));
        assertEquals((String) "e", test.getValueAt(4));
    }

    /**
     * Tests der Methode removeAt: Getestet wurde fürs Löschen am Start, in der Mitte und am Ende
     */
    public void testRemoveAt() {
        MyList<String> test = createStringList((String) "a", (String) "b", (String) "c",
                (String) "d", (String) "e");

        test.removeAt(4);
        assertEquals(null, test.getValueAt(4));

        test.removeAt(2);
        assertEquals("d", test.getValueAt(2));

        test.removeAt(0);
        assertEquals("b", test.getValueAt(0));
    }

    /**
     * Test of using Raw-types
     */
    @Test
    public void testDidntUseRawTypes() {
        MyList<String> strL = createStringList((String) "B", (String) "C");
        /* if you used raw-types in your listdefinition, 
           l.getValueAt() will return an object and 
           toLowerCase() won´t work with it */
        assertEquals("B", strL.getValueAt(0));

        MyLinkedList<Waypoint> wpL = new MyLinkedList(new Waypoint(1, 1), new Waypoint(1, 2));
        /* if you used raw-types in your listdefinition, 
           l.getValueAt() will return an object and 
           calcDistance() won´t work with it */
        assertEquals(1, wpL.getValueAt(0).calcDistance(wpL.getValueAt(1)), 0.00001);
    }

    /**
     * Test of getValueAt method, of class MyLinkedList.
     */
    @Test
    public void testGetValueAt_Fst() {
        MyList<Integer> e = createIntList(1, 2, 3);
        assertEquals(Integer.valueOf(1), e.getValueAt(0));
    }

    @Test
    public void testGetValueAt_Snd() {
        MyList<Integer> e = createIntList(1, 2, 3);
        assertEquals(Integer.valueOf(1), e.getValueAt(0));
        assertEquals(Integer.valueOf(2), e.getValueAt(1));
    }

    @Test
    public void testGetValueAt_IndexOneTooBig() {
        MyList<Integer> e = createIntList(1, 2, 3);
        assertNull(e.getValueAt(3));
    }

    @Test
    public void testGetValueAt_IndexTooBig() {
        MyList<Integer> e = createIntList(1, 2, 3);
        assertNull(e.getValueAt(4));
    }

    /**
     * Test of isEmpty method, of class MyLinkedList.
     */
    @Test
    public void testIsEmpty() {
        MyList<Integer> e = createIntList();
        assertTrue(e.isEmpty());

        MyList<Integer> f = createIntList(99);
        assertFalse(f.isEmpty());
    }

    /**
     * Test of append method, of class MyLinkedList.
     */
    @Test
    public void testAppend() {
        MyList<Integer> l = createIntList(11, 22);
        l.append(33);
        MyList<Integer> expected = createIntList(11, 22, 33);
        assertEquals(3, l.length());
        assertEquals(expected, l);
    }

    @Test
    public void testAppend_DigitValues() {
        MyList<Integer> l = createIntList();

        assertTrue(l.isEmpty());

        l.append(0);
        assertFalse(l.isEmpty());
        assertEquals(Integer.valueOf(0), l.getValueAt(0));

        l.append(1);
        assertFalse(l.isEmpty());
        assertEquals(Integer.valueOf(0), l.getValueAt(0));
        assertEquals(Integer.valueOf(1), l.getValueAt(1));

        l.append(2);
        assertFalse(l.isEmpty());
        assertEquals(Integer.valueOf(0), l.getValueAt(0));
        assertEquals(Integer.valueOf(1), l.getValueAt(1));
        assertEquals(Integer.valueOf(2), l.getValueAt(2));
    }

    /**
     * Test of insertAtFront method, of class MyLinkedList.
     */
    @Test
    public void testInsertAtFront() {
        MyList<Integer> l = createIntList(22, 33);
        l.insertAtFront(11);
        assertEquals(3, l.length());
        assertEquals(Integer.valueOf(11), l.getValueAt(0));
        assertEquals(Integer.valueOf(22), l.getValueAt(1));
        assertEquals(Integer.valueOf(33), l.getValueAt(2));
    }

    /**
     * Test of insertAt method, of class MyLinkedList.
     */
    @Test
    public void testInsertAt_First() {
        MyList<Integer> l = createIntList(22, 33);
        l.insertAt(0, 11);
        assertEquals(3, l.length());
        assertEquals(Integer.valueOf(11), l.getValueAt(0));
        assertEquals(Integer.valueOf(22), l.getValueAt(1));
        assertEquals(Integer.valueOf(33), l.getValueAt(2));
    }

    @Test
    public void testInsertAt_Snd() {
        MyList<Integer> l = createIntList(11, 33);
        l.insertAt(1, 22);
        assertEquals(3, l.length());
        assertEquals(Integer.valueOf(11), l.getValueAt(0));
        assertEquals(Integer.valueOf(22), l.getValueAt(1));
        assertEquals(Integer.valueOf(33), l.getValueAt(2));
    }

    @Test
    public void testInsertAt_Last() {
        MyList<Integer> l = createIntList(11, 22);
        l.insertAt(2, 33);
        assertEquals(3, l.length());
        assertEquals(Integer.valueOf(11), l.getValueAt(0));
        assertEquals(Integer.valueOf(22), l.getValueAt(1));
        assertEquals(Integer.valueOf(33), l.getValueAt(2));
    }

    @Test
    public void testInsertAt_InvalidIndex() {
        MyList<Integer> l = createIntList(11, 22);
        l.insertAt(3, 33);
        assertEquals(2, l.length());
    }

    /**
     * Test of removeAt method, of class MyLinkedList.
     */
    @Test
    public void testRemoveAt_First() {
        MyList<Integer> l = createIntList(11, 33);
        l.removeAt(0);
        assertEquals(1, l.length());
        assertEquals(Integer.valueOf(33), l.getValueAt(0));
    }

    @Test
    public void testRemoveAt_Last() {
        MyList<Integer> l = createIntList(11, 22);
        l.removeAt(1);
        assertEquals(1, l.length());
        assertEquals(Integer.valueOf(11), l.getValueAt(0));
    }

    @Test
    public void testRemoveAt_InvalidIndex() {
        MyList<Integer> l = createIntList(11, 22);
        l.removeAt(2);
        assertEquals(2, l.length());
        assertEquals(Integer.valueOf(11), l.getValueAt(0));
        assertEquals(Integer.valueOf(22), l.getValueAt(1));
    }

    /**
     * Test of equals method, of class MyLinkedList.
     */
    @Test
    public void testEquals_ListOfTwo() {
        MyList<Integer> a = createIntList(11, 22);
        MyList<Integer> b = createIntList(11, 22);
        assertEquals(a, b);
    }

    @Test
    public void testEquals_DifferentElements() {
        MyList<Integer> e = createIntList(11, 22);
        MyList<Integer> o = createIntList(22, 11);
        assertNotEquals(e, o);
        assertNotEquals(o, e);
    }

    @Test
    public void testEquals_OneShorter() {
        MyList<Integer> e = createIntList(11, 22);
        MyList<Integer> o = createIntList(11);
        assertNotEquals(e, o);
        assertNotEquals(o, e);
    }

    /**
     * Test of toString method, of class MyLinkedList.
     */
    @Test
    public void testToString() {
        MyList<Integer> l = createIntList(11, 22);
        assertEquals("[11, 22]", l.toString());
    }

    @Test
    public void testToString_OneElement() {
        MyList<Integer> l = createIntList(11);
        assertEquals("[11]", l.toString());
    }

    /**
     * Test of filterThis method, of class MyLinkedList.
     */
    @Test
    public void testFilterThis_IsGreater() {
        MyList<Integer> l = createIntList(0, 1, 0, 2, 0, 3);

        l.filterThis(new IsGreater(0));

        assertEquals(3, l.length());
        assertEquals(Integer.valueOf(1), l.getValueAt(0));
        assertEquals(Integer.valueOf(2), l.getValueAt(1));
        assertEquals(Integer.valueOf(3), l.getValueAt(2));
    }

    /**
     * Test of find method, of class MyLinkedList.
     */
    @Test
    public void testFind_IsGreater() {
        MyList<Integer> l = createIntList(0, 1, 0, 2, 0, 3);

        assertEquals(Integer.valueOf(1), l.find(new IsGreater(0)));
        assertEquals(Integer.valueOf(2), l.find(new IsGreater(1)));
        assertEquals(Integer.valueOf(3), l.find(new IsGreater(2)));
    }

    /**
     * Test of exists method, of class MyLinkedList.
     */
    @Test
    public void testExists_IsGreater() {
        MyList<Integer> l = createIntList(0, 1, 0, 2, 0, 3);

        assertTrue(l.exists(new IsGreater(0)));
        assertTrue(l.exists(new IsGreater(1)));
        assertTrue(l.exists(new IsGreater(2)));
    }

    @Test
    public void testExists_IsGreater_Not() {
        MyList<Integer> l = createIntList(0, 1, 0, 2, 0, 3);

        assertFalse(l.exists(new IsGreater(3)));
    }

    /**
     * Test of forAll method, of class MyLinkedList.
     */
    @Test
    public void testforAll_IsGreater() {
        MyList<Integer> l = createIntList(0, 1, 0, 2, 0, 3);
        assertTrue(l.forAll(new IsGreater(-1)));

        l = createIntList(5);
        assertTrue(l.forAll(new IsGreater(4)));

        l = createIntList();
        assertTrue(l.forAll(new IsGreater(4)));
    }

    @Test
    public void testforAll_IsGreater_Not() {
        MyList<Integer> l = createIntList(0, 1, 0, 2, 0, 3);
        assertFalse(l.forAll(new IsGreater(1)));

        l = createIntList(10, 11, 9, 22);
        assertFalse(l.forAll(new IsGreater(9)));

        l = createIntList(11, 11, 11, 9);
        assertFalse(l.forAll(new IsGreater(10)));
    }

    @Test
    public void testReplaceAt() {
        MyList<String> test = createStringList((String) "a", (String) "b", (String) "d", (String) "j");

        test.replaceAt(0, (String) "ß");
        test.replaceAt(1, (String) "c");
        test.replaceAt(3, (String) "e");
        test.replaceAt(4, (String) "z");

        assertEquals((String) "ß", test.getValueAt(0));
        assertEquals((String) "c", test.getValueAt(1));
        assertEquals((String) "d", test.getValueAt(2));
        assertEquals((String) "e", test.getValueAt(3));
        assertNull(test.getValueAt(4));

        MyList<String> empty = createStringList();
        empty.replaceAt(0, (String) "TEST");
        assertNull(empty.getValueAt(0));
        

    }

}
