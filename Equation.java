/**
 * A simple equation.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Equation extends Node {

    // instance variables
    private final Node leftChild;
    private final Node rightChild;
    
    /**
     * Constructor for objects of class Equation.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Equation(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
 
    @Override
    // Returns Latex of Equation
    public String toString() {
        return "" + leftChild.toString() + " = " + rightChild.toString() + "";
    }
    
}
