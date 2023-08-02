package ueb05;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static ueb05.Color.*;

/**
 *
 * @author klk
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ColorTest {

    //<editor-fold defaultstate="collapsed" desc="Test of getColor">
    /**
     * Test of getColor method, of class Color.
     */
    @Test
    public void testCreateColor_3args_ExistingColors() {
        Color color = Color.getColor(0, 0, 0);
        assertEquals(BLACK, color);

        color = Color.getColor(0, 0, 0xff);
        assertEquals(BLUE, color);
    }

    @Test
    public void testCreateColor_3args_notExistingColors() {
        Color color = Color.getColor(1, 1, 1);
        assertNull(color);

        color = Color.getColor(0, 0, 1);
        assertNull(color);
    }

    @Test
    public void testCreateColor_int_ExistingColor() {
        Color color = Color.getColor(0);
        assertEquals(BLACK, color);

        color = Color.getColor(0xff);
        assertEquals(BLUE, color);

        color = Color.getColor(0x00ffff);
        assertEquals(AQUA, color);
    }

    @Test
    public void testCreateColor_Array_ExistingColor() {
        int[] rgb = new int[3];
        rgb[0] = 0x00;
        rgb[1] = 0xFF;
        rgb[2] = 0xFF;

        Color color = Color.getColor(rgb);
        assertEquals(AQUA, color);
    }

    @Test
    public void testCreateColor_Array_NotExistingColor() {
        int[] rgb = new int[3];
        rgb[0] = 0x00;
        rgb[1] = 0x11;
        rgb[2] = 0xFF;

        Color color = Color.getColor(rgb);
        assertNull(color);
    }
//</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Test of toString">
    /**
     * Test of toString method, of class Color.
     */
    @Test
    public void testToString() {
        assertEquals("WHITE    #FFFFFF", WHITE.toString());
        assertEquals("BLUE     #0000FF", BLUE.toString());
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of isKnownColor">
    /**
     * Test of isKnownColor method, of Class Color
     */
    @Test
    public void testIsKnownColor_String_ExistingColor() {
        String color;

        color = "BluE";
        assertTrue(isKnownColor(color));

        color = "RED";
        assertTrue(isKnownColor(color));

        color = "yellow";
        assertTrue(isKnownColor(color));
    }

    @Test
    public void testIsKnownColor_String_NotExistingColor() {
        String color;

        color = "Gelb";
        assertFalse(isKnownColor(color));
    }

    @Test
    public void testIsKnownColor_Array_ExistingColor() {
        int[] rgb = new int[3];
        rgb[0] = 0;
        rgb[1] = 0;
        rgb[2] = 0;
        assertTrue(isKnownColor(rgb));
    }

    @Test
    public void testIsKnownColor_Array_NotExistingColor() {
        int[] rgb = new int[3];
        rgb[0] = 1;
        rgb[1] = 1;
        rgb[2] = 1;
        assertFalse(isKnownColor(rgb));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Test of getRGB"> 
    @Test
    public void testGetRGB_Array() {
        int[] result = new int[3];
        result[0] = 0x00;
        result[1] = 0xFF;
        result[2] = 0xFF;

        Color color = AQUA;

        assertArrayEquals(color.getRGB(), result);
    }

    @Test
    public void testGetRGB_Array_static() {
        int[] result = new int[3];
        result[0] = 0x00;
        result[1] = 0xFF;
        result[2] = 0xFF;

        assertArrayEquals(getRGB("AQUA"), result);
    }

    public void testGetPackedRGB() {
        int packedRGB = 0b000000001111111111111111;

        Color color = AQUA;

        assertEquals(packedRGB, color.getPackedRGB());
    }
//</editor-fold>
}
