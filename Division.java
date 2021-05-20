
/**
 * An Integer division.
 *
 * @author Roberto Ferrari
 * @version v0.1
 */
public class Division extends Node
{
    // instance variables - replace the example below with your own
    private final Node leftChild;
    private final Node rightChild;

    /**
     * Constructor for objects of class Division.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Division(final Node leftChild, final Node rightChild)
    {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    @Override
    public Type getType() {
        return Type.INT;
    }
 
    @Override
    public String toString() {
        return "(/frac{" + leftChild.toString() + "}{" + rightChild.toString() + "})";
    }
}
