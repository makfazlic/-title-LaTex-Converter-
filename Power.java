
/**
 * An Integer Power
 *
 * @author Roberto Ferrari
 * @version v0.1
 */
public class Power extends Node
{
    // instance variables - replace the example below with your own
    private final Node child;
    private final Node exponent;

    /**
     * Constructor for objects of class Power.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Power(final Node child, final Node exponent)
    {
        super();
        this.child = child;
        this.exponent = exponent;
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
 
    @Override
    public String toString() {
        return "(" + child.toString() + "^{" + exponent.toString() + "})";
    }
}
