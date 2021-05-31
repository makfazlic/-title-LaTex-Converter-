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
     * Constructor for objects of class Error.
     * 
     * @param errorMessage is a String which represents the correct message when
     *          there's an error.
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
