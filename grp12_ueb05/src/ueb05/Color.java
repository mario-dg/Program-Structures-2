package ueb05;

import java.util.Arrays;

/**
 * Enum für die Farben der Figuren. Ebenso einige Methoden, die benötigt werden, um später
 * mit den Farben zu arbeiten
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public enum Color {
    
    /**
     * Farben mit Hex-Werten
     */
    BLACK(0x00, 0x00, 0x00),
    NAVY(0x00, 0x00, 0x80),
    BLUE(0x00, 0x00, 0xFF),
    GREEN(0x00, 0x80, 0x00),
    LIME(0x00, 0xFF, 0x00),
    AQUA(0x00, 0xFF, 0xFF),
    PURPLE(0x80, 0x00, 0x80),
    SILVER(0xC0, 0xC0, 0xC0),
    RED(0xFF, 0x00, 0x00),
    FUCHSIA(0xFF, 0x00, 0xFF),
    YELLOW(0xFF, 0xFF, 0x00),
    WHITE(0xFF, 0xFF, 0xFF);

    //<editor-fold defaultstate="collapsed" desc="Constants">
    /* the values are handled like attributes */
    /**
     * Konstanten für Farbwerte
     */
    private final int r;
    private final int g;
    private final int b;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor 3 Parameters">
    /* there must be a private constructor that takes all attributes */
    /**
     * Konstruktor mit 3 Parametern
     * @param red Rotwert
     * @param green Grünwert
     * @param blue Blauwert
     */
    private Color(int red, int green, int blue) {
        this.r = red;
        this.g = green;
        this.b = blue;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter-Methods der Farbwerte">
    /**
     * Getter Methode
     * @return liefert den Rotwert zurück
     */
    public int getR() {
        return r;
    }
    
    public int getG() {
        return g;
    }
    
    public int getB() {
        return b;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method colorToArray">
    /**
     * Liefert die Farbwerte in einem Array zurück
     * @param color
     * @return
     */
    private static int[] colorToArray(Color color) {
        int[] rgb = new int[3];
        rgb[0] = color.r;
        rgb[1] = color.g;
        rgb[2] = color.b;
        
        return rgb;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getColor">
    /**
     * Liefert die Farbe aus 3 int Werten zurück
     * @param r Rotwert
     * @param g Grünwert
     * @param b Blauwert
     * @return Color, passende Farbe
     */
    public static Color getColor(int r, int g, int b) {
        for (Color c : values()) {
            if ((r == c.r) && (g == c.g) && (b == c.b)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Liefert die Farbe aus einem Array zurück
     * @param rgb Array mit den Farbwerten
     * @return Color, passende Farbe
     */
    public static Color getColor(int[] rgb) {
        for (Color c : values()) {
            if (Arrays.equals(colorToArray(c), rgb)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Liefert die Farbe aus einem int Wert zurück
     * @param otherValue Alle 3 Farbwerte in einem Int Wert gespeichert
     * @return Color, passende Farbe
     */
    public static Color getColor(int otherValue) {
        
        for (Color c : values()) {
            int value;
            value = (c.getR() << 16) + (c.getG() << 8) + (c.getB());
            if (value == otherValue) {
                return c;
            }
        }
        return null;
    }

    /**
     * Liefert die Farbe aus einem String zurück
     * @param otherName Farbe im String
     * @return Color, passende Farbe
     */
    public static Color getColor(String otherName) {
        for (Color c : values()) {
            String name;
            name = c.name();
            if (name.equalsIgnoreCase(otherName)) {
                return c;
            }

        }
        return null;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Method isKnownColor">
    /**
     * Prüft, ob die Farbe bekannt ist
     * @param rgb Array mit den Farbwerten
     * @return boolean, ob die Farbe bekannt ist oder nicht
     */
    public static boolean isKnownColor(int[] rgb) {
        return getColor(rgb) != null;
    }
    
    /**
     * Prüft, ob die Farbe bekannt ist
     * @param colorName String mit der Farbe
     * @return boolean, ob die Farbe bekannt ist oder nicht
     */
    public static boolean isKnownColor(String colorName) {
        return getColor(colorName) != null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method getRGB">
    /**
     * Liefert die RGB Werte in einem Array zurück
     * @return int[], Array mit den Farbwerten
     */
    public int[] getRGB() {
        int[] rgb = new int[3];
        
        rgb[0] = r;
        rgb[1] = g;
        rgb[2] = b;
        
        return rgb;
    }
    
    /**
     * Liefert die RGB Werte in einem Array zurück
     * @param name String mit der Farbe
     * @return int[], Array mit den Farbwerten
     */
    public static int[] getRGB(String name) {
        Color color;
        //FIXME DONE Nullpointerexception wenn ungültiger Name angegeben wird
        if (isKnownColor(name)) {
        color = getColor(name);
        return colorToArray(color);
        }else {
            return new int[]{};
        }
        
    }
    
    /**
     * Liefert die RGB Werte in einem Int Wert zurück
     * @return int, mit den Farbwerten
     */
    public int getPackedRGB() {
        int packedRGB;
        
        packedRGB = (r << 16) + (g << 8) + (b);
        
        return packedRGB;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Method toString">
    /**
     * Liefert Stringdarstellung der Farbe
     * @return Farbe in Stringdarstellung
     */
    public String toString() {
        String output;
        Color color;
        
        color = getColor(r, g, b);
        output = String.format("%-9s#%06X",color.name(), color.getPackedRGB());
        
        return output;
    }
//</editor-fold>

}
