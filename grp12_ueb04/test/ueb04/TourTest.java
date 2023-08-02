package ueb04;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author GeritKaleck
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TourTest {
    
    //<editor-fold defaultstate="collapsed" desc="Test of constructors">
    /**
     * Test of constructors, of class Tour.
     */
    @Test
    public void test0Tour() {
        Tour tour = new Tour();
        assertTrue(tour.isEmpty());
    }
    
    @Test
    public void test0Tour_intArr_Empty() {
        Tour tour = new Tour(new int[][]{});
        assertTrue(tour.isEmpty());
    }
    
    @Test
    public void test0Tour_intArr_OneElement() {
        Tour tour = new Tour(new int[][]{{0, 0}});
        assertTrue(new Waypoint(0,0).isEqual(tour.getWaypointAt(0)));
        assertNull(tour.getWaypointAt(1));
    }
    
    @Test
    public void test0Tour_intArr_ThreeElements() {
        Tour tour = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertTrue(new Waypoint(0,0).isEqual(tour.getWaypointAt(0)));
        assertTrue(new Waypoint(1,1).isEqual(tour.getWaypointAt(1)));
        assertTrue(new Waypoint(2,2).isEqual(tour.getWaypointAt(2)));
        assertNull(tour.getWaypointAt(3));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of isEqual">
    /**
     * Test of isEqual method, of class Tour.
     */
    @Test
    public void test0IsEqual_ThreeElements() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertTrue(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstLonger() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstShorter() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstDiffers() {
        Tour tourA = new Tour(new int[][]{{9, 9}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_LastDiffers() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {9, 9}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_FirstEmpty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_SndEmpty() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = new Tour();
        assertFalse(tourA.isEqual(tourB));
    }
    @Test
    public void test0IsEqual_BothEmpty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour();
        assertTrue(tourA.isEqual(tourB));
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Test of copy">
    /**
     * Test of copy method, of class Tour.
     */
    @Test
    public void testCopy_ThreeElements() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = tourA.copy();
        assertTrue(tourA.isEqual(tourB));
    }
    
    @Test
    public void testCopy_ThreeElementsNotChangingList() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour control = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour unused = tourA.copy();
        assertTrue(tourA.isEqual(control));
    }
    
    @Test
    public void testCopy_ThreeElementsNotSameReference() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour tourB = tourA.copy();
        assertNotEquals(tourA, tourB);
    }
//</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Test of createConcenatedTour">
    /**
     * Test of createConcatenatedTour method, of class Tour.
     */
    @Test
    public void testCreateConcatenatedTour_TwoPlusOne() {
        Tour tourA    = new Tour(new int[][]{{0, 0}, {1, 1}});
        Tour tourB    = new Tour(new int[][]{{2, 2}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}});
        Tour result   = tourA.createConcatenatedTour(tourB);
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateConcatenatedTour_EmptyPlusOne() {
        Tour tourA    = new Tour();
        Tour tourB    = new Tour(new int[][]{{2, 2}});
        Tour expected = new Tour(new int[][]{{2, 2}});
        Tour result   = tourA.createConcatenatedTour(tourB);
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateConcatenatedTour_OnePlusEmpty() {
        Tour tourA    = new Tour();
        Tour tourB    = new Tour(new int[][]{{2, 2}});
        Tour expected = new Tour(new int[][]{{2, 2}});
        Tour result   = tourB.createConcatenatedTour(tourA);
        assertTrue(result.toString(), expected.isEqual(result));
    }
//</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Test of createTourWithOrder">
    /**
     * Test of createTourWithOrder method, of class Tour.
     */
    @Test
    public void testCreateTourWithOrder_SameOrder() {
        Tour tour     = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour result = tour.createTourWithOrder(new int[]{0, 1, 2, 3});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateTourWithOrder_ReversedOrder() {
        Tour tour     = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{3, 3}, {2, 2}, {1, 1}, {0, 0}});
        Tour result = tour.createTourWithOrder(new int[]{3, 2, 1, 0});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateTourWithOrder_EverySecondElement() {
        Tour tour     = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {2, 2}});
        Tour result = tour.createTourWithOrder(new int[]{0, 2});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateTourWithOrder_RepetitiveIndices() {
        Tour tour     = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {1, 1}, {1, 1}, {2, 2}, {3, 3}});
        Tour result = tour.createTourWithOrder(new int[]{0, 1, 1, 1, 2, 3});
        assertTrue(result.toString(), expected.isEqual(result));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of createPopularTour">
    /**
     * Test of createPopularTour method, of class Tour.
     */
    @Test
    public void testCreatePopularTour_sameTours() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour result = tourA.createPopularTour(tourB);
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreatePopularTour_EverySecondSameStartingFirst() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour tourB = new Tour(new int[][]{{0, 0}, {1, 2}, {2, 2}, {67, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {2, 2}});
        Tour result = tourA.createPopularTour(tourB);
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreatePopularTour_EverySecondSameStartingSecond() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour tourB = new Tour(new int[][]{{0, 2}, {1, 1}, {2, 22}, {3, 3}});
        Tour expected = new Tour(new int[][]{{1, 1}, {3, 3}});
        Tour result = tourA.createPopularTour(tourB);
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreatePopularTour_fewSamefewNot() {
        Tour tourA = new Tour(new int[][]{{0, 0}, {33, 23}, {1, 1}, {2, 2}, {3, 3}, {90, 300000}});
        Tour tourB = new Tour(new int[][]{{11, 12}, {0, 0}, {1, 1}, {34, 400}, {2, 2}, {3, 3}});
        Tour expected = new Tour(new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        Tour result = tourA.createPopularTour(tourB);
        assertTrue(result.toString(), expected.isEqual(result));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of createShortestTour">
    /**
     * Test of createShortestTour method, of class Tour.
     */
    @Test
    public void testCreateShortestTour() {
        Tour tour = new Tour(new int[][]{{10, 10}, {1, 1}, {22, 22}, {3, 3}});
        Tour result = tour.createShortestTour(1);
        Tour expected = new Tour(new int[][]{{1, 1}, {3, 3}, {10, 10}, {22, 22}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateShortestTour_OneDuplicate() {
        Tour tour = new Tour(new int[][]{{10, 10}, {1, 1}, {22, 22}, {3, 3}, {1, 1}});
        Tour result = tour.createShortestTour(1);
        Tour expected = new Tour(new int[][]{{1, 1}, {1, 1}, {3, 3}, {10, 10}, {22, 22}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateShortestTour_FalseIndex() {
        Tour tour = new Tour(new int[][]{{10, 10}, {1, 1}, {22, 22}, {3, 3}, {1, 1}});
        Tour result = tour.createShortestTour(-1);
        assertTrue(result.isEmpty());
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of createTourWithoutDuplicates">
    /**
     * Test of createTourWithoutDuplicates method, of class Tour.
     */
    @Test
    public void testCreateTourWithoutDuplicates_DupAtEnd() {
        Tour tour = new Tour(new int[][]{{1, 1}, {2, 2}, {1, 1}});
        Tour result = tour.createTourWithoutDuplicates();
        Tour expected = new Tour(new int[][]{{1, 1}, {2, 2}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateTourWithoutDuplicates_SeveralDuplicates() {
        Tour tour = new Tour(new int[][]{{1, 1}, {2, 2}, {1, 1}, {1, 1}, {2, 2}});
        Tour result = tour.createTourWithoutDuplicates();
        Tour expected = new Tour(new int[][]{{1, 1}, {2, 2}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of createUnion">
    /**
     * Test of createUnion method, of class Tour.
     */
    @Test
    public void testCreateUnion_OneAndOne() {
        Tour tourA = new Tour(new int[][]{{1, 1}});
        Tour tourB = new Tour(new int[][]{{12, 12}});
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{1, 1}, {12, 12}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateUnion_EmptyAndOne() {
        Tour tourA = new Tour();
        Tour tourB = new Tour(new int[][]{{12, 12}});
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{12, 12}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateUnion_OneAndEmpty() {
        Tour tourA = new Tour();
        Tour tourB = new Tour(new int[][]{{12, 12}});
        Tour result = tourB.createUnion(tourA);
        Tour expected = new Tour(new int[][]{{12, 12}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
    
    @Test
    public void testCreateUnion_SameElements() {
        Tour tourA = new Tour(new int[][]{{1, 1}, {12, 12}, {3, 3}, {2, 2}, {-2, -2}});
        Tour tourB = new Tour(new int[][]{{2, 2}, {12, 12}, {6, 6}, {1, 1}, {4, 4}});
        Tour result = tourA.createUnion(tourB);
        Tour expected = new Tour(new int[][]{{1, 1}, {2, 2}, {3, 3}, {4, 4}, {6, 6}, {12, 12}, {-2, -2}});
        assertTrue(result.toString(), expected.isEqual(result));
    }
//</editor-fold>
}
