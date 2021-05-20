/**
 * An integer addition.
 */
public class Addition extends Node {
    
    private final Node leftChild;
    private final Node rightChild;

    
    /**
     * Create a new Addition node.
     * @param leftChild the left operand
     * @param rightChild the right operand
     */
    public Addition(final Node leftChild, final Node rightChild) {
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
        return "(" + leftChild.toString() + "+" + rightChild.toString() + ")";
    }
    
}
