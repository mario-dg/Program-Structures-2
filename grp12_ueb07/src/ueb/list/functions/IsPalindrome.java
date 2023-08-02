package ueb.list.functions;

/**
 * Prüft, ob ein String ein Palindrom ist
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class IsPalindrome implements PredicateFunctionObject<String> {
 
    /**
     * Prüft, ob ein String ein Palindrom ist
     * @param s String der geprüft wird
     * @return boolean, ob Palindrom oder nicht
     */
    @Override
    public boolean call(String s){
        // Anwendung der Klasse StringBuilder, um Methode reverse zu nutzen
        // StringBuilder reverseString = new StringBuilder(s);
        // return reverseString == reverseString.reverse();

        String reverse = "";
        for (int i = s.length(); i >= 0; i--) {
            reverse += s.charAt(i);
        }
        
        return s.equalsIgnoreCase(reverse);
    }
}
