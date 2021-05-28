/**
 * An Integer Addition.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Addition extends Node {

    // instance variables
    private final Node leftChild;
    private final Node rightChild;
    
    /**
     * Constructor for objects of class Division.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Addition(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
 
    @Override
    // Returns Latex of Addition
    public String toString() {
        return "{\\left(" + leftChild.toString() + "+" + rightChild.toString() + "\\right)}";
    }
    
}
