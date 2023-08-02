package ueb.list.linked;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import ueb.Waypoint;
import ueb.list.functions.IsGreater;
import ueb.list.MyListTestBase;

/**
 *
 * @author Marcus Riemer (mri), klk
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyNaiveLinkedListTest1<E> extends MyListTestBase<E>{
    
    
    @Override
    public MyNaiveLinkedList<String> createStringList(String... varArgs) {
        MyNaiveLinkedList<String> newList = new MyNaiveLinkedList<>(varArgs);
        return newList;
    }
    
    @Override
    public MyNaiveLinkedList<Integer> createIntList(Integer... varArgs) {
        MyNaiveLinkedList<Integer> newList = new MyNaiveLinkedList<>(varArgs);
        return newList;
    }
    
    @Test
    public void testConstruct_EmptyIntegerList() {
        MyLinkedList<Integer> l = new MyLinkedList<>();
        
        assertTrue(l.isEmpty());
        assertEquals("[]", l.toString());
    }
    
    @Test
    public void testConstruct_SingleIntegerElement() {
        MyLinkedList<Integer> l = new MyLinkedList<>(0);
        
        assertFalse(l.isEmpty());
        assertEquals(Integer.valueOf(0), l.getValueAt(0));
        assertEquals("[0]", l.toString());
    }
    
    @Test
    public void testConstruct_SingleStringElement() {
        MyLinkedList<String> l = new MyLinkedList<>("0");
        
        assertFalse(l.isEmpty());
        assertEquals("0", l.getValueAt(0));
        assertEquals("[0]", l.toString());
    }
    
    @Test
    public void testConstruct_SingleBooleanElement() {
        MyLinkedList<Boolean> l = new MyLinkedList<>(false);
        
        assertFalse(l.isEmpty());
        assertEquals(false, l.getValueAt(0));
        assertEquals("[false]", l.toString());
    }
}
