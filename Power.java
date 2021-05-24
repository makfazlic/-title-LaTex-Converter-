
/**
 * An Integer Power
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Power extends Node
{
    // instance variables
    private final Node child;
    private final Node exponent;

    /**
     * Constructor for objects of class Power.
     * @param child expression Node.
     * @param exponent expression Node.
     */
    public Power(final Node child, final Node exponent) {
        super();
        this.child = child;
        this.exponent = exponent;
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
 
    @Override
    // Returns Latex of Power
    public String toString() {
        return "{" + child.toString() + "^{" + exponent.toString() + "}}";
    }
}
