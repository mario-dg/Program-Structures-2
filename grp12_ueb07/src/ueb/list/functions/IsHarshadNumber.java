package ueb.list.functions;

/**
 * Prüft, ob eine übergeben Zahl eine Harshad Zahl ist
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class IsHarshadNumber implements PredicateFunctionObject<Integer> {
    
     /**
     * Berechnen der Quersumme
     *
     * @param num Zahle dessen Quersumme berechnet werden soll
     * @return Quersumme der Zahl
     */
    public static int calcDigitSum(int num) {
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
     * @param num Zahl die geprüft wird
     * @return true wenn die Zahl eine Harshadzahl ist
     */
    @Override
    public boolean call(Integer num){
    return (num > 0) && (num % calcDigitSum(num)) == 0;
    }
    
}
