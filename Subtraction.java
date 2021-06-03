/**
 * An Integer Subtraction.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Subtraction extends Node {
    
    // instance variables
    private final Node leftChild;
    private final Node rightChild;
    
    /**
     * Create a new Subtraction node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Subtraction(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    // Returns Latex of Subtraction
    public String toLatex() {
        return "{\\left(" + leftChild.toLatex() + "-" + rightChild.toLatex() + "\\right)}";
    }
    
}
