/**
 * An Integer division.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
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
    public Division(final Node leftChild, final Node rightChild) {
        super();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
 
    @Override
    // Returns Latex of Division
    public String toString() {
        return "\\frac{" + leftChild.toString() + "}{" + rightChild.toString() + "}";
    }
}
