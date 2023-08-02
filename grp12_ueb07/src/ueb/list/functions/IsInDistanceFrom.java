package ueb.list.functions;
import ueb.Waypoint;

/**
 * Prüft, ob die Distanz von zwei Wegpunkten kleiner ist, als eine vorgegebene Distanz
 * @author cgt103579(Mario da Graca), tinf103521(Tjorben Klein)
 */
public class IsInDistanceFrom implements PredicateFunctionObject<Waypoint> {
    
    /**
     * Instance Variables
     */
    private final Waypoint referenceWaypoint;
    private final double distance;

    /**
     * Konstruktor mit 2 Wegpunkten
     * @param referenceWaypoint Wegpunkt zu dem die Distanz berechnet wird
     * @param distance für die geprüft wird, ob sie überschritten wird
     */
    public IsInDistanceFrom(Waypoint referenceWaypoint, double distance) {
        this.referenceWaypoint = referenceWaypoint;
        this.distance = distance;
    }
    
    /**
     * Default Konstruktor
     */
    public IsInDistanceFrom(){
    referenceWaypoint = null;
    distance = Double.MIN_VALUE;
    }
    
    /**
     * Prüft, ob die Distanz zwischen zwei Punkten kleiner ist als eine vorgegebene
     * @param other Wegpunkt zu dem die Distanz berechnet wird
     * @return boolean, ob Distanz kleiner oder größer ist
     */
    @Override
    public boolean call(Waypoint other){
    return this.referenceWaypoint.calcDistance(other) < distance;
    }
    
}
