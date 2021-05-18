
/**
 * Write a description of class Limit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Limit extends Node
{
    private final Node child;
    private final Node variable;
    private final Node value;

    /**
     * Constructor for objects of class Power.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Limit(final Node child, final Node variable, final Node value)
    {
        super();
        this.child = child;
        this.variable = variable;
        this.value = value;
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
 
    @Override
    public String toString() {
        return "(/lim_{" + variable.toString() + "/" + value.toString() + "}{" + child.toString() + "})";
    }
}
