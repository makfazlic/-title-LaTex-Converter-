/**
 * An integer subtraction.
 */
public class Subtraction extends Node {
    
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
    public Type getType() {
        return Type.INT;
    }

    @Override
    public String toString() {
        return "(" + leftChild.toString() + "-" + rightChild.toString() + ")";
    }
    
}
