
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
     * 
     * @param leftChild is a Node which represents the first factor.
     * @param rightChild is a Node which represents the second factor.
     */
    public Multiplication(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    // Returns Latex of Multiplication
    public String toLatex() {
        return "{\\left(" + leftChild.toLatex() + "*" + rightChild.toLatex() + "\\right)}";
    }
}
