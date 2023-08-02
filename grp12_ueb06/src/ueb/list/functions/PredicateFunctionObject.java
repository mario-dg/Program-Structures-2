package ueb.list.functions;

/**
 * Super-Klasse deren call Methode überschrieben wird
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class PredicateFunctionObject<P> {

    /**
     * Bekommt einen Datentyp übergeben und liefert true zurück (Dient nur zum überschreiben)
     * @param value beliebiger Datentyp der übergeben wird
     * @return true
     */
    public boolean call(P value) {
        return true;
    }

}
