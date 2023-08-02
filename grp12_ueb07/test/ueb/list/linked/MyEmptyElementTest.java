package ueb.list.linked;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author klk
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyEmptyElementTest {
    
    /**
     * Test of getPayload method, of class MyElement.
     */
    @Test (expected = AssertionError.class)
    public void testGetPayload() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        myElem.getPayload();
    }

    /**
     * Test of getNext method, of class MyElement.
     */
    @Test (expected = AssertionError.class)
    public void testGetNext() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        myElem.getNext();
    }

    /**
     * Test of getValueAt method, of class MyElement.
     */
    @Test 
    public void testGetValueAt() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        assertNull(myElem.getValueAt(0));
    }

    /**
     * Test of isEmpty method, of class MyElement.
     */
    @Test
    public void testIsEmpty() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        assertTrue(myElem.isEmpty());
    }

    /**
     * Test of length method, of class MyElement.
     */
    @Test
    public void testLength() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        assertEquals(0, myElem.length());
    }

    /**
     * Test of insertAtFront method, of class MyElement.
     */
    @Test
    public void testInsertAtFront() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        MyElement<Integer> result = myElem.insertAtFront(99);
        assertEquals(Integer.valueOf(99), result.getPayload());
        assertNotNull(result.getNext());
        assertEquals(1, result.length());
        assertEquals(MyFilledElement.class, result.getClass());
        assertEquals(MyEmptyElement.class, result.getNext().getClass());
    }

    /**
     * Test of insertAt method, of class MyElement.
     */
    @Test
    public void testInsertAt_First() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        MyElement<Integer> result = myElem.insertAt(0, 99);
        assertEquals(Integer.valueOf(99), result.getPayload());
        assertNotNull(result.getNext());
        assertEquals(1, result.length());
        assertEquals(MyFilledElement.class, result.getClass());
        assertEquals(MyEmptyElement.class, result.getNext().getClass());        
    }
    
    @Test
    public void testInsertAt_InvalidIndex() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        MyElement<Integer> result = myElem.insertAt(1, 99);
        assertSame(myElem, result);
    }

    /**
     * Test of removeAt method, of class MyElement.
     */
    @Test
    public void testRemoveAtIndex() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        MyElement<Integer> result = myElem.removeAt(0);
        assertSame(myElem, result);
    }

    /**
     * Test of append method, of class MyElement.
     */
    @Test
    public void testAppend() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        MyElement<Integer> result = myElem.append(99);
        assertEquals(Integer.valueOf(99), result.getPayload());
        assertNotNull(result.getNext());
        assertEquals(1, result.length());
        assertEquals(MyFilledElement.class, result.getClass());
        assertEquals(MyEmptyElement.class, result.getNext().getClass());
    }

    /**
     * Test of equals method, of class MyElement.
     */
    @Test
    public void testEquals() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        assertTrue(myElem.equals(new MyEmptyElement<>()));
        assertTrue(new MyEmptyElement<>().equals(myElem));
        assertFalse(myElem.equals(null));
        assertFalse(myElem.equals(0));
    }

    /**
     * Test of toString method, of class MyElement.
     */
    @Test
    public void testToString() {
        MyElement<Integer> myElem = new MyEmptyElement<>();
        assertEquals("", myElem.toString());
    }

    
}
