/**
 * A Literal is an AST node that 
 * corresponds to a literal integer value
 * (a number in the source code).
 * 
 * @author Mak Fazlic & Roberto Ferrari - Latex project.
 * @version v1.0
 */
public class Literal extends Node {
    
    // instance variables
    private final int value;
    
    /**
     * Create a new Literal node.
     * @param value the integer value this node evaluates to
     */
    public Literal(final int value) {
        super();
        this.value = value;
    }

    @Override
    // Returns Latex of Literal
    public String toString() {
        return "{" + value + "}";
    }
    
}
