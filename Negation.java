/**
 * An integer negation (e.g., -5, or -x).
 */
public class Negation extends Node {
    
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
    public Type getType() {
        return Type.INT;
    }

    @Override
    public String toString() {
        return "(-" + child.toString() + ")";
    }
    
}
