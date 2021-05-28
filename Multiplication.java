
/**
 * An Integer Multiplication.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Multiplication extends Node
{
    // instance variables
    private final Node leftChild;
    private final Node rightChild;

    /**
     * Constructor for objects of class Multiplication.
     * @param leftChild is the first node of the expression.
     * @param rightChild is the second node of the expression.
     */
    public Multiplication(final Node leftChild, final Node rightChild){
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    // Returns Latex of Multiplication
    public String toString() {
        return "{\\left(" + leftChild.toString() + "*" + rightChild.toString() + "\\right)}";
    }
}
