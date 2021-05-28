/**
 * An integer negation (e.g., -5, or -x).
 * 
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Negation extends Node {

    // instance variables
    private final Node child;
    
    /**
     * Create a new Negation node.
     * @param child the operand we will negate
     */
    public Negation(final Node child) {
        super();
        this.child = child;
    }

    @Override
    // Returns Latex of Negation
    public String toString() {
        return "{(-" + child.toString() + ")}";
    }
    
}
