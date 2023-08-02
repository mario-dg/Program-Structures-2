package ueb01;

/**
 *
 * Das Java-Programm bestimmt für einen gegebenen Zahlenbereich die Quersumme,
 * die Anzahl der benötigten Schritte in der Collatz-Folge, prüft ob die Zahl
 * eine Harshad Zahl ist, und verschlüsselt die Zahl mit einem gegebenen Key für
 * jede einzelne Zahl und gibt das Ergebnis nach angepassten Vorschriften aus.
 *
 * @author tinf103521, cgt103579 (Tjorben Klein, Mario da Graca)
 */
public class Grp12_ueb01 {

    /**
     * Berechnet die Anzahl der Stellen einer Zahl.
     *
     * @param num zu untersuchende Zahl
     * @return die Anzahl der Stellen
     */
    public static int calcNumLength(int num) {
        int len = num == 0 ? 1 : 0; //Wenn num 0 ist Länge gleich 1
        if (num < 0) {
            len++;  //Eine Stelle mehr für  Minuszeichen
            num *= -1;  //Den absoluten Wert nehmen
        }
        while (num > 0) {
            len++;
            num /= 10;  //Zehnerstellen ermitteln
        }
        return len;
    }

    /**
     * Ausgeben der größeren Zahl
     *
     * @param num Zahl1 zum vergleichen mit Zahl2
     * @param num2 Zahl2
     * @return gibt größere Zahl von beiden aus
     */
    public static int getMaxLength(int num, int num2) {
        int lengthOfNum = calcNumLength(num);
        int lengthOfNum2 = calcNumLength(num2);
        return lengthOfNum > lengthOfNum2
                ? lengthOfNum
                : lengthOfNum2;  //Gibt die größere Zahl aus
    }

    /**
     * Berechnen der Quersumme
     *
     * @param num Zahle dessen Quersumme berechnet werden soll
     * @return Quersumme der Zahl
     */
    public static int calcDigitSum(int num) {
        //TODO negativ DONE
        int digitSum = 0;
        if (num < 0) {
            num *= -1;
        }
        while (num > 0) {
            //Die aktuell letzte Ziffer wird auf die Summe der vorherigen 
            //letzten Ziffern addiert
            digitSum += num % 10;
            num /= 10; //Die letzte Stelle wird durch div 10 Operation entfernt
        }
        return digitSum;
    }

    /**
     * Prüft, ob eine Zahl eine Harshad-Zahl ist Gibt true zurück, wenn num
     * größer 0 und durch die eigene Quersumme teilbar ist
     *
     * @param num zu prüfende Zahl
     * @return true oder false, für die Bedingung
     */
    public static boolean checkHarshad(int num) {
        return (num > 0) && (num % calcDigitSum(num)) == 0;
    }

    /**
     * zählt die Anzahl der Schritte der Collatz-Folge
     *
     * @param num Zahl, deren Collatz-Folge gebildet wird
     * @return counter Anzahl der Schritte der Collatz-Folge
     */
    public static int countCollatzSteps(int num) {
        int counter = 0;

        if (num <= 0) {
            // Für Zahlen kleiner oder gleich 0, wird der Counter auf -1 gesetzt
            counter = -1;
        } else {
            while (num > 1) {
                if ((num % 2) == 1) { //Ermittelt ob num gerade bzw ungerade ist
                    num = (num * 3) + 1; //Rechenschritt bei ungerader Zahl
                    counter++;
                } else {
                    num /= 2; //Rechenschritt bei gerader Zahl
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * Prüft den Key auf Korrektheit Der Key soll 8 Bit groß sein. Bei
     * Verschiebung um 8 Bits soll er also 0 sein
     *
     * @param key zu prüfender Key
     * @return true oder false, ob Key korrekt
     */
    public static boolean isKeyValid(int key) {
        return ((key >>> 8) == 0);
    }

    /**
     * Verschlüsselt num mit key (Bitweise xor Verknüpfung)
     *
     * @param num zu verschlüsselende Zahl
     * @param key Zahl mit der verschlüsselt wird
     * @return verschlüsselte Zahl
     */
    public static int encryptXOR(int num, int key) {
        //Key wird 4 Mal hintereinander in eine Variable geschrieben
        key += ((key << 24) + (key << 16) + (key << 8));

        return num ^ key; //Bitweise XOR-Verschlüsselung
    }

    /**
     * Wandelt Zahl in Binärstring um
     *
     * @param num zu konvertierende dezimal Zahl
     * @return String, mit der konvertieren Binärzahl
     */
    public static String getBinaryString(int num) {
        String BinaryString = "";
        while (num != 0) {
            //Das Bit an letzter Stelle von num wird an den Anfang 
            //(des Strings geschrieben
            BinaryString = (num & 1) + BinaryString;
            num >>>= 1;  //Bitverschiebung um 1 nach rechts entspricht div 2
        }
        return BinaryString;
    }

    /**
     * Formatierung und Ausgabe der Ergebnisse nach Vorgabe
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final int START = -2;
        final int END = 5;
        final int KEY = 25;

        //TODO DONE width valid
        int WIDTH = getMaxLength(START, END);
        boolean VALIDKEY = isKeyValid(KEY);

        System.out.printf("Untersucht werden Zahlen zwischen %d und %d. %n",
                          START, END);

        System.out.printf(VALIDKEY
                ? "Schlüssel = "
                : "WARNUNG: Verschlüsselung nicht möglich, ungültiger Schlüssel = ");

        System.out.printf("%s (%d) %n", getBinaryString(KEY), KEY);
        //Für jede Zahl in dem Intervall von START bis END wird 
        //eine Ausgabe gemacht
        for (int i = START; i <= END; i++) {

            System.out.printf("%" + WIDTH
                    + "d: Quersumme = %2d", i, calcDigitSum(i));

            System.out.printf((countCollatzSteps(i) == -1)
                    ? ""
                    : ", Collatz-Schritte = %3d", countCollatzSteps(i));

            System.out.printf(checkHarshad(i) ? ", Harshad-Zahl" : "");

            System.out.printf(!VALIDKEY
                    ? "%n"
                    : "%n %" + WIDTH
                    + "s Verschlüsselung = %32s (%10d) %n", " ",
                    getBinaryString(encryptXOR(i, KEY)), encryptXOR(i, KEY));
        }
    }
}
