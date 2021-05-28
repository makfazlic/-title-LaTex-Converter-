/**
 * Conversion of Limits.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Limit extends Node
{
    // instance variables
    private final Node child;
    private final Node from;
    private final Node to;

    /**
     * Constructor for objects of class LLimit.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Limit(final Node child, final Node from, final Node to){
        super();
        this.child = child;
        this.from = from;
        this.to = to;
    }
 
    @Override
    // Returns Latex of Limit
    public String toString() {
        return "\\lim_{" + from.toString() + "\\to " + to.toString() + "}{" + child.toString() + "}";
    }
}
