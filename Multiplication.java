
/**
 * An Integer multiplication.
 *
 * @author Roberto Ferrari
 * @version v0.1
 */
public class Multiplication extends Node
{
    // instance variables - replace the example below with your own
    private final Node leftChild;
    private final Node rightChild;

    /**
     * Constructor for objects of class Multiplication.
     * @param leftChild is the first node of the expression.
     * @param rightChild is the second node of the expression.
     */
    public Multiplication(final Node leftChild, final Node rightChild)
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
        return "(" + leftChild.toString() + "*" + rightChild.toString() + ")";
    }
}
