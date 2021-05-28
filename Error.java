/**
 * An Integer Addition.
 *
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Error extends Node {

    // instance variables
    private final String errorMessage;
    
    /**
     * Constructor for objects of class Division.
     * @param leftChild expression Node.
     * @param rightChild expression Node.
     */
    public Error(final String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
 
    @Override
    // Returns Latex of Addition
    public String toString() {
        return this.errorMessage;
    }
    
}
