package ueb05;

/**
 *
 * @author Mario
 */
public class Grp12_ueb05 {
    //<editor-fold defaultstate="collapsed" desc="Constant">
    /**
     * Lösung mit fortlaufendem Index kriegen wir nur so gelöst
     */
    public static int index;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Main Method">
    /**
     * Main Methode - Hauptausgabe der Liste. Unsortiert und sortiert
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Füge folgende Figuren zu:");

        Figure triangle = new Triangle(Color.BLUE, 7, 8, 4);
        Figure circle = new Circle(Color.GREEN, 3);
        Figure rectangle = new Rectangle(Color.getColor("keinefarbe"), 7, 8);
        Figure square = new Square(Color.YELLOW, 2.5);

        Liste newList = new Liste(triangle, circle, rectangle, square);
        System.out.println(newList.start);
        
        System.out.printf("Liste neu erzeugt mit%2d Elementen.\n", newList.size());
        System.out.println("------------------------------");
        
        Liste sortedList = new Liste();
        
        for (Figure i : newList.getValues()) {
            sortedList = sortedList.insert(i);
        }
        
        index = 0;
        System.out.println(sortedList.start);
    }
//</editor-fold>
}
